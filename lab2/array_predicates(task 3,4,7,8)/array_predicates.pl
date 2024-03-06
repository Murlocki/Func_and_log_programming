

% read_list(-InputList:List)
% InputList constains Inputed List
read_list([Head|Tail]):-read(X),X\==stop,Head is X,read_list(Tail).
read_list([]):-!.

% write_list(+InputList:List)
% Write all elements of InputList
write_list([]):-!.
write_list([Head|Tail]):-write(Head),nl,write_list(Tail).

% concat(+FirstList:List,+SecondList:List,-ResultList:List)
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


%findViaInd(+InputList:List,+Index:Integer,-Elem:Integer)
% Return in Elem elements with index equal to Index
findViaInd(InputList,Index,Elem):- findViaIndRec(InputList,Index,Elem,1),!.

%findViaIndRec(+InputList:List,+Index:Integer,-Elem:Integer,+CurrentInd:integer)
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
read_interval([NewStart|NewEnd]):- read(Start),read(End),min(Start,End,NewStart),max(Start,End,NewEnd),!.
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
in_list1([El|_],El):-!.
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


%7.38
%list_length(+InputList:List,-ResCount:integer)
%ResCount contatins length of InputList list
list_length([],0):-!.
list_length([_|Tail],ResCount):-list_length(Tail,PrevResCount),ResCount is PrevResCount + 1,!.

%main7_38(+InputList:List,+Interval:List,-ResultCount:Integer)
%ResultCount contains count of elements of InputList which fits Interval
main7_38(InputList,Interval,ResultCount):-main_34(InputList,Interval,ListOtvet),list_length(ListOtvet,ResultCount),!.

%task7_38
%main predicate for task 7.38
task7_38:-read_34(InputList,Interval),main7_38(InputList,Interval,ResultCount),write(ResultCount),!.

%task 7.46
%choose_negative_elements(+InputList:List,-ResultList:List)
%ResultList contains List of negative elements of InputList
choose_negative_elements([],[]):-!.
choose_negative_elements([Head|Tail],[Head|NewCurResList]):-choose_negative_elements(Tail,NewCurResList),Head<0,!.
choose_negative_elements([_|Tail],NewCurResList):-choose_negative_elements(Tail,NewCurResList),!.

%choose_positive_elements(+InputList:List,-ResultList:List)
%ResultList contains List of positive elements of InputList
choose_positive_elements([],[]):-!.
choose_positive_elements([Head|Tail],[Head|NewCurResList]):-choose_positive_elements(Tail,NewCurResList),Head>0,!.
choose_positive_elements([_|Tail],NewCurResList):-choose_positive_elements(Tail,NewCurResList),!.

%main(+InputList:List,-ResultList:List)
%ResultList contains list with all positive elements at the start and all negative elements at the end
main7_46(InputList,ResultList):-choose_negative_elements(InputList,NegativeList),choose_positive_elements(InputList,PositiveList),concat(PositiveList,NegativeList,ResultList),!.

%task7_46
%main predicate for task 7.46
task7_46:-read_list(InputList),main7_46(InputList,ResultList),write_list(ResultList),!.


% task 7.50
% del_element(+InputList:List,+El:Integer,-NewList:List)
% NewList contains InputList without El
del_element([],_,[]):-!.
del_element([El|Tail],El,Tail):-!.
del_element([Head|Tail],El,[Head|PrevResult]):-del_element(Tail,El,PrevResult),!.

%get_unique_elements(+InputList:List,-ResultList:List)
% ResultList contains all unique elements of InputList
get_unique_elements([],[]):-!.
get_unique_elements([Head|Tail],PrevResultList):-get_unique_elements(Tail,PrevResultList), in_list1(PrevResultList,Head),!.
get_unique_elements([Head|Tail],[Head|PrevResultList]):-get_unique_elements(Tail,PrevResultList),!.

%concat_unique(+FirstList:List,+SecondList:List,-ResultList:List)
%ResultList contains concatination of all unqiue elements of First and Second Lists
concat_unique([],SecondList,SecondList):-!.
concat_unique([Head|Tail],SecondList,PrevResultList):-in_list1(SecondList,Head),del_element(SecondList,Head,NewSecondList),concat_unique(Tail,NewSecondList,PrevResultList),!.
concat_unique([Head|Tail],SecondList,[Head|PrevResultList]):-concat_unique(Tail,SecondList,PrevResultList),!.

