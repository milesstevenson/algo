/*
ID: code.mi1
LANG: C++
TASK: milk2
*/
#include <fstream>
#include <vector>
#include <utility>
#include <algorithm>

using namespace std;

vector<pair<int,int> > times;

bool intersectMilking(int current, int finish) {
  bool premise1 = times[current].first <= finish;
  return premise1;
}

int main() {
  ifstream fin("milk2.in");
  ofstream fout("milk2.out");

  int n; fin >> n;
  for (int i = 0; i < n; i++) {
    int start, finish; fin >> start >> finish;
    times.push_back(make_pair(start,finish));
  }
  sort(times.begin(),times.end());

  int start = times[0].first, finish = times[0].second;
  int off, on, offmax = 0, onmax = finish - start;
  
  for (int i = 1; i < n; i++) {
     if (intersectMilking(i, finish)) {
       finish = max(finish, times[i].second);
       start = min(start, times[i].first);
       onmax = max(onmax, finish - start);
     }
     else {
       off = times[i].first - finish;
       offmax = max(offmax, off);
       start = times[i].first;
       finish = times[i].second;
       onmax = max(onmax, finish-start);
     }
  }
  
  fout << onmax << " " << offmax << endl;
  return 0;
}
