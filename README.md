# Texas-Holdem-Java
Made by java, typical texas holdem game.


This is made to calculate winning probability for each starting hands.

There's a few conditions.

1. Number of players is 9.

2. All players never fold so all players show down.

3. All players never bet, never raise, always call.


So this calculates probability only in perfectly natural situation without any variables.


RESULTS:

1) 100,000 iterations

A2 : 12.3
A3 : 12.5
A4 : 13.0
A5 : 14.2
A6 : 14.1
A7 : 14.1
A8 : 14.3
A9 : 15.9
A7s : 19.2

AJ : 17.8
AK : 20.4
AQ : 19.6
AT : 16.1
A8s : 19.3
AQs : 22.0
A9s : 19.2
AJs : 21.4
A2s : 17.2
AKs : 23.6
ATs : 21.1
A3s : 16.5
A4s : 18.1
A5s : 17.4
A6s : 18.3
AA : 39.2

K2s : 13.8
K3s : 15.9
KTs : 17.5
K2 : 10.4
K3 : 10.9
K4 : 11.5
K5 : 10.9
K4s : 15.0
K6 : 11.9
K7 : 12.1
K8 : 13.0
K9 : 14.0
KJ : 15.5
KK : 32.3
KQ : 16.8
K5s : 16.3
KT : 14.2
K6s : 16.1
K7s : 16.3
K8s : 18.6
KQs : 19.6
K9s : 18.2
KJs : 19.4


Q2 : 8.9
Q3 : 9.1
Q4 : 9.3
Q5 : 10.2
Q6 : 10.4
Q7 : 11.5
Q8 : 11.5
Q9 : 12.5
Q6s : 15.2
QJ : 13.9
QQ : 29.4
QT : 13.1
Q7s : 16.3
Q8s : 14.5
Q9s : 15.4
QJs : 18.8
Q2s : 14.1
Q3s : 13.7
QTs : 16.9
Q4s : 14.2
Q5s : 13.6


J8s : 14.6
J9s : 14.6
J2 : 8.1
J3 : 8.2
J4 : 8.2
J5 : 9.0
J6 : 9.6
J7 : 10.6
J8 : 10.8
J9 : 11.9
JJ : 26.0
JT : 12.3
J2s : 11.9
JTs : 16.2
J3s : 13.1
J4s : 12.0
J5s : 12.7
J6s : 11.4
J7s : 14.2


T4s : 11.8
T5s : 12.0
T6s : 13.1
T2 : 7.3
T3 : 7.5
T4 : 7.9
T5 : 8.4
T6 : 9.3
T7 : 10.0
T8 : 10.3
T9 : 11.0
T7s : 14.3
TT : 21.7
T8s : 13.7
T9s : 14.7
T2s : 11.0
T3s : 11.1


97s : 12.8
92 : 6.6
93 : 6.6
94 : 7.3
95 : 8.0
96 : 9.1
97 : 9.6
98 : 9.8
99 : 19.5
98s : 14.8
92s : 10.3
93s : 11.5
94s : 11.5
95s : 10.9
96s : 12.7


88 : 18.4
85s : 12.7
86s : 12.7
87s : 15.9
82s : 10.2
83s : 10.2
84s : 11.7
82 : 6.3
83 : 6.4
84 : 7.6
85 : 8.8
86 : 9.7
87 : 9.7


73s : 11.6
74s : 11.3
75s : 13.3
76s : 14.7
72 : 5.8
73 : 6.6
74 : 9.2
75 : 9.8
76 : 10.3
77 : 17.4
72s : 9.9


62s : 10.4
63s : 11.2
64s : 12.6
65s : 14.8
62 : 7.2
63 : 8.1
64 : 9.4
65 : 10.6
66 : 15.0


52s : 10.7
53s : 11.7
52 : 6.4
53 : 8.0
54 : 9.7
55 : 14.9
54s : 13.1


42 : 6.4
43 : 7.7
44 : 14.1
42s : 10.9
43s : 10.8


32 : 5.8
33 : 14.5
32s : 9.3


22 : 13.5


2) 1 million iterations

