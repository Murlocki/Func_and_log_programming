
%read_str_one(+CurrentSymbol:char,+CurrentStr:List,-ResultStr:List,-Flag:integer)
% ResultStr contains read one str
read_str_one(-1,CurrentStr,CurrentStr,0):-!.
read_str_one(10,CurrentStr,CurrentStr,1):-!.
read_str_one(26,CurrentStr,CurrentStr,0):-!.
read_str_one(CurrentSymbol,CurrentStr,ResultStr,Flag):- char_code(ResSymbol,CurrentSymbol),append(CurrentStr,[ResSymbol],NewCurrentStr),get0(NewSymbol), read_str_one(NewSymbol,NewCurrentStr,ResultStr,Flag).

%read_rest_str(-StrList:List,+CurrentList:List,-Flag:integer)
%StrList contains list of all str read from input
read_rest_str(CurrentList,CurrentList,0):-!.
read_rest_str(StrList,CurrentList,_):-get0(NewSymbol), read_str_one(NewSymbol,[],ResultStr,Flag),append(CurrentList,[ResultStr],NewStrList),read_rest_str(StrList,NewStrList,Flag).

%read_str_from_f(-ListOfStrings:List)
%Main predicate for reading all str from input
read_str_from_f(ListOfStrings):-get0(NewSymbol),read_str_one(NewSymbol,[],FirstStr,Flag),read_rest_str(ListOfStrings,[FirstStr],Flag),!.

%read_strings_in_list(+FilePath:String,-ReadStrs:List)
%ReadStrs contains all str from FilePath file
read_strings_in_list(FilePath,ReadStrs):-see(FilePath),read_str_from_f(ReadStrs),seen.

%write_list_of_lists(+ListOfLists:List)
%Write all strings from ListOfLists
write_list_of_lists([]):-!.
write_list_of_lists([H|TailListOfLists]):-write_list_str(H),write_list_of_lists(TailListOfLists),!.

%write_list_str(+List:List)
%Write List 
write_list_str([]):-nl,!.
write_list_str([H|List]):-write(H),write_list_str(List).

%write_to_file(+FilePath:String,+ListOfStrings:List)
%Write ListOfStrings to Filepath File
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

%task2_1(InputPath:String)
%Main predicate for task 2.1.
task2_1(InputPath):-read_strings_in_list(InputPath,ListOfStrings),write_list_of_lists(ListOfStrings),main2_1(ListOfStrings),!.


%2.2

%check_if_space(+InputString:List,-Flag:Integer)
% Flag is 1 if InputString is without spaces
check_if_space([],1):-!.
check_if_space([Head|_],Flag):-  char_code(Head,HeadCode), HeadCode is 32, Flag is 0,!.
check_if_space([_|StringTail],Flag):-  check_if_space(StringTail,Flag),!.

%count_str_without_space(+StringList:List,+CurrentResult:integer,-Result:Integer)
%Result contains number of strings without spaces from StringList
count_str_without_space([],Result,Result):-!.
count_str_without_space([Head|TailStringList],CurrentRes,Result):- check_if_space(Head,Res), NewCurrentRes is CurrentRes + Res,
    count_str_without_space(TailStringList,NewCurrentRes,Result),!.

%main2_2(+ListOfStrings:List)
%main predicate for prining number of strings without spaces
main2_2(ListOfStrings):-count_str_without_space(ListOfStrings,0,Result),write('Количество строк без пробелов:'),write(Result),!.

%task2_2(+InputPath:String)
%Main predicate for task 2.2.
task2_2(InputPath):-read_strings_in_list(InputPath,ListOfStrings),write_list_of_lists(ListOfStrings),main2_2(ListOfStrings),!.

% task 2.3
%count_of_A(+String:List,+CurrentRes:integer,-Result:Integer)
%Result contains number of 'A' in String
count_of_A([],Result,Result):-!.
count_of_A([Head|StringTail],CurrentACount,ResultCount):-char_code(Head,Code), Code is 65 ,NewCurrentCount is CurrentACount + 1, 
    count_of_A(StringTail,NewCurrentCount,ResultCount),!.
count_of_A([_|StringTail],CurrentACount,ResultCount):- count_of_A(StringTail,CurrentACount,ResultCount),!.

%count_mean_A(+StringList:List,+CurrentSum:Integer,+CurrentCount:Integer,-Result:Float)
%Result contains mean count of A between all strings of StringList
count_mean_A([],0,0,0):-!.
count_mean_A([],CurrentSum,CurrentCount,Result):-Result is CurrentSum/CurrentCount,!.
count_mean_A([Head|StringList],CurrentSum,CurrentCount,Result):- count_of_A(Head,0,ResultA), NewSum is CurrentSum + ResultA,
    NewCount is CurrentCount+1, count_mean_A(StringList,NewSum,NewCount,Result),!.

