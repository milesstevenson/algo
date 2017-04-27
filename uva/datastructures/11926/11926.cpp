// The job is to make sure that a calendar is free of conflicts for the next
  // one million minutes.
  //
  // In this calendar, there are two types of tasks: one-time tasks and
  // repeating tasks.
  //
  // One-time tasks have a start time and an end time.
  // Repeating tasks have a start time and an end time for their first occurance,
  // and a repetition interval. Repeating tasks are assumed to keep repeating
  // forever without end.
  //
  // A repeating task with stat time 5, and end time 8 and repetition interval 100
  // would be occuring at time intervals [5..8], [105..108], [205..208], ...
  //
  // Tasks are considered to be in conflict if and only if their time intervals
  // overlap, for example [2..5] and [4..6] overlap.

  // Input:
  // There are approx. 30 test cases. The first line of each test case contains
  // two numbers n and m.
  //
  // n is the number of one-time tasks and m the number of repeating tasks. The
  // following n lines contain two numbers each: the start and end times of a
  // one-time task.
  //
  // Afterward, m more lines similarly describe the repeating tasks by giving
  // their start times, end times, and repetition intervals.
  //
  // Both n and m are at most 100.
  //
  // All numbers are integers in the range [0..1000000]. For each task, the end
  // time is guaranteed to be larger than the start time, and the repetition
  // interval is larger than 0.

  // Output:
  // For each test case, print a single line containing either the words
  // 'NO CONFLICT' if there are no overlaps between any tasks for minutes
  // 0..1000000, or 'CONFLICT' if there is at least one overlap.
#include <iostream>
#include <bitset>
using namespace std;


bitset<1000001> range;

bool collisionsFoundNoRep(int n) {
  int start, end;
  bool collideFound = false;

  for (int i = 0; i < n; i++) {
    cin >> start >> end;
    if (!collideFound) {
      for (int j = start; j <= end; j++) {
        if (j != end) {
          bool collides = range.test(j);
          if (collides) {
            collideFound = true;
          }
          else {
            range.set(j);
          }
        }
      }
    }
  }

  return collideFound;
}

bool collisionsFoundRep(int m) {
  int start, end, rep;
  bool collideFound = false;

  for (int i = 0; i < m; i++) {
    cin >> start >> end >> rep;
    int diff = end - start;
    int j = start;
    if (!collideFound) {
      while (j <= 1000000) {
        if (j > end) {
          start += rep;
          end = start + diff;
          j = start;
          continue;
        }

        if (j != end) {
          bool collides = range.test(j);
          if (collides) {
            collideFound = true;
          }
          else {
            range.set(j);
          }
        }
        j++;
      }
    }
  }
  return collideFound;
}

int main(void) {
  int n, m;
  while (cin >> n >> m, (n != 0 || m != 0)) {
    //cout << "testing: " << n << " and " << m << endl;
    bool collidesNoRep = collisionsFoundNoRep(n);
    bool collidesRep = collisionsFoundRep(m);
    bool collides = collidesNoRep || collidesRep;

    if (collides) {
      cout << "CONFLICT" << endl;
    }
    else {
      cout << "NO CONFLICT" << endl;
    }
    range.reset();
  }
}
