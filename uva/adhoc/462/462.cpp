#include <iostream>
#include <fstream>
#include <map>
#include <string>
#define SSTART 0
#define HSTART 13
#define DSTART 26
#define CSTART 39
using namespace std;


int deck[52];
map<char, int> pos;

void init() {
  for (int i = 0; i < 52; ++i)
    deck[i] = 0;
  
  pos['S'] = SSTART;
  pos['H'] = HSTART;
  pos['D'] = DSTART;
  pos['C'] = CSTART;
  pos['2'] = 0;
  pos['3'] = 1;
  pos['4'] = 2;
  pos['5'] = 3;
  pos['6'] = 4;
  pos['7'] = 5;
  pos['8'] = 6;
  pos['9'] = 7;
  pos['T'] = 8;
  pos['J'] = 9;
  pos['Q'] = 10;
  pos['K'] = 11;
  pos['A'] = 12;
}

void map_card(char rank, char suit) {
  int index = pos[suit] + pos[rank];
  deck[index] = 1;
}

int rule_one() {
  int points = 0;
  points += (deck[pos['D'] + pos['A']]*4);
  points += (deck[pos['C'] + pos['A']]*4);
  points += (deck[pos['H'] + pos['A']]*4);
  points += (deck[pos['S'] + pos['A']]*4);

  points += (deck[pos['D'] + pos['K']]*3);
  points += (deck[pos['C'] + pos['K']]*3);
  points += (deck[pos['H'] + pos['K']]*3);
  points += (deck[pos['S'] + pos['K']]*3);

  points += (deck[pos['D'] + pos['Q']]*2);
  points += (deck[pos['C'] + pos['Q']]*2);
  points += (deck[pos['H'] + pos['Q']]*2);
  points += (deck[pos['S'] + pos['Q']]*2);

  points += (deck[pos['D'] + pos['J']]);
  points += (deck[pos['C'] + pos['J']]);
  points += (deck[pos['H'] + pos['J']]);
  points += (deck[pos['S'] + pos['J']]);

  return points;
}

int helper_two(int start, int end, int king)
{
  int points = 0;
  int length = 0;
  for (int i = start; i < end; ++i)
    if (deck[i] == 1)
      ++length;
  if (length == 1 && deck[king] == 1)
    points--;
  return points;
}
      

int rule_two() {
  int points = 0;
  int dking = pos['D'] + pos['K'];
  int cking = pos['C'] + pos['K'];
  int hking = pos['H'] + pos['K'];
  int sking = pos['S'] + pos['K'];
  
  points += helper_two(SSTART,HSTART,sking);
  points += helper_two(HSTART,DSTART,hking);
  points += helper_two(DSTART,CSTART,dking);
  points += helper_two(CSTART,52,cking);

  return points;
}

int helper_three(int start, int end, int queen) {
  int points = 0;
  int length = 0;
  
  for (int i = start; i < end; ++i)
    if (deck[i] == 1)
      length++;
  if (length <= 2 && deck[queen] == 1)
    points--;
  return points;
}

int rule_three() {
  int points = 0;
  int dking = pos['D'] + pos['Q'];
  int cking = pos['C'] + pos['Q'];
  int hking = pos['H'] + pos['Q'];
  int sking = pos['S'] + pos['Q'];

  points += helper_three(SSTART,HSTART,sking);
  points += helper_three(HSTART,DSTART,hking);
  points += helper_three(DSTART,CSTART,dking);
  points += helper_three(CSTART,52,cking);
  
  return points;
}

int helper_four(int start, int end, int jack) {
  int points = 0;
  int length = 0;
  
  for (int i = start; i < end; ++i)
    if (deck[i] == 1)
      length++;
  if (length <= 3 && deck[jack] == 1)
    points--;
  return points;
}

int rule_four() {
  int points = 0;
  int dking = pos['D'] + pos['J'];
  int cking = pos['C'] + pos['J'];
  int hking = pos['H'] + pos['J'];
  int sking = pos['S'] + pos['J'];

  points += helper_four(SSTART,HSTART,sking);
  points += helper_four(HSTART,DSTART,hking);
  points += helper_four(DSTART,CSTART,dking);
  points += helper_four(CSTART,52,cking);
  
  return points;
}

int helper_five(int start, int end) {
  int points = 0;
  int length = 0;
  
  for (int i = start; i < end; ++i)
    if (deck[i] == 1)
      length++;
  if (length == 2)
    points++;
  return points;
}

int rule_five() {
  int points = 0;

  points += helper_five(SSTART,HSTART);
  points += helper_five(HSTART,DSTART);
  points += helper_five(DSTART,CSTART);
  points += helper_five(CSTART,52);
  
  return points;
}

int helper_six(int start, int end) {
  int points = 0;
  int length = 0;
  
  for (int i = start; i < end; ++i)
    if (deck[i] == 1)
      length++;
  if (length == 1)
    points+=2;
  return points;
}

int rule_six() {
  int points = 0;

  points += helper_six(SSTART,HSTART);
  points += helper_six(HSTART,DSTART);
  points += helper_six(DSTART,CSTART);
  points += helper_six(CSTART,52);
  
  return points;
}

int helper_seven(int start, int end) {
  int points = 0;
  int length = 0;
  
  for (int i = start; i < end; ++i)
    if (deck[i] == 1)
      length++;
  if (length == 0)
    points+=2;
  return points;
}

int rule_seven() {
  int points = 0;

  points += helper_seven(SSTART,HSTART);
  points += helper_seven(HSTART,DSTART);
  points += helper_seven(DSTART,CSTART);
  points += helper_seven(CSTART,52);
  
  return points;
}

int rule(int n) {
  switch (n) {
  case 1:
    return rule_one();
  case 2:
    return rule_two();
  case 3:
    return rule_three();
  case 4:
    return rule_four();
  case 5:
    return rule_five();
  case 6:
    return rule_six();
  case 7:
    return rule_seven();
  default:
    return 0;
  }
}

int compute_points() {
  int points = 0;
  for (int i = 0; i < 7; ++i)
    points += rule(i+1);
  
  return points;
}

int no_trump() {
  int points = 0;
  for (int i = 0; i < 4; ++i)
    points += rule(i+1);
  
  return points;
}

bool stopped_condition1(int start) {
  return deck[start + pos['A']];
}

bool stopped_condition2(int start, int end) {
  bool king = deck[start + pos['K']];
  int length = 0;
  for (int i = start; i < end; ++i)
    if (deck[i])
      length++;
  bool result = (king && (length > 1));
  return result;
}

bool stopped_condition3(int start, int end) {
  
  bool queen = deck[start + pos['Q']];
  int length = 0;
  for (int i = start; i < end; ++i)
    if (deck[i])
      length++;
  bool result = (queen && (length > 2));
  return result;
}

bool all_stopped() {
  bool c1 = stopped_condition1(SSTART);
  bool c2 = stopped_condition2(SSTART,HSTART);
  bool c3 = stopped_condition3(SSTART,HSTART);
  bool c_1 = c1||c2||c3;
  if (c_1 == false)
    return false;

  c1 = stopped_condition1(HSTART);
  c2 = stopped_condition2(HSTART,DSTART);
  c3 = stopped_condition3(HSTART,DSTART);
  bool c_2 = c1||c2||c3;
  if (c_2 == false)
    return false;
  
  c1 = stopped_condition1(DSTART);
  c2 = stopped_condition2(DSTART,CSTART);
  c3 = stopped_condition3(DSTART,CSTART);
  bool c_3 = c1||c2||c3;
  if (c_3 == false)
    return false;

  c1 = stopped_condition1(CSTART);
  c2 = stopped_condition2(CSTART,52);
  c3 = stopped_condition3(CSTART,52);
  bool c_4 = c1||c2||c3;
  if (c_4 == false)
    return false;

  return c_1 && c_2 && c_3 && c_4;
  // shdc
}

int suit_length(int start, int end) {
  int length = 0;
  for (int i = start; i < end; ++i)
    if (deck[i] == 1)
      ++length;
  return length;
}

char best_suit() {
  char suit = 'S';
  int count = suit_length(SSTART,HSTART);

  int temp = suit_length(HSTART,DSTART);
  if (count < temp) {
    suit = 'H';
    count = temp;
  }

  temp = suit_length(DSTART,CSTART);
  if (count < temp) {
    count = temp;
    suit = 'D';
  }

  temp = suit_length(CSTART,52);
  if(count < temp) {
    count = temp;
    suit = 'C';
  }

  return suit;
}
  
int main() {
  char r,s;

  while (cin >> r) {
    init();
    cin >> s;
    map_card(r,s);
    for (int i = 0; i < 12; ++i) {
      cin >> r; cin >> s;
      map_card(r,s);
    }
   int trump_points = no_trump();
   bool stopped = all_stopped();
   int points = compute_points();
   if (trump_points >= 16 && stopped)
      cout << "BID NO-TRUMP" << endl;
   else if (points >= 14) {
     char suit = best_suit();
       cout << "BID " << suit << endl;
   }
   else
     cout << "PASS" << endl;
  }
  
  return 0;
}