A2 : 12.0%	
A3 : 12.4%	
A4 : 13.0%	
A5 : 13.3%	
A6 : 13.7%	
A7 : 14.5%	
A8 : 14.8%	
A9 : 15.8%	
A7s : 18.4%	
AA : 39.8%	
AJ : 17.9%	
AK : 21.3%	
AQ : 19.1%	
AT : 16.8%	
A8s : 19.0%	
AQs : 23.1%	
A9s : 19.8%	
AJs : 21.5%	
A2s : 16.7%	
AKs : 24.6%	
ATs : 21.2%	
A3s : 16.8%	
A4s : 17.5%	
A5s : 17.3%	
A6s : 18.3%	


K2s : 14.5%	
K3s : 15.3%	
KTs : 18.3%	
K2 : 10.3%	
K3 : 10.9%	
K4 : 11.1%	
K5 : 11.4%	
K4s : 15.6%	
K6 : 11.9%	
K7 : 12.6%	
K8 : 12.9%	
K9 : 13.7%	
KJ : 15.5%	
KK : 33.5%	
KQ : 17.2%	
K5s : 15.8%	
KT : 14.3%	
K6s : 16.2%	
K7s : 16.2%	
K8s : 16.8%	
KQs : 20.6%	
K9s : 17.3%	
KJs : 19.3%	


Q2 : 9.0%	
Q3 : 9.3%	
Q4 : 9.7%	
Q5 : 10.2%	
Q6 : 10.5%	
Q7 : 10.9%	
Q8 : 11.5%	
Q9 : 12.4%	
Q6s : 14.8%	
QJ : 14.0%	
QQ : 28.4%	
QT : 13.2%	
Q7s : 14.9%	
Q8s : 16.1%	
Q9s : 16.5%	
QJs : 17.7%	
Q2s : 13.0%	
Q3s : 13.1%	
QTs : 16.9%	
Q4s : 13.7%	
Q5s : 14.6%	


J8s : 14.6%	
J9s : 15.5%	
J2 : 8.0%	
J3 : 8.3%	
J4 : 8.7%	
J5 : 9.1%	
J6 : 9.5%	
J7 : 10.3%	
J8 : 10.6%	
J9 : 11.3%	
JJ : 25.1%	
J2s : 12.1%	
JT : 12.3%	
JTs : 15.7%	
J3s : 12.5%	
J4s : 12.6%	
J5s : 13.0%	
J6s : 13.8%	
J7s : 14.0%	


T4s : 11.8%	
T5s : 12.6%	
T6s : 12.8%	
T2 : 6.9%	
T3 : 7.5%	
T4 : 7.7%	
T5 : 8.4%	
T6 : 9.5%	
T7 : 10.0%	
T8 : 10.4%	
T9 : 11.1%	
T7s : 13.6%	
TT : 22.1%	
T8s : 14.0%	
T9s : 14.6%	
T2s : 11.2%	
T3s : 11.4%	


97s : 13.5
92 : 6.4
93 : 6.9
94 : 7.4
95 : 8.2
96 : 9.3
97 : 10.1
98 : 10.4
99 : 20.0
98s : 14.0
92s : 10.6
93s : 10.7
94s : 11.4
95s : 12.2
96s : 12.9


88 : 18.5
85s : 12.2
86s : 13.1
87s : 13.7
82s : 9.7
83s : 10.5
84s : 11.5
82 : 5.9
83 : 6.6
84 : 7.5
85 : 8.7
86 : 9.7
87 : 10.0


73s : 11.0
74s : 11.9
75s : 13.5
76s : 13.7
72 : 5.8
73 : 7.1
74 : 8.4
75 : 9.5
76 : 10.2
77 : 16.9
72s : 9.8


62s : 10.7
63s : 12.0
64s : 13.4
65s : 14.2
62 : 6.9
63 : 8.3
64 : 9.8
65 : 10.5
66 : 15.6


52s : 10.0
53s : 11.3
52 : 6.7
53 : 8.3
54 : 9.3
55 : 15.1
54s : 12.7


42 : 6.5
43 : 7.7
44 : 14.5
42s : 10.3
43s : 11.3


32 : 5.9
33 : 13.9
32s : 9.6


22 : 14.2
