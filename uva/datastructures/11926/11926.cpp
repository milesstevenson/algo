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
using namespace std;

int main() {
  int n, m;
  cin >> n >> m;

  while (!(n == 0 && m == 0)) {
    int bits = 0;
    for (int i = 0; i < n; i++) {
      int start, end;
      cin >> start >> end;
      // do something

      for (int j = 0; j < m; j++) {
        int start, end, repetition_int;
        cin >> start >> end >> repetition_int;
        // do something
      }
    }
    cin >> n >> m;
  }
  return 0;
}
