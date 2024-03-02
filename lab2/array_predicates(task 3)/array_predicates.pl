

% read_list(-InputList:List)
% InputList constains Inputed List
read_list([Head|Tail]):-read(X),X\==stop,Head is X,read_list(Tail).
read_list([]):-!.

% write_list(+InputList:List)
% Write all elements of InputList
write_list([]):-!.
write_list([Head|Tail]):-write(Head),nl,write_list(Tail).

% concat(+FirstList:List,+SecondList:List,?ResultList:List)
% ResultList contains concatination of FirstList and SecondList
concat([],ResultList,ResultList):-!.
concat([FirstListHead|FirstListTail],SecondList,[FirstListHead|Tail]):- concat(FirstListTail,SecondList,Tail).

% min(+X:integer,+Y:integer,-Result:integer)
% Result contains the smallest number of X and Y
min(X,Y,Y):- X>Y,!.
min(X,_,X):-!.


% max(+X:integer,+Y:integer,-Result:integer)
% Result contains the biggest number of X and Y
max(X,Y,Y):- X<Y,!.
max(X,_,X):-!.

%3.1.
% min_index(+X:integer,+Y:integer,+X_index:integer,+Y_index:integer,-ResultIndex:integer)
% ResultIndex contains index of the smallest number of X and Y
min_index(X,Y,_,Y_index,ResultIndex):- min(X,Y,Y), ResultIndex is Y_index,!.
min_index(X,Y,X_index,_,ResultIndex):- min(X,Y,X), ResultIndex is X_index,!.

% min_elem_call(+InputList:List,+AvoidIndex:integer,-ResultIndex:integer)
% ResultIndex contains index of the smallest number of InputList with index not equal to AvoidIndex
min_elem_call([_|[]],1,ResultIndex):- ResultIndex is -1,!.
min_elem_call([_|[HeadTail|Tail]],1,ResultIndex):- min_elem(1,Tail,HeadTail,3,2,ResultIndex),!.
min_elem_call([Head|Tail],AvoidIndex,ResultIndex):- min_elem(AvoidIndex,Tail,Head,2,1,ResultIndex),!.

% min_elem(+AvoidIndex:integer,+InputList:List,+CurrentMin:integer,+CurrentIndex:integer,+CurrentResultIndex:integer,-ResultIndex:integer)
% ResultIndex contains index of the smallest number of InputList with index not equal to AvoidIndex
min_elem(_,[],_,_,ResultIndex,ResultIndex):-!.
min_elem(CurrentIndex,[_|Tail],CurrentMin,CurrentIndex,CurrentResultIndex,ResultIndex):- NewCurrentIndex is CurrentIndex + 1,min_elem(-1,Tail,CurrentMin,NewCurrentIndex,CurrentResultIndex,ResultIndex),!.
min_elem(AvoidIndex,[Head|Tail],CurrentMin,CurrentIndex,CurrentResultIndex,ResultIndex):- min(Head,CurrentMin,MinResult), min_index(Head,CurrentMin,CurrentIndex,CurrentResultIndex,NewResultIndex), NewCurrentIndex is CurrentIndex + 1,
min_elem(AvoidIndex,Tail,MinResult,NewCurrentIndex,NewResultIndex,ResultIndex).

%main(+InputList:List,-ListOtver:List)
% Main predicate to calc ListOtvet of indexes of two smalles elements of InputList
% If length of InputList is 1, it contains indexes 1 and -1
main(InputList,ListOtvet):-min_elem_call(InputList,-1,FirstMinIndex),min_elem_call(InputList,FirstMinIndex,SecondMinIndex),concat([FirstMinIndex|[]],[SecondMinIndex|[]],ListOtvet),!.

%task3_8_call
% Main predicate to start calc of task 3
task3_8_call:-read_list(InputList),main(InputList,AnswersList),write('Indexes of two min elements'),nl,write_list(AnswersList).

%3.2.
% max_index(+X:integer,+Y:integer,+X_index:integer,+Y_index:integer,-ResultIndex:integer)
% ResultIndex contains index of the biggest number of X and Y
max_index(X,Y,_,Y_index,ResultIndex):- max(X,Y,Y), ResultIndex is Y_index,!.
max_index(X,Y,X_index,_,ResultIndex):- max(X,Y,X), ResultIndex is X_index,!.

% max_elem_call(+InputList:List-ResultIndex:integer)
% ResultIndex contains index of the biggest number of InputList
max_elem_call([Head|Tail],ResultIndex):- max_elem(Tail,Head,2,1,ResultIndex),!.

% max_elem(+InputList:List,+CurrentMax:integer,+CurrentIndex:integer,+CurrentResultIndex:integer,-ResultIndex:integer)
% ResultIndex contains index of the biggest number of InputList
max_elem([],_,_,ResultIndex,ResultIndex):-!.
max_elem([Head|Tail],CurrentMax,CurrentIndex,CurrentResultIndex,ResultIndex):- max(Head,CurrentMax,MaxResult), max_index(Head,CurrentMax,CurrentIndex,CurrentResultIndex,NewResultIndex), NewCurrentIndex is CurrentIndex + 1,
max_elem(Tail,MaxResult,NewCurrentIndex,NewResultIndex,ResultIndex).