%print_all_mean_greater(+StringList:List,+ResultMean:float)
%Print all strings from StringList which have count of A greater than ResultMean
print_all_mean_greater([],_):-!.
print_all_mean_greater([Head|TailStringList],ResultMean):-count_of_A(Head,0,ResultCount), ResultCount > ResultMean, write_list_str(Head),print_all_mean_greater(TailStringList,ResultMean),!.
print_all_mean_greater([Head|TailStringList],ResultMean):-print_all_mean_greater(TailStringList,ResultMean),!.

%main2_3(+ListOfStrings:List)
%Main predicate for printing strings
main2_3(ListOfStrings):- count_mean_A(ListOfStrings,0,0,MeanResult),print_all_mean_greater(ListOfStrings,MeanResult),!.

%task2_3(+InputPath:String)
%Main prediacte for task 2.3
task2_3(InputPath):- read_strings_in_list(InputPath,ListOfStrings), main2_3(ListOfStrings),!.


%2.4.
%God save us for this

:-dynamic wordsCount/2.

%prologHashmap(Element)
% Add Element in fact base with count 1 or set count+1 for Element
prologHashmap(''):- !.
prologHashmap(Element):- wordsCount(Element,Count),NewCount is Count + 1, retract(wordsCount(Element,Count)),
    assert(wordsCount(Element,NewCount)),!.
prologHashmap(Element):- assert(wordsCount(Element,1)),!.

%getWords(+String:List,+Buffer:Atom)
%Calc the count of words in String
getWords([],''):-!.
getWords([],Buffer):-prologHashmap(Buffer),!.
getWords([Head|Tail],Buffer):- char_code(Head,NewCode), NewCode >=65,NewCode=<90, atom_concat(Buffer,Head,NewBuffer),getWords(Tail,NewBuffer),!.
getWords([Head|Tail],Buffer):- char_code(Head,NewCode), NewCode >=97,NewCode=<122, atom_concat(Buffer,Head,NewBuffer),getWords(Tail,NewBuffer),!.
getWords([Head|Tail],Buffer):- char_code(Head,NewCode), NewCode >=48,NewCode=<57, atom_concat(Buffer,Head,NewBuffer),getWords(Tail,NewBuffer),!.
getWords([_|Tail],Buffer):- prologHashmap(Buffer),NewBuffer='',getWords(Tail,NewBuffer),!.

%count_all_words(StringList)
%Calc the count of all words in String List
count_all_words([]):-!.
count_all_words([Head|StringList]):-getWords(Head,''),count_all_words(StringList),!.

%max_word(-Word:Atom)
%Word contains word which appears at max count
max_word(Word) :-
    wordsCount(Word, Count), \+ (wordsCount(_, Count1), Count1 > Count).

%main2_4(+ListOfStrings:List,-Word:Atom)
%Main pred for finding max Word
main2_4(ListOfStrings,Word):-retractall(wordsCount(_,_)),count_all_words(ListOfStrings),max_word(Word),!.

%task2_4(+InputPath:String)
%Main pred for task 2.4.
task2_4(InputPath):-read_strings_in_list(InputPath,ListOfStrings), main2_4(ListOfStrings,Word),write(Word),!.


%2.5.
%checkWord1(+String:List,+Buffer:Atom,-Res:Integer)
%Res is 1 if any word in string appear only 1 time in input
checkWord1([],'',1):-!.
checkWord1([],Buffer,0):-wordsCount(Buffer,Count),Count>=2,!.
checkWord1([],Buffer,1):-!.
checkWord1([Head|Tail],Buffer,Res):- char_code(Head,NewCode), NewCode >=65,NewCode=<90, atom_concat(Buffer,Head,NewBuffer),checkWord1(Tail,NewBuffer,Res),!.
checkWord1([Head|Tail],Buffer,Res):- char_code(Head,NewCode), NewCode >=97,NewCode=<122, atom_concat(Buffer,Head,NewBuffer),checkWord1(Tail,NewBuffer,Res),!.
checkWord1([Head|Tail],Buffer,Res):- char_code(Head,NewCode), NewCode >=48,NewCode=<57, atom_concat(Buffer,Head,NewBuffer),checkWord1(Tail,NewBuffer,Res),!.
checkWord1([_|Tail],Buffer,Res):- wordsCount(Buffer,Count),Count>=2,Res is 0,!.
checkWord1([_|Tail],Buffer,Res):- NewBuffer='',checkWord1(Tail,NewBuffer,Res),!.

%check_in_all_strings(+ListString:List)
%Print all Strings that have unique words across all input
check_in_all_strings([]):-!.
check_in_all_strings([Head|TailListString]):-checkWord1(Head,'',Res),Res is 1, write_list_str(Head),check_in_all_strings(TailListString),!.
check_in_all_strings([Head|TailListString]):-check_in_all_strings(TailListString),!.

%main2_5(+ListOfStrings:List)
%main predicate 
main2_5(ListOfStrings):-retractall(wordsCount(_,_)),count_all_words(ListOfStrings), check_in_all_strings(ListOfStrings),!.

%task2_5(+InputPath:String,+FilePath:String)
%main predicate for task 2.5.
task2_5(InputPath,FilePath):-read_strings_in_list(InputPath,ListOfStrings), tell(FilePath),main2_5(ListOfStrings),told,!.

