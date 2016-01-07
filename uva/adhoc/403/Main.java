import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.io.IOException;
import java.util.HashMap;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Map;
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
        Task403 solver = new Task403();
        solver.solve(1, in, out);
        out.close();
    }

    static class Task403 {
        C5CharRef c5 = new C5CharRef();
        Board board = new Board();

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            String msg;
            while ((msg = in.nextLine()) != null) {
                // parse command input
                if (msg.equals(".EOP")) {
                    for (int i = 0; i < board.grid.length; i++) {
                        for (int j = 0; j < board.grid[i].length; j++) {
                            out.print(board.grid[i][j]);
                        }
                        out.println();
                    }
                    out.println();
                    for (int i = 0; i < 60; i++)
                        out.print("-");
                    out.println();
                    out.println();
                    board = new Board();
                    continue;
                }
                Command c = new Command(msg);
                if (c.font.equals("C1")) {
                    board.placeMsg(c.row, c.col, c.command);
                } else {
                    board.placeMsg(c.row, c.col, c5.transform(c.command), c5.blankBitMap);
                }
            }
        }

        class C5CharRef {
            Map<Character, String[]> bigChar;
            boolean[] blankBitMap;

            C5CharRef() {
                bigChar = new HashMap<>();
                initializeMap();
            }

            public String[] transform(String msg) {
                blankBitMap = new boolean[msg.length() * 6];
                String[][] strA = new String[msg.length()][5];
                Arrays.fill(blankBitMap, false);

                for (int i = 0; i < msg.length(); i++) {
                    char c = msg.charAt(i);
                    strA[i] = bigChar.containsKey(c) ? bigChar.get(c) : bigChar.get(' ');
                    if (c == ' ' || !bigChar.containsKey(c))
                        setBlanks(i * 6);
                }

                String[] transformedMsg = new String[5];

                for (int i = 0; i < 5; i++) {
                    String s = "";
                    for (int j = 0; j < msg.length(); j++) {
                        s += strA[j][i];
                    }
                    transformedMsg[i] = s;
                }

                return transformedMsg;
            }

            private void setBlanks(int start) {
                for (int i = start; i < start + 6; i++)
                    blankBitMap[i] = true;
            }

            private void initializeMap() {
                bigChar.put('A', new String[]{
                        ".***..",
                        "*...*.",
                        "*****.",
                        "*...*.",
                        "*...*." // A
                });

                bigChar.put('B', new String[]{
                        "****..",
                        "*...*.",
                        "****..",
                        "*...*.",
                        "****.." // B
                });

                bigChar.put('C', new String[]{
                        ".****.",
                        "*...*.",
                        "*.....",
                        "*.....",
                        ".****." // C
                });

                bigChar.put('D', new String[]{
                        "****..",
                        "*...*.",
                        "*...*.",
                        "*...*.",
                        "****.." // D
                });

                bigChar.put('E', new String[]{
                        "*****.",
                        "*.....",
                        "***...",
                        "*.....",
                        "*****." // E
                });

                bigChar.put('F', new String[]{
                        "*****.",
                        "*.....",
                        "***...",
                        "*.....",
                        "*....." // F
                });

                bigChar.put('G', new String[]{
                        ".****.",
                        "*.....",
                        "*..**.",
                        "*...*.",
                        ".***.." // G
                });

                bigChar.put('H', new String[]{
                        "*...*.",
                        "*...*.",
                        "*****.",
                        "*...*.",
                        "*...*." // H
                });

                bigChar.put('I', new String[]{
                        "*****.",
                        "..*...",
                        "..*...",
                        "..*...",
                        "*****." // I
                });

                bigChar.put('J', new String[]{
                        "..***.",
                        "...*..",
                        "...*..",
                        "*..*..",
                        ".**..." // J
                });

                bigChar.put('K', new String[]{
                        "*...*.",
                        "*..*..",
                        "***...",
                        "*..*..",
                        "*...*." // K
                });

                bigChar.put('L', new String[]{
                        "*.....",
                        "*.....",
                        "*.....",
                        "*.....",
                        "*****." // L
                });

                bigChar.put('M', new String[]{
                        "*...*.",
                        "**.**.",
                        "*.*.*.",
                        "*...*.",
                        "*...*." // M
                });

                bigChar.put('N', new String[]{
                        "*...*.",
                        "**..*.",
                        "*.*.*.",
                        "*..**.",
                        "*...*." // N
                });

                bigChar.put('O', new String[]{
                        ".***..",
                        "*...*.",
                        "*...*.",
                        "*...*.",
                        ".***.." // O
                });

                bigChar.put('P', new String[]{
                        "****..",
                        "*...*.",
                        "****..",
                        "*.....",
                        "*....." // P
                });

                bigChar.put('Q', new String[]{
                        ".***..",
                        "*...*.",
                        "*...*.",
                        "*..**.",
                        ".****." // Q
                });

                bigChar.put('R', new String[]{
                        "****..",
                        "*...*.",
                        "****..",
                        "*..*..",
                        "*...*." // R
                });

                bigChar.put('S', new String[]{
                        ".****.",
                        "*.....",
                        ".***..",
                        "....*.",
                        "****.." // S
                });

                bigChar.put('T', new String[]{
                        "*****.",
                        "*.*.*.",
                        "..*...",
                        "..*...",
                        ".***.." // T
                });

                bigChar.put('U', new String[]{
                        "*...*.",
                        "*...*.",
                        "*...*.",
                        "*...*.",
                        ".***.." // U
                });

                bigChar.put('V', new String[]{
                        "*...*.",
                        "*...*.",
                        ".*.*..",
                        ".*.*..",
                        "..*..." // V
                });

                bigChar.put('W', new String[]{
                        "*...*.",
                        "*...*.",
                        "*.*.*.",
                        "**.**.",
                        "*...*." // W
                });

                bigChar.put('X', new String[]{
                        "*...*.",
                        ".*.*..",
                        "..*...",
                        ".*.*..",
                        "*...*." // X
                });

                bigChar.put('Y', new String[]{
                        "*...*.",
                        ".*.*..",
                        "..*...",
                        "..*...",
                        "..*..." // Y
                });

                bigChar.put('Z', new String[]{
                        "*****.",
                        "...*..",
                        "..*...",
                        ".*....",
                        "*****." // Z
                });

                bigChar.put(' ', new String[]{
                        "......",
                        "......",
                        "......",
                        "......",
                        "......" // empty
                });
            }

        }

        class Board {
            char[][] grid;

            Board() {
                grid = new char[60][60];
                initializeGrid();
            }

            private void initializeGrid() {
                for (int i = 0; i < 60; i++) {
                    for (int j = 0; j < 60; j++) {
                        grid[i][j] = '.';
                    }
                }
            }

            public void placeMsg(int row, int col, String message) {
                for (int i = col, j = 0; i < col + message.length(); i++, j++) {
                    if (message.charAt(j) != ' ' && !outOfBounds(row, i)) {
                        grid[row][i] = message.charAt(j);
                    }
                }
            }

            public void placeMsg(int row, int col, String[] message, boolean[] blankMap) {
                for (int i = row, j = 0; i < row + 5; i++, j++) {
                    for (int k = col, l = 0; k < col + message[j].length(); k++, l++) {
                        if (blankMap[l]) continue;
                        if (!outOfBounds(i, k) && message[j].charAt(l) != '.') {
                            grid[i][k] = message[j].charAt(l);
                        }
                    }
                }
            }

            private boolean outOfBounds(int row, int col) {
                boolean a = row > 59;
                boolean b = col < 0;
                boolean c = col > 59;
                return a || b || c;
            }

        }

        class Command {
            String command;
            String type;
            String font;
            int row;
            int col;
            boolean typeP;

            Command(String command) {
                this.command = command;
                assignArgs();
            }

            private void assignArgs() {
                int index = command.indexOf(' ');
                setType(index);
                index = command.indexOf(' ');
                setFont(index);
                index = command.indexOf(' ');
                setRow(index);
                index = typeP ? command.indexOf(' ') : -1;
                setCol(index);
                setCommand();
            }

            private void setCommand() {
                command = command.replace("|", "");
//            String temp = "";
//            for (int i = 0; i < command.length(); i++)
//                if (c5.bigChar.containsKey(command.charAt(i)))
//                    temp = temp + String.valueOf(command.charAt(i));
//            command = temp;
//            command = command.substring(0, command.lastIndexOf(" "));
            }

            private void setCol(int index) {
                int first = command.indexOf('|');
                int last = command.lastIndexOf('|');
                int size = command.substring(first, last).length() - 1; // [x,y)
                switch (type) {
                    case ".P":
                        col = Integer.valueOf(command.substring(0, index)) - 1;
                        break;
                    case ".L":
                        col = 0;
                        break;
                    case ".R":
                        col = (font.equals("C1")) ? 60 - size : 60 - size * 6;
                        break;
                    case ".C":
                        col = (font.equals("C1")) ? 60 / 2 - size / 2 : 60 / 2 - (size * 6) / 2;
                        break;
                    default:
                        break;
                }
                command = command.substring(index + 1);
            }

            private void setRow(int index) {
                row = Integer.valueOf(command.substring(0, index)) - 1;
                command = command.substring(index + 1);
            }

            private void setFont(int index) {
                font = command.substring(0, index);
                command = command.substring(index + 1);
            }

            private void setType(int index) {
                type = command.substring(0, index);
                command = command.substring(index + 1);
                typeP = type.equals(".P") ? true : false;
            }

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

    }
}