% putElemOnIndex(+InputList:List,+IndexToPut:integer,+ElemToPut:integer,-ResultList:List)
% Return ResultList with ElemToPut on IndexToPut position
putElemOnIndex([_|Tail],IndexToPut,IndexToPut,ElemToPut,[ElemToPut|Tail]):-!.
putElemOnIndex([Head|Tail],CurrentIndex,IndexToPut,ElemToPut,[Head|NewResultList]):- NewIndex is CurrentIndex + 1, putElemOnIndex(Tail,NewIndex,IndexToPut,ElemToPut,NewResultList).


%findViaine(+InputList:List,+Index:Integer,-Elem:Integer)
% Return in Elem elements with index equal to Index
findViaInd(InputList,Index,Elem):- findViaIndRec(InputList,Index,Elem,1),!.

%findViaine(+InputList:List,+Index:Integer,-Elem:Integer,+CurrentInd:integer)
% Return in Elem elements with index equal to Index
findViaIndRec([Head|_],Index,Head,Index):-!.
findViaIndRec([_|Tail],Index,Elem,CurrentInd):- NewCurrentIndex is CurrentInd + 1, findViaIndRec(Tail,Index,Elem,NewCurrentIndex).

%main_2(+InputList:List,-ListOtvet:List)
% Return list in ListOtvet with switched max and min of this list
main_2(InputList,ListOtvet):- min_elem_call(InputList,-1,IndexMin),max_elem_call(InputList,IndexMax), findViaInd(InputList,IndexMin,MinElem),findViaInd(InputList,IndexMax,MaxElem), putElemOnIndex(InputList,1,IndexMin,MaxElem,ResultListMax),putElemOnIndex(ResultListMax,1,IndexMax,MinElem,ListOtvet),!.

%task3_17_call
% main predicate for task 3.17
task3_17_call:-read_list(InputList),main_2(InputList,AnswersList),write('List with switched elements'),nl,write_list(AnswersList).


%3.34
%read_interval(-Interval:List)
% Interval contains inputed interval of integers
read_interval([Start|End]):- read(Start),read(End),!.
%read_34(-InputList:List,-Interval:List)
% InputList contains inputed list and Interval contains inputed interval
read_34(InputList,Interval):- read_list(InputList),write('This was a list'),nl,read_interval(Interval),!.

%check_values(+InputList:List,+InputInterval:List,-Result:List)
% Result contains List of InputList elements which fit InputInterval
check_values([],_,[]):-!.
check_values([Head|Tail],[Start|End],[Head|PrevResult]):-check_values(Tail,[Start|End],PrevResult),Head>=Start,Head=<End.
check_values([_|Tail],[Start|End],PrevResult):-check_values(Tail,[Start|End],PrevResult).

%main_34(+InputList:List,+Interval:List,-ListOtvet:List)
% Main work predicate for task 34
main_34(InputList,Interval,ListOtvet):-check_values(InputList,Interval,ListOtvet),!.

%task_34
%Main predicate for task 34
task_34:-read_34(InputList,Interval),main_34(InputList,Interval,ListOtvet),write('Elemnts in your interval'),nl,write_list(ListOtvet),!.

%task 4
%in_list(?InputList:List,?El:integer)
%True if InputList contains El
% If InputList is unbound, create return InputList with El
% If El is unbound, start returning elements of InputList until . inputed
in_list([El|_],El).
in_list([_|Tail],El):-in_list(Tail,El).

%in_list1(+InputList:List,+El:integer)
%True if InputList contains El
in_list1([El|_],El)-!.
in_list1([_|Tail],El):-in_list1(Tail,El).

% pr_girlfriends
% write all girls shoes and dress colors
pr_girlfriends:-Girls = [_,_,_],
    in_list(Girls,[_,green,_]),
    in_list(Girls,[_,blue,_]),
    in_list(Girls,[_,white,_]),
    in_list(Girls,[_,_,green]),
    in_list(Girls,[_,_,blue]),
    in_list(Girls,[_,_,white]),
    in_list(Girls,[vali,_,_]),
    in_list(Girls,[anna,Color1,Color1]),
    in_list(Girls,[natasha,green,_]),
    not(in_list(Girls,[vali,white,_])),
    not(in_list(Girls,[vali,_,white])),

    not(in_list(Girls,[vali,Color2,Color2])),
    not(in_list(Girls,[natasha,Color3,Color3])),
    
    % Get and print results
    in_list(Girls,[vali,Shoes1,Dress1]),
    in_list(Girls,[anna,Shoes2,Dress2]),
    in_list(Girls,[natasha,Shoes3,Dress3]),
    write("vali:"),write(Shoes1),write(" "), write(Dress1),nl,
    write("anna:"),write(Shoes2),write(" ") ,write(Dress2),nl,
    write("natasha:"),write(Shoes3), write(" "),write(Dress3),nl,!.


% task 8.8
% pr_stud_from_mosc
% write students from moscow
pr_stud_from_mosc:- Students = [_,_,_,_,_],
    in_list(Students,[_,penza,_]),
    in_list(Students,[_,kharkov,_]),
    in_list(Students,[_,moscow,_]),

    in_list(Students,[_,_,lvov]),

    in_list(Students,[leonid,LeonidMotherland,_]),
    in_list(Students,[sergie,_,riga]),
    in_list(Students,[boris,riga,penza]),
    in_list(Students,[victor,_,moscow]),
    in_list(Students,[grigori,_,kharkov]),
    in_list(Students,[victor,lvov,_]),
    in_list(Students,[_,penza,LeonidMotherland]),

    not(in_list(Students,[_,City,City])),
    
    %Вывод студента из москвы
    in_list(Students,[Name,moscow,_]),
    write(Name),!.

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

