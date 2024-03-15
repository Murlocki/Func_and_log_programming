
write_list_str([]):-!.
write_list_str([H|List]):-write(H),write_list_str(List).

read_str_one(-1,CurrentStr,CurrentStr,0):-!.
read_str_one(10,CurrentStr,CurrentStr,1):-!.
read_str_one(26,CurrentStr,CurrentStr,0):-!.
read_str_one(CurrentSymbol,CurrentStr,ResultStr,Flag):- char_code(ResSymbol,CurrentSymbol),append(CurrentStr,[ResSymbol],NewCurrentStr),get0(NewSymbol), read_str_one(NewSymbol,NewCurrentStr,ResultStr,Flag).

read_rest_str(CurrentList,CurrentList,0):-!.
read_rest_str(StrList,CurrentList,_):-get0(NewSymbol), read_str_one(NewSymbol,[],ResultStr,Flag),append(CurrentList,[ResultStr],NewStrList),read_rest_str(StrList,NewStrList,Flag).

read_str_from_f(ListOfStrings):-get0(NewSymbol),read_str_one(NewSymbol,[],FirstStr,Flag),read_rest_str(ListOfStrings,[FirstStr],Flag),!.
read_strings_in_list(FilePath,ReadStrs):-see(FilePath),read_str_from_f(ReadStrs),seen.


write_list_of_lists([]):-!.
write_list_of_lists([H|TailListOfLists]):-write_list_str(H),nl,write_list_of_lists(TailListOfLists),!.


write_to_file(FilePath,ListOfStrings):-tell(FilePath),write_list_of_lists(ListOfStrings),told,!.

%2.1.

%max(+X:integer,+Y:integer,-Result:Integer)
%Result contrains max of X and Y
max(X,Y,X):-X>Y,!.
max(X,Y,Y):-!.

%findLengthOfString(+String:List,+CurrentLen:Integer,-ResultLen:Integer)
%ResultLen contains len of String
findLengthOfString([],CurrentLen,CurrentLen):-!.
findLengthOfString([H|StringTail],CurrentLen,MaxLen):-NewLen is CurrentLen + 1,findLengthOfString(StringTail,NewLen,MaxLen),!.

%findStringMaxLength(+ListOfString:List,+CurrentMax:Integer,-ResultMax:Integer):-!.
%ResultMax contains max length of element of ListOfString
findStringMaxLength([],ResultMax,ResultMax):-!.
findStringMaxLength([String|TailListOfStrings],CurrentMax,ResultMax):-findLengthOfString(String,0,CurrentLen),max(CurrentLen,CurrentMax,NewMax),  
    findStringMaxLength(TailListOfStrings,NewMax,ResultMax).

%main2_1(+ListOfStrings:List)
%Main predicate for calculating max length
main2_1(ListOfStrings):-findStringMaxLength(ListOfStrings,0,ResultMax),write('Максимальная длина:'),write(ResultMax),!.

%task2_1(InputPath:String,OutputPath:String)
%Main predicate for task 2.1.
task2_1(InputPath,OutputPath):-read_strings_in_list(InputPath,ListOfStrings),write_list_of_lists(ListOfStrings),main2_1(ListOfStrings), write_to_file(OutputPath,ListOfStrings),!.