% task 2

%2.1
% max(+FirstNumber:integer,+SecondNumber:integer,?MaximumNumber:integer) is det
max(FirstNumber,SecondNumber,FirstNumber):- FirstNumber>SecondNumber,!.
max(_,SecondNumber,SecondNumber).

% max_digit_up(+InputNumber:integer,?MaximumNumber:integer) is det
max_digit_up(0,0):-!.
max_digit_up(InputNumber,CurrentMaximumDigit):- NextInputNumber is InputNumber div 10, max_digit_up(NextInputNumber,CurrentResult),  NewDigit is InputNumber mod 10, max(NewDigit,CurrentResult,NewResult), CurrentMaximumDigit is NewResult.


% max_digit_call(+InputNumber:integer,?MaximumNumber:integer) is det
max_digit_call(InputNumber,MaximumNumber):-max_digit_down(InputNumber,0,MaximumNumber),!. 

% max_digit_down(+CurrentNumber:integer, +CurrentMaximumDigit:integer,+ResultMaximumDigit) is det
max_digit_down(0,ResultMaximumDigit,ResultMaximumDigit):-!.
max_digit_down(CurrentNumber,CurrentMaximumDigit,ResultMaximumDigit):- NewDigit is CurrentNumber mod 10, max(CurrentMaximumDigit,NewDigit,NewResultDigit), NewNumber is CurrentNumber div 10, max_digit_down(NewNumber,NewResultDigit,ResultMaximumDigit).

