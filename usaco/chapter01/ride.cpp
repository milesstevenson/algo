/*
ID: code.mi1
PROG: ride
LANG: C++
*/

#include <string>
#include <fstream>
#include <iostream>

using namespace std;

int main (void) {
    ifstream fin("ride.in");
    ofstream fout("ride.out");
    
    string comet, group;
    fin >> comet >> group;
    
    int c, g;
    c = g = 1;

    for (int i = 0; i < comet.size(); ++i) 
        c *= comet[i]-'A' + 1;

    for (int i = 0; i < group.size(); ++i)
        g *= group[i]-'A' + 1;
    

    if ((g % 47) == (c % 47))
        fout << "GO\n";
    else
        fout << "STAY\n";

    return 0;
}
