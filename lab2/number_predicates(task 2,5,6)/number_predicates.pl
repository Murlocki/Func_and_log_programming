% task 2

%2.1
% max(+FirstNumber:integer,+SecondNumber:integer,-MaximumNumber:integer)
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

%task 5.8
% min(+X:integer,+Y:integer,-Result:integer)
% Result contains the smallest number of X and Y
min(X,Y,Y):- X>Y,!.
min(X,_,X):-!.



%task 5.8.
%vs_simple(+X:integer,+Y:integer,-Result:integer)
%Contains 1 in Result if X and Y are Coprime integers
%Otherwise contains 0 in Result
vs_simple(X,Y,1):-max(X,Y,ResultMax),min(X,Y,ResultMin),vs_simple_rec(ResultMax,ResultMin).
vs_simple(_,_,0):-!.

%vs_simple_rec(+X:integer,+Y:integer)
% True if X and Y are Coprime integers
vs_simple_rec(_,1):-!,true.
vs_simple_rec(X,Y):-0 is X mod Y,!,fail.
vs_simple_rec(X,Y):-NewY is X mod Y, NewX is Y, vs_simple_rec(NewX,NewY).

%check_simple_count(+N:integer,+Del:integer,-Count:integer)
%Count contains count of digits of N which are Coprime integers with Del
check_simple_count(0,_,0):- !.
check_simple_count(N,Del,Count):- NewN is N div 10, check_simple_count(NewN,Del,PrevCount),NewDigit is N mod 10,vs_simple(NewDigit,Del,Result),Count is PrevCount + Result,!.

%chose_del(+PrevCountMax:integer,+CurrentCount:integer,+CurrentDel:integer,+PrevResultDel:integer,-ResultDel:integer)
%ResultDel contains max del based on max between PrevCountMax and CurrentCount
chose_del(CurrentCount,CurrentCount,_,PrevResultDel,PrevResultDel):-!.
chose_del(PrevCountMax,CurrentCount,_,PrevResultDel,ResultDel):-PrevCountMax>CurrentCount,ResultDel is PrevResultDel.
chose_del(_,_,CurrentDel,_,ResultDel):-ResultDel is CurrentDel,!.

%get_del_down(+N:integer,+CurrentDel:integer,+CurrentMaxCount:integer,+CurrentMaxDel:integer,-ResultDel:integer)
%ResultDel contains max del of N based on count of digits of N which are Coprime Integers with this del
get_del_down(_,1,_,ResultDel,ResultDel):-!.
get_del_down(N,CurrentDel,CurrentMaxCount,CurrentMaxDel,ResultDel):- 0 is N mod CurrentDel,check_simple_count(N,CurrentDel,NewCount),
    chose_del(CurrentMaxCount,NewCount,CurrentDel,CurrentMaxDel,NewMaxDel),
    max(CurrentMaxCount,NewCount,NewMaxCount),
    NewCurrentDel is CurrentDel - 1, get_del_down(N,NewCurrentDel,NewMaxCount,NewMaxDel,ResultDel).
get_del_down(N,CurrentDel,CurrentMaxCount,CurrentMaxDel,ResultDel):-
    NewCurrentDel is CurrentDel - 1, get_del_down(N,NewCurrentDel,CurrentMaxCount,CurrentMaxDel,ResultDel).


%get_del(+N:integer,-Del:integer)
%Del contains max del of N based on count of digits of N which are Coprime Integers with this del
get_del(N,Del):-get_del_down(N,N,0,0,Del),!.

%read5_8(-InputNumber:integer)
%InputNumber contains inputed number
read5_8(InputNumber):-read(InputNumber),!.

%task5_8
%main predicate for task 5.8
task5_8:- read5_8(InputNumber),get_del(InputNumber,ResultDel),write(ResultDel),!.


%task6_8
%check_if_simple(+N:integer,+CurrentDel:integer)
%True if N is simple integer
check_if_simple(1,_):-!,fail.
check_if_simple(N,_):-simple(N),!.
check_if_simple(N,1):-assert(simple(N)),!.
check_if_simple(N,CurrentDel):-0 is N mod CurrentDel,!,fail.
check_if_simple(N,CurrentDel):-NewCurrentDel is CurrentDel - 1,check_if_simple(N,NewCurrentDel).

