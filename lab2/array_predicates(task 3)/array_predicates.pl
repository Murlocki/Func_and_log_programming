

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

%check_values(+InputList:List,+InputInterval:List,-Result:List
% Result contains List of InputList elements which fit InputInterval
check_values([],_,[]):-!.
check_values([Head|Tail],[Start|End],[Head|PrevResult]):-check_values(Tail,[Start|End],PrevResult),Head>=Start,Head=<End.
check_values([Head|Tail],[Start|End],PrevResult):-check_values(Tail,[Start|End],PrevResult).

%main_34(+InputList:List,+Interval:List,-ListOtvet:List)
% Main work predicate for task 34
main_34(InputList,Interval,ListOtvet):-check_values(InputList,Interval,ListOtvet),!.

%task_34
%Main predicate for task 34
task_34:-read_34(InputList,Interval),main_34(InputList,Interval,ListOtvet),write('Elemnts in your interval'),nl,write_list(ListOtvet),!.
