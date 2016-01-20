#include <cstdio>
#include <cmath>
using namespace std;

int main () {
  float H, M;
  for (;;) {
    scanf("%f:%f", &H, &M);
    if (H == 0 && M == 0)
      break;
    if (H == 12)
      H = 0;
    float mangle = M * 6;
    float hangle = (H*6*5) + (M/2.0);
    float degree = abs(hangle - mangle);
    
    printf("%.3f\n", (degree > 180) ? (360 - degree) : degree);
  }
  return 0;
}