%read7_50(-FirstList:List,-SecondList:List)
% Read FirstList and SecondList from user input
read7_50(FirstList,SecondList):-read_list(FirstList),write("Input second list"),nl,read_list(SecondList),!.

%main7_50(+FirstList:List,+SecondList:List,-ResultList:List)
% ResultList contains concatination of unique elements of First and SecodList
main7_50(FirstList,SecondList,ResultList):-get_unique_elements(FirstList,FirstUnique),get_unique_elements(SecondList,SecondUnique),concat_unique(FirstUnique,SecondUnique,ResultList),!.

%task7_50
%main predicate for 7.50 task
task7_50:-read7_50(InputList1,InputList2),main7_50(InputList1,InputList2,ResultList),write_list(ResultList),!.

%task 7.56
%get_srt(+ElemSum:integer,+ElemCount:integer,-Result:integer)
%Result contains ElemSum/ElemCount
get_sr(_,0,0):-!.
get_sr(ElemSum,ElemCount,Result):-Result is ElemSum/ElemCount,!.

%check_if_simple(+N:integer,+CurrentDel:integer)
%True if N is simple integer
check_if_simple(1,_):-!,fail.
check_if_simple(N,_):-simple(N),!.
check_if_simple(N,1):-assert(simple(N)),!.
check_if_simple(N,CurrentDel):-0 is N mod CurrentDel,!,fail.
check_if_simple(N,CurrentDel):-NewCurrentDel is CurrentDel - 1,check_if_simple(N,NewCurrentDel).

%get_sum_of_simple(+InputList:List,-ResultSum:integer,-ResultCount:integer)
%ResultSum and ResultCount contain Sum and count of simple elements of InputList
get_sum_of_simple([],0,0):-!.
get_sum_of_simple([Head|Tail],CurrentSum,CurrentCount):-get_sum_of_simple(Tail,PrevCurrentSum,PrevCurrentCount),FirstDel is Head - 1,check_if_simple(Head,FirstDel),CurrentSum is PrevCurrentSum + Head,CurrentCount is PrevCurrentCount + 1,!.
get_sum_of_simple([_|Tail],PrevCurrentSum,PrevCurrentCount):-get_sum_of_simple(Tail,PrevCurrentSum,PrevCurrentCount),!.
:-dynamic simple/1.

%get_sum_of_not_simple(+InputList:List,+Sr:float,-ResultSum:integer,-ResultCount:integer)
%ResultSum and ResultCount contain Sum and count of not simple elements of InputList which are larger than Sr
get_sum_of_not_simple([],Sr,0,0):-!.
get_sum_of_not_simple([Head|Tail],Sr,CurrentSum,CurrentCount):-get_sum_of_not_simple(Tail,Sr,PrevCurrentSum,PrevCurrentCount),FirstDel is Head - 1, not(check_if_simple(Head,FirstDel)),Head>Sr,CurrentSum is PrevCurrentSum + Head,CurrentCount is PrevCurrentCount + 1,!.
get_sum_of_not_simple([_|Tail],Sr,PrevCurrentSum,PrevCurrentCount):-get_sum_of_not_simple(Tail,Sr,PrevCurrentSum,PrevCurrentCount),!.

%main7_56(+InputList:List,-NotSimpleSr:float)
% NotSimpleSr contains arithmethic average of not simple elements of InputList which are larger than average of simple elements
main7_56(InputList,NotSimpleSr):-retractall(simple(_)),get_sum_of_simple(InputList,ResultSum,ResultCount),get_sr(ResultSum,ResultCount,ResultSr),get_sum_of_not_simple(InputList,ResultSr,NotSimpleSum,NotSimpleCount),get_sr(NotSimpleSum,NotSimpleCount,NotSimpleSr),!.

%task7_56
%main predicate for task 7.56
task7_56:-read_list(InputList),main7_56(InputList,Result),write(Result),!.