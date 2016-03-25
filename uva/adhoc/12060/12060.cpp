#include <cstdio>
#include <cmath>
#include <string>
#include <iostream>
#include <iomanip>

using namespace std;

inline int gcd(int a, int b)
{
  return b ? gcd(b, a % b) : a;
}

int main()
{
  int a, b, c, n, k = 1;

  // loop until EOF or 0 encountered
  while (scanf("%d", &n) != EOF) {
    if (n == 0)
      break;

    // sum of all values
    int sum = 0, a_i = 0;
    for (int i = 0; i < n; i++) {
      scanf("%d", &a_i);
      sum += a_i;
    }

    // initial a b c values
    a = sum / n;
    b = abs(sum % n);
    c = n;

    // determine if we have a negative value or not
    bool neg = sum < 0 ? true : false;
    
    int g = gcd(b,c);

    // compute final b and c values
    b = b / g;
    c = c / g;

    // convert b and c to string
    string whole = a != 0 ? to_string(abs(a)) : "";
    string num = b > 0 ? to_string(b) : "";
    string denom = b > 0 ? to_string(c) : "";
    int line_len = denom.length();

    // generate entire dividing bar
    string barr = !neg == 0 ? "- " : "";
    if (whole.length() > 0)
      barr.append(whole);
    if (b > 0)
      for (int i = 0; i < line_len; i++)
	barr.append("-");


    // output the results
    printf("Case %d:\n", k++);
    if (a == 0 && b == 0) {
      cout << "0" << endl;
    }
    else {
      if (num.length() > 0)
	cout << setw(barr.length()) << right << num << endl;
      cout << barr << endl;
      if (num.length() > 0)
	cout << setw(barr.length()) << right << denom << endl;
    }
  }
  return 0;
}
