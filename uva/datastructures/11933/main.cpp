#include <iostream>
using namespace std;

int main (void) {
  int n;
  
  while (true) {
    cin >> n;
    if (n == 0)
      break;
    int bi = 0;
    int a = 0, b = 0;
    
    for (int i = 0; n != 0; i++) {
      int m = n & (1 << i);
      if (m) {
	bi++;
	if (bi % 2)
	  a |= m;
	else
	  b |= m;
      }
      n &= ~(1 << i);
    }
    cout << a << " " << b << endl;
  }
}
