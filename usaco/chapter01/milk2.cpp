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

bool disjointMilking(int current,  int finish) {
  bool premise1 = times[current].first > finish;
  bool premise2 = times[current].second > finish;
  return premise1 && premise2;
}

bool extendMilking(int current, int finish) {
  bool premise1 = times[current].second > finish;
  bool premise2 = times[current].first <= finish;
  return premise1 && premise2;
}

bool subsetMilking(int current, int finish) {
  bool premise1 = times[current].second < finish;
  bool premise2 = times[current].first < finish;
  return premise1 && premise2;
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
    // check if there is spare time between (i-1).finish and i.start
    // this allows us to extend maximum down time
    if (disjointMilking(i, finish)) {
      off = times[i].first - finish;
      offmax = max(offmax, off);
      start = times[i].first;
      finish = times[i].second;
      onmax = max(onmax, finish-start);
    }
    // check if the intersection between current milking and past
    // milking allows us to extend maximum milking time
    else if (extendMilking(i, finish)) {
      finish = times[i].second;
      onmax = finish - start;
    }
    // inception (subset) milking... doesn't matter
    // do nothing
    else if (subsetMilking(i, finish))
      continue;
  }
 
  fout << onmax << " " << offmax << endl;
  return 0;
}
