% task 2

%2.1
% max(+FirstNumber:integer,+SecondNumber:integer,?MaximumNumber:integer)
max(FirstNumber,SecondNumber,FirstNumber):- FirstNumber>SecondNumber,!.
max(_,SecondNumber,SecondNumber).

% max_digit_up(+InputNumber:integer,-MaximumNumber:integer)
max_digit_up(0,0):-!.
max_digit_up(InputNumber,CurrentMaximumDigit):- NextInputNumber is InputNumber div 10, max_digit_up(NextInputNumber,CurrentResult),  NewDigit is InputNumber mod 10, max(NewDigit,CurrentResult,NewResult), CurrentMaximumDigit is NewResult.


% max_digit_call(+InputNumber:integer,-MaximumNumber:integer)
max_digit_call(InputNumber,MaximumNumber):-max_digit_down(InputNumber,0,MaximumNumber),!. 

% max_digit_down(+CurrentNumber:integer, +CurrentMaximumDigit:integer,-ResultMaximumDigit)
max_digit_down(0,ResultMaximumDigit,ResultMaximumDigit):-!.
max_digit_down(CurrentNumber,CurrentMaximumDigit,ResultMaximumDigit):- NewDigit is CurrentNumber mod 10, max(CurrentMaximumDigit,NewDigit,NewResultDigit), NewNumber is CurrentNumber div 10, max_digit_down(NewNumber,NewResultDigit,ResultMaximumDigit).


%2.2
% multiplt_of_three(+InputNumber:integer, -Result:Integer)
multiplt_of_three(InputNumber,Result):-0 is InputNumber mod 3, Result is 1,!.
multiplt_of_three(_,Result):-Result is 0,!.

%mult_of_3_sum_up(+InputNumber:integer,-ResultSum:integer)
mult_of_3_sum_up(0,0):-!.
mult_of_3_sum_up(InputNumber,ResultSum):-NewInputNumber is InputNumber div 10, mult_of_3_sum_up(NewInputNumber,PrevResult), NewDigit is InputNumber mod 10, multiplt_of_three(NewDigit,MultResult), ResultSum is PrevResult + NewDigit * MultResult.

%mult_of_3_sum_call(+InputNumber:integer,-ResultSum:integer)
mult_of_3_sum_call(InputNumber,ResultSum):-mult_of_3_sum_down(InputNumber,0,ResultSum),!.

%mult_of_3_sum_down(+InputNumber:integer,+CurrentSum:integer,-ResultSum:integer)
mult_of_3_sum_down(0,ResultSum,ResultSum):-!.
mult_of_3_sum_down(InputNumber,CurrentSum,ResultSum):-NewDigit is InputNumber mod 10, multiplt_of_three(NewDigit,MultResult), NewResultSum is CurrentSum + NewDigit * MultResult, NewInputNumber is InputNumber div 10,mult_of_3_sum_down(NewInputNumber,NewResultSum,ResultSum).

%2.3
%multiplt_of(+InputNumber:integer,+PossibleDel:integer,-Result:integer)
multiplt_of(InputNumber,PossibleDel,Result):-0 is InputNumber mod PossibleDel, Result is 1,!.
multiplt_of(_,_,Result):-Result is 0,!.

% count_of_del_up_call(+InputNumber:integer,-CountOfDel:integer)
count_of_del_up_call(InputNumber,CountOfDel):-count_of_del_up(InputNumber,InputNumber,CountOfDel).

% count_of_del_up_call(+InputNumber:integer,+CurrentNumber:integer,-ResultCount:integer)
count_of_del_up(_,1,1):-!.
count_of_del_up(InputNumber,CurrentNumber,ResultCount):-NewCurrentNumber is CurrentNumber - 1, count_of_del_up(InputNumber,NewCurrentNumber,PrevResult),  multiplt_of(InputNumber,CurrentNumber,IsDel), ResultCount is PrevResult + IsDel.


% count_of_del_down_call(+InputNumber:integer,-ResultCount:integer)
count_of_del_down_call(InputNumber,ResultCount):-count_of_del_down(InputNumber,InputNumber,0,ResultCount).

% count_of_del_down_call(+InputNumber:integer,+CurrentNumber:integer,+CountOfDel:integer,-ResultCount:integer)
count_of_del_down(_,0,ResultCount,ResultCount):-!.
count_of_del_down(InputNumber,CurrentNumber,CountOfDel,ResultCount):-NewCurrentNumber is CurrentNumber - 1,  multiplt_of(InputNumber,CurrentNumber,IsDel), NewCountOfDel is CountOfDel + IsDel, count_of_del_down(InputNumber,NewCurrentNumber,NewCountOfDel,ResultCount).
