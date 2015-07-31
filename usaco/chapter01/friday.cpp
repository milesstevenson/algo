/*
ID: code.mi1
PROG: friday
LANG: C++
*/
#include <fstream>
#include <iostream>
using namespace std;

int main(void) {
    ifstream fin("friday.in");
    ofstream fout("friday.out");

    int months[12] = {31,28,31,30,31,30,31,31,30,31,30,31};
    int days[7] = {0,0,0,0,0,0,0};
    int N, day, cur = 0;
    fin >> N;

    for (int i = 1900; i < 1900+N; ++i) {
        for (int j = 0; j < 12; ++j) {
            if (((i%4==0&&i%100!=0) || i%400==0) && j==1)
                day = 29;
            else
                day = months[j];
            for (int k = 0; k < day; ++k) {
               if (k == 12) {
                   ++days[cur%7];
               }
               ++cur;
            }
        }
    }

    fout << days[5] << " " << days[6] << " ";
    for (int i = 0; i < 5; i++) {
        fout << days[i];
        if (i!=4) fout << " ";
    }
    fout << endl;

    return 0;
}

