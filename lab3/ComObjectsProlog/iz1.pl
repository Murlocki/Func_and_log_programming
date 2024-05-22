
in_list([El|_],El).
in_list([Head|Tail],El):-in_list(Tail,El).


put_on_position(N,0,Integer,Result):- Result is N*10 + Integer,!.
put_on_position(N,Pos,Integer,Result):- NewN is N div 10,NewPos is Pos - 1,
    put_on_position(NewN,NewPos,Integer,PrevResult), Result is PrevResult * 10 + (N mod 10),!.

make_pos_list(-1,[]):-!.
make_pos_list(N,[N|PrevResult]):- NewN is N - 1, make_pos_list(NewN,PrevResult),!.

sum_digits(0,0):-!.
sum_digits(N,ResultSum):-NewN is N div 10, sum_digits(NewN,PrevSum),ResultSum is PrevSum + (N mod 10),!.

check_if_true(InputNumber,PrevNumber):- sum_digits(InputNumber,ResultSum), PrevNumber is InputNumber - ResultSum,!.

:-dynamic allNumbs/1.

getAllNumbs(InputNumber):-make_pos_list(3,PosList), make_pos_list(9,DigitsList),
    make_pos_list(36,SumList), in_list(PosList,Position),  in_list(DigitsList,Digit),
        put_on_position(InputNumber,Position,Digit,NewNumber), NewNumber >= 1000,
        in_list(SumList,Summa), NewNumber2 is NewNumber + Summa, check_if_true(NewNumber2,NewNumber),
        \+(allNumbs(NewNumber2)), assert(allNumbs(NewNumber2)),write(NewNumber2),nl,fail,!.