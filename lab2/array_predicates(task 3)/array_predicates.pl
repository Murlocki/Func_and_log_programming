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

