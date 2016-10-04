#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main(void) {
  int n;
  while (cin >> n) {

    // load up the weights
    vector<int> weights;
    for (int corner = 0; corner < (1 << n); corner++) {
      int weight;
      cin >> weight;
      weights.push_back(weight);
    }

    // compute each corner's potency
    vector<int> potencies;
    for (int corner = 0; corner < (1 << n); corner++) {
      int potency = 0;
      for (int adj = 0; adj < n; adj++) {
        // potency of the corner is the sum of weights
        // of all neighbouring corners
        potency += weights[corner ^ (1 << adj)];
      }
      potencies.push_back(potency);
    }

    int maxSum = 0;

    // find max potencies sum
    for (int corner = 0; corner < (1 << n); corner++) {
      // you are to determine two neighbouring corners that
      // have the maximum sum of potencies and to output this sum
      for (int adj = 0; adj < n; adj++) {
        maxSum = max(maxSum, potencies[corner] + potencies[corner ^ (1 << adj)]);
      }
    }

    cout << maxSum << endl;
  }
  return 0;
}