:-dynamic simpleToLeft/1.
:-dynamic simpleToRight/1.
:-dynamic simple/1.

%cut_right(+N:integer)
%True if N is always simple while it cuts on the right side
cut_right(2):-!.
cut_right(3):-!.
cut_right(5):-!.
cut_right(7):-!.
cut_right(N):-simpleToRight(N),!.
cut_right(N):-FirstDel is N div 2,check_if_simple(N,FirstDel),NewN is N div 10,cut_right(NewN),assert(simpleToRight(N)),!.

%get_ten(+X:integer,+CurrentRes:integer,-Result:integer)
%Result contains 10**y<X
get_ten(X,CurrentRes,Result):-X<CurrentRes, Result is CurrentRes div 10,!.
get_ten(X,CurrentRes,Result):-NewCurrentRes is CurrentRes * 10, get_ten(X,NewCurrentRes,Result).

%cut_left(+N:integer)
%True if N is always simple while it cuts on the left side
cut_left(2):-!.
cut_left(3):-!.
cut_left(5):-!.
cut_left(7):-!.
cut_left(N):-simpleToLeft(N),!.
cut_left(N):-FirstDel is N div 2,check_if_simple(N,FirstDel),get_ten(N,1,TenRes),NewN is N mod TenRes,cut_left(NewN),assert(simpleToLeft(N)),!.


%check_noteven_numbers(+InpN:integer,+CurrentN:integer,-UpdNumb:integer)
% UpdNumber contains number with replaced the first even digit in InpN beside first integer

check_noteven_numbers(InpN,CurrentN,UpdNumb):-CurrentN<10,1 is CurrentN mod 2,UpdNumb is 0,!.
check_noteven_numbers(InpN,InpN,UpdNumb):-get_ten(InpN,1,TenRes),FirstN is InpN div TenRes,(FirstN is 2;FirstN is 5),NewN is InpN mod TenRes,check_noteven_numbers(InpN,NewN,NewUpd),UpdNumb is FirstN*TenRes+NewUpd,!.
check_noteven_numbers(InpN,CurrentN,UpdNumb):-get_ten(CurrentN,1,TenRes),Digit is CurrentN div TenRes,0 is Digit mod 2,!,UpdNumb is (Digit + 1) * TenRes.
check_noteven_numbers(InpN,CurrentN,UpdNumb):-get_ten(CurrentN,1,TenRes),Digit is CurrentN div TenRes,Digit is 5,!,UpdNumb is (Digit + 1) * TenRes.
check_noteven_numbers(InpN,CurrentN,UpdNumb):-get_ten(CurrentN,1,TenRes),Digit is CurrentN  div (TenRes div 10),0 is Digit mod 2,!,UpdNumb is (Digit + 1) * (TenRes div 10).
check_noteven_numbers(InpN,CurrentN,UpdNumb):-get_ten(CurrentN,1,TenRes),NewN is CurrentN mod TenRes,check_noteven_numbers(InpN,NewN,PrevUpdNumb),UpdNumb is PrevUpdNumb + (CurrentN div TenRes) * TenRes.

%cut(+N:integer,+Sum:integer,-ResultSum:integer)
% ResultSum contains Sum of numbers which we can cut_left and cut_right
cut(1000001,ResultSum,ResultSum):-!.
cut(N,Sum,ResultSum):-check_noteven_numbers(N,N,UpdateNumber),max(N,UpdateNumber,ResNumb),cut_left(ResNumb),cut_right(ResNumb),NewSum is Sum + ResNumb,NewN is ResNumb + 2,write(ResNumb),nl, cut(NewN,NewSum,ResultSum).
cut(N,Sum,ResultSum):-check_noteven_numbers(N,N,UpdateNumber),max(N,UpdateNumber,ResNumb),write(ResNumb),nl,NewN is ResNumb + 2,cut(NewN,Sum,ResultSum).

%main(-ResultSum:integer)
% ResultSum contains Sum of numbers which we can cut_left and cut_right
main(ResultSum):-retractall(simpleToLeft(_)),retractall(simpleToRight(_)),retractall(simple(_)),cut(11,0,ResultSum),!.
