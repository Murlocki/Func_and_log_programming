%3.1.

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
min(X,Y,X):-!.

% min_index(+X:integer,+Y:integer,+X_index:integer,+Y_index:integer,-ResultIndex:integer)
% ResultIndex contains index of the smallest number of X and Y
min_index(X,Y,_,Y_index,ResultIndex):- min(X,Y,Y), ResultIndex is Y_index,!.
min_index(X,Y,X_index,_,ResultIndex):- min(X,Y,X), ResultIndex is X_index,!.

% min_elem_call(+InputList:List,+AvoidIndex:integer,-ResultIndex:integer)
% ResultIndex contains index of the smallest number of InputList with index not equal to AvoidIndex
min_elem_call([Head|[]],1,ResultIndex):- ResultIndex is -1,!.
min_elem_call([Head|[HeadTail|Tail]],1,ResultIndex):- min_elem(1,Tail,HeadTail,3,2,ResultIndex),!.
min_elem_call([Head|Tail],AvoidIndex,ResultIndex):- min_elem(AvoidIndex,Tail,Head,2,1,ResultIndex),!.

% min_elem(+AvoidIndex:integer,+InputList:List,+CurrentMin:integer,+CurrentIndex:integer,+CurrentResultIndex:integer,-ResultIndex:integer)
% ResultIndex contains index of the smallest number of InputList with index not equal to AvoidIndex
min_elem(_,[],_,_,ResultIndex,ResultIndex):-!.
min_elem(CurrentIndex,[Head|Tail],CurrentMin,CurrentIndex,CurrentResultIndex,ResultIndex):- NewCurrentIndex is CurrentIndex + 1,min_elem(-1,Tail,CurrentMin,NewCurrentIndex,CurrentResultIndex,ResultIndex),!. 
min_elem(AvoidIndex,[Head|Tail],CurrentMin,CurrentIndex,CurrentResultIndex,ResultIndex):- min(Head,CurrentMin,MinResult), min_index(Head,CurrentMin,CurrentIndex,CurrentResultIndex,NewResultIndex), NewCurrentIndex is CurrentIndex + 1,
min_elem(AvoidIndex,Tail,MinResult,NewCurrentIndex,NewResultIndex,ResultIndex). 

%main(+InputList:List,-ListOtver:List)
% Main predicate to calc ListOtvet of indexes of two smalles elements of InputList
% If length of InputList is 1, it contains indexes 1 and -1
main(InputList,ListOtvet):-min_elem_call(InputList,-1,FirstMinIndex),min_elem_call(InputList,FirstMinIndex,SecondMinIndex),concat([FirstMinIndex|[]],[SecondMinIndex|[]],ListOtvet),!.

%task3_call
% Main predicate to start calc of task 3
task3_call:-read_list(InputList),main(InputList,AnswersList),write('Indexes of two min elements'),nl,write_list(AnswersList).