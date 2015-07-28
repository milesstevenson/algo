/*
ID: code.mi1
PROG: gift1
LANG: C++
*/

#include <fstream>
#include <iostream>
#include <string>
#include <map>

using namespace std;

int main(void) {
    ifstream fin("gift1.in");
    ofstream fout("gift1.out");

    int NP;
    fin >> NP;
    string group[NP];

    map<string,int> give, receive;
    for (int i = 0; i < NP; ++i) {
        fin >> group[i];
        give[group[i]] = 0; receive[group[i]] = 0;
    }
    
    for (int i = 0; i < NP; ++i) {
        string name; 
        int init, size;
        fin >> name >> init >> size;
        give[name] = init;
         
        int portion = ((size > 0) ? init / size : 0);
        int leftover = ((init > 0) ?  init - (portion*size) : init);

        receive[name] += leftover;
        for (int j = 0; j < size; ++j) {
            string receiver;
            fin >> receiver;
            receive[receiver] += portion;
        }
    }

    for (int i = 0; i < NP; ++i)
        fout << group[i] << " " << (receive[group[i]] - give[group[i]]) << endl;
    return 0;
}
