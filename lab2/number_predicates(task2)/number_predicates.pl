% task 2

%2.1
% max(+FirstNumber:integer,+SecondNumber:integer,?MaximumNumber:integer) is det
max(FirstNumber,SecondNumber,FirstNumber):- FirstNumber>SecondNumber,!.
max(_,SecondNumber,SecondNumber).

% max_digit_up(+InputNumber:integer,?MaximumNumber:integer) is det
max_digit_up(0,0):-!.
max_digit_up(InputNumber,CurrentMaximumNumber):- NextInputNumber is InputNumber div 10, max_digit_up(NextInputNumber,CurrentResult),  NewDigit is InputNumber mod 10, max(NewDigit,CurrentResult,NewResult), CurrentMaximumNumber is NewResult.

