import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Map;
import java.util.Map.Entry;
import java.io.BufferedReader;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author Miles Stevenson
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task1061 solver = new Task1061();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task1061 {
        Map<String, ArrayList<String>> bloodToABO = new HashMap<>();
        Map<String, String> ABOtoBlood = new HashMap<>();
        String[] p = new String[3];
        int pos = 0;
        int c = 1;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            setup();
            p[0] = in.next();
            p[1] = in.next();
            p[2] = in.next();
            while (!p[0].equals("E") && !p[1].equals("N") && !p[2].equals("D")) {
                findPosition();
                p[pos] = computePossibilities();

                out.println("Case " + c + ": " + p[0] + " " + p[1] + " " + p[2]);
                p[0] = in.next();
                p[1] = in.next();
                p[2] = in.next();
                c++;
            }
        }

        private String computePossibilities() {
            List<String> pSet = new ArrayList<>();
            if (pos == 2) {
                // we have parents and need to find the child
                findChild(pSet);
            } else {
                // we have a parent and a child and need to find a parent
                findParent(pSet, p[(pos == 1) ? pos - 1 : pos + 1], p[2]);
            }

            String totalPossibilities = prettify(pSet);
            return totalPossibilities;
        }

        private void findChild(List<String> pSet) {
            String s0 = p[0].contains("+") ? "+" : "-";
            String p0 = p[0].replace(s0, "");
            List<String> list0 = bloodToABO.get(p0);

            String s1 = p[1].contains("+") ? "+" : "-";
            String p1 = p[1].replace(s1, "");
            List<String> list1 = bloodToABO.get(p1);

            // list of ABO allele pairs i.e <AA, AO, ..>
            for (int i = 0; i < list0.size(); i++) {
                // list of ABO allele pairs i.e. <BB, BO, ..>
                for (int j = 0; j < list1.size(); j++) {
                    // current ABO allele pair i.e AA
                    for (int k = 0; k < list0.get(i).length(); k++) {
                        // current ABO allele pair i.e BO
                        for (int l = 0; l < list1.get(j).length(); l++) {
                            String first = "" + list0.get(i).charAt(k);
                            String second = "" + list1.get(j).charAt(l);
                            String bloodType = findABOtoBlood(first + second);

                            // not impossible
                            if (bloodType.equals("IMPOSSIBLE")) {
                                pSet.add(bloodType);
                                return;
                            }

                            // one blood type
                            if (s0.equals("-") && s1.equals("-")) {
                                if (!pSet.contains(bloodType + "-"))
                                    pSet.add(bloodType + "-");
                            } else {
                                // two blood types
                                if (!pSet.contains(bloodType + "-"))
                                    pSet.add(bloodType + "-");
                                if (!pSet.contains(bloodType + "+"))
                                    pSet.add(bloodType + "+");
                            }
                        }
                    }
                }
            }
        }

        private void findParent(List<String> pSet, String parent, String child) {
            String signParent = parent.contains("+") ? "+" : "-";
            String trimmedParent = parent.replace(signParent, "");

            String signChild = child.contains("+") ? "+" : "-";
            String trimmedChild = child.replace(signChild, "");

            List<String> parentABOs = bloodToABO.get(trimmedParent);
            for (int i = 0; i < parentABOs.size(); i++) {
                for (int j = 0; j < parentABOs.get(i).length(); j++) {
                    for (Map.Entry<String, String> entry : ABOtoBlood.entrySet()) {
                        // ABO allele combination
                        String key = entry.getKey();

                        // blood type allele
                        String value = entry.getValue();

                        // allele
                        String allele = "" + parentABOs.get(i).charAt(j);

                        if (isAlleleValid(allele, key, trimmedChild)) {
                            if (signParent.equals("-") && signChild.equals("+")) {
                                if (!pSet.contains(value + "+"))
                                    pSet.add(value + "+");
                            } else {
                                if (!pSet.contains(value + "+"))
                                    pSet.add(value + "+");
                                if (!pSet.contains(value + "-"))
                                    pSet.add(value + "-");
                            }
                        }
                    }
                }
            }
            if (pSet.size() == 0) {
                pSet.add("IMPOSSIBLE");
            }
        }

        private boolean isAlleleValid(String allele, String abo, String child) {
            List<String> childAbos = bloodToABO.get(child);

            for (int i = 0; i < abo.length(); i++) {
                String newAbo = allele + abo.charAt(i);
                for (int j = 0; j < childAbos.size(); j++) {
                    if (newAbo.equals(childAbos.get(j))) {
                        return true;
                    }
                }
            }

            return false;
        }

        private String findABOtoBlood(String abo) {
            if (ABOtoBlood.containsKey(abo)) {
                return ABOtoBlood.get(abo);
            }
            return "IMPOSSIBLE";
        }

        private String prettify(List<String> pSet) {
            String totalPossibilities = "{";
            if (pSet.size() == 1) {
                return pSet.get(0);
            }
            for (int i = 0; i < pSet.size(); i++) {
                totalPossibilities = totalPossibilities + pSet.get(i);
                if (i != pSet.size() - 1)
                    totalPossibilities = totalPossibilities + ", ";
            }
            totalPossibilities = totalPossibilities + "}";
            return totalPossibilities;
        }

        private void findPosition() {
            for (int i = 0; i < p.length; i++)
                if (p[i].equals("?"))
                    pos = i;
        }

        private void setup() {
            bloodToABO.put("A", new ArrayList<String>());
            bloodToABO.get("A").add("AA");
            bloodToABO.get("A").add("AO");
            bloodToABO.get("A").add("OA");

            bloodToABO.put("AB", new ArrayList<String>());
            bloodToABO.get("AB").add("AB");
            bloodToABO.get("AB").add("BA");

            bloodToABO.put("B", new ArrayList<String>());
            bloodToABO.get("B").add("BB");
            bloodToABO.get("B").add("BO");
            bloodToABO.get("B").add("OB");

            bloodToABO.put("O", new ArrayList<String>());
            bloodToABO.get("O").add("OO");

            ABOtoBlood.put("AA", "A");
            ABOtoBlood.put("AO", "A");
            ABOtoBlood.put("OA", "A");

            ABOtoBlood.put("AB", "AB");
            ABOtoBlood.put("BA", "AB");

            ABOtoBlood.put("BB", "B");
            ABOtoBlood.put("OB", "B");
            ABOtoBlood.put("BO", "B");

            ABOtoBlood.put("OO", "O");
        }

    }

    static class InputReader {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream));
            tokenizer = null;
        }

        public String nextLine() {
            try {
                return reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public String next() {
            try {
                while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                    tokenizer = new StringTokenizer(nextLine());
                }
                return tokenizer.nextToken();
            } catch (NullPointerException e) {
                return null;
            }
        }

    }
}

