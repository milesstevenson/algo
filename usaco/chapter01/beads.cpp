/*
ID: code.mi1
LANG: C++
TASK: beads
*/
#include <fstream>
#include <iostream>
#include <string>
using namespace std;
string s; int n; bool eq;

void EQ(void) {
  eq = true;
  for (int i = 0; i < n; i++)
    if (s[i] != s[0] && s[i] != 'w') {
      eq = false;
      break;
    }
}

int main(void) {
  ifstream fin("beads.in");
  ofstream fout("beads.out");
  
  fin >> n >> s;

  EQ();
  if (eq) {
    fout << n << endl;
    return 0;
  }
  
  int max = 0;
  int lindex, rindex;
  int bluecount, redcount;
  char lchar, rchar;
  bool searching;

  // use a dividing line on every char of the string
  for (int div = 0; div < n; div++) {
    
    bluecount = redcount = 0;
    searching = true;
    lchar = s[div]; rchar = s[div+1];
    
    if (lchar == 'w')
      for (int left = div; lchar == 'w'; left--) {
	if (left == -1) left = n-1;
	lchar = s[left];
      }
    if (rchar == 'w')
      for (int right = div+1; rchar == 'w'; right++) {
	if (right == n) right = 0;
	rchar = s[right];
      }
    
    // search left side in a dumb way
    for (lindex = div; searching; lindex--) {
      if (lindex == -1) lindex = n-1;
      if (s[lindex] == lchar || s[lindex] == 'w')
	bluecount++;
      else
	searching = false;
    }
    
    searching = true;
    
    // search right side in a dumb way
    for (rindex = div+1; searching; rindex++) {
      if (rindex == n) rindex = 0;
      if (s[rindex] == rchar || s[rindex] == 'w')
	redcount++;
      else
	searching = false;
    }
    max = (bluecount + redcount > max) ? bluecount + redcount : max;
  }
  fout << max << endl;
  return 0;
}
