% task 2

%2.1
% max(+FirstNumber:integer,+SecondNumber:integer,?MaximumNumber:integer)
max(FirstNumber,SecondNumber,FirstNumber):- FirstNumber>SecondNumber,!.
max(_,SecondNumber,SecondNumber).

% max_digit_up(+InputNumber:integer,?MaximumNumber:integer)
max_digit_up(0,0):-!.
max_digit_up(InputNumber,CurrentMaximumDigit):- NextInputNumber is InputNumber div 10, max_digit_up(NextInputNumber,CurrentResult),  NewDigit is InputNumber mod 10, max(NewDigit,CurrentResult,NewResult), CurrentMaximumDigit is NewResult.


% max_digit_call(+InputNumber:integer,?MaximumNumber:integer)
max_digit_call(InputNumber,MaximumNumber):-max_digit_down(InputNumber,0,MaximumNumber),!. 

% max_digit_down(+CurrentNumber:integer, +CurrentMaximumDigit:integer,+ResultMaximumDigit)
max_digit_down(0,ResultMaximumDigit,ResultMaximumDigit):-!.
max_digit_down(CurrentNumber,CurrentMaximumDigit,ResultMaximumDigit):- NewDigit is CurrentNumber mod 10, max(CurrentMaximumDigit,NewDigit,NewResultDigit), NewNumber is CurrentNumber div 10, max_digit_down(NewNumber,NewResultDigit,ResultMaximumDigit).


%2.2
% multiplt_of_three(+InputNumber:integer, ?Result:Integer)
multiplt_of_three(InputNumber,Result):-0 is InputNumber mod 3, Result is 1,!.
multiplt_of_three(_,Result):-Result is 0,!.

%mult_of_3_sum_up(+InputNumber:integer,?ResultSum:integer)
mult_of_3_sum_up(0,0):-!.
mult_of_3_sum_up(InputNumber,ResultSum):-NewInputNumber is InputNumber div 10, mult_of_3_sum_up(NewInputNumber,PrevResult), NewDigit is InputNumber mod 10, multiplt_of_three(NewDigit,MultResult), ResultSum is PrevResult + NewDigit * MultResult.

%mult_of_3_sum_call(+InputNumber:integer,?ResultSum:integer)
mult_of_3_sum_call(InputNumber,ResultSum):-mult_of_3_sum_down(InputNumber,0,ResultSum),!.

%mult_of_3_sum_down(+InputNumber:integer,+CurrentSum:integer,+ResultSum:integer)
mult_of_3_sum_down(0,ResultSum,ResultSum):-!.
mult_of_3_sum_down(InputNumber,CurrentSum,ResultSum):-NewDigit is InputNumber mod 10, multiplt_of_three(NewDigit,MultResult), NewResultSum is CurrentSum + NewDigit * MultResult, NewInputNumber is InputNumber div 10,mult_of_3_sum_down(NewInputNumber,NewResultSum,ResultSum).
