#include <iostream>
#include <string>
#include <list>
#include <iterator>

using namespace std;

int main() {
  string line;
 list<char> vec;

  while (getline(cin, line)) {
    auto it = vec.begin();
    
    for (int i = 0; i < line.size(); i++) {
      char ch = line[i];
      if (ch == '[')
	it = vec.begin();
      else if (ch  == ']')
	it = vec.end();
      else {
	it = vec.emplace(it, ch);
	it = next(it, 1);
      }	
    }

    for (auto& x: vec)
      cout << x;
    cout << endl;

    vec.clear();
  }
  return 0;
}
