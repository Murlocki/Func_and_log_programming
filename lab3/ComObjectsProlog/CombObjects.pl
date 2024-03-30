%in_list(+InputList:List,?El:atom)
%True if El is in InputList
in_list([El|_],El).
in_list([_|T],El):-in_list(T,El).

%razm_povt(+Alphabet:List,+Ncur:integer,+RazmCur:List,-Razm:List)
%Return first placement with repeats
%Otherwise return next placements with repeats
razm_povt(_,0,Razm,Razm):-!.
razm_povt(Alphabet,Ncur,RazmCur,Razm):- in_list(Alphabet,El),NNew is Ncur - 1, razm_povt(Alphabet,NNew,[El|RazmCur],Razm).

%comb(+Alphabet:List,-Combination:List,+K:integer)
comb(_,[],0):-!.
comb([El|RestAlp],[El|PrevComb],K):-KNew is K - 1,comb(RestAlp,PrevComb,KNew).
comb([El|RestAlp],PrevComb,K):- comb(RestAlp,PrevComb,K).

%make_pos_list(+K:integer,+CurPos:integer,-OutPut:List)
%Create a list with numbers from 0 to K
make_pos_list(K,K,[]):-!.
make_pos_list(K,CurPos,[NewPos|TailPos]):-NewPos is CurPos + 1,make_pos_list(K,NewPos,TailPos).

%make_3a_empty_word(+K:integer,+CurIndex:integer,+PositionsOfA:List,-OutputWord:List)
%Create OutputWord with length k and 'a' on positions from PositionsOfA
make_3a_empty_word(K,K,_,[]):-!.
make_3a_empty_word(K,CurIndex,[NewIndex|PositionsOfATail],[a|WordTail]):- NewIndex is CurIndex + 1,make_3a_empty_word(K,NewIndex,PositionsOfATail,WordTail),!.
make_3a_empty_word(K,CurIndex,PositionsOfA,[_|Tail]):- NewIndex is CurIndex + 1, make_3a_empty_word(K,NewIndex,PositionsOfA,Tail).

%build_word(-Word:List,+AWord:List,+RestWord:List)
%Create word in Word with 3 'a'
build_word([],[],_):-!.
build_word([a|WordTail],[El|AwordTail],RestWord):-nonvar(El),build_word(WordTail,AwordTail,RestWord),!.
build_word([Y|WordTail],[El|AwordTail],[Y|RestWordTail]):-var(El),build_word(WordTail,AwordTail,RestWordTail),!.


%build_3a_words_of_k(+Alphabet:List,+K:integer,-Word:List)
%Create a word with length k and 3 'a'
build_3a_words_of_k(Alphabet,K,Word):-make_pos_list(K,0,PositionList),
    comb(PositionList,PositionsOfA,3),make_3a_empty_word(K,0,PositionsOfA,OutputWord), 
    Alphabet = [a|NewAlphabet], M is K-3, razm_povt(NewAlphabet,M,[],RestWord),
    build_word(Word,OutputWord,RestWord).

%build_all_3a(+Alphabet:List,+K:integer)
%Print all words with length k and 3 'a'
build_all_3a(Alphabet,K):-build_3a_words_of_k(Alphabet,K,Word),write(Word),nl,fail.


%task 3

%3.1.

%delete_elem(+List:List,+El:atom,-Result:List)
%Result contains List without first appearance of El
delete_elem([],El,[]):-!.
delete_elem([El|Tail],El,Tail):-!.
delete_elem([Head|Tail],El,[Head|Res]):-delete_elem(Tail,El,Res),!.

%getPlacement(+Alphabet:List,K:integer,-Pl:List)
%Pl contains placement with length K
getPlacement(_,0,[]):-!.
getPlacement(Alphabet,K,[Element|PrevPl]):-in_list(Alphabet,Element),NewK is K-1,
    delete_elem(Alphabet,Element,NewAlp),getPlacement(NewAlp,NewK,PrevPl).

%print_all_pl(+Alphabet:List,+K:Integer)
%Printing all placements without rep with length k
print_all_pl(Alphabet,K):-getPlacement(Alphabet,K,Res),write(Res),nl,fail,!.

%print_all_pl(+Alphabet:List,+K:integer,+FilePath:String)
%Prin all placements into file
print_all_pl(Alphabet,K,FilePath):-tell(FilePath),\+(print_all_pl(Alphabet,K)),told,!.

%3.2.

%findLengthOfList(_InputList:List,+CurrentLen:integer,-ResultLen:integer)
%ResultLen contains length of InputList
findLengthOfList([],ResultLen,ResultLen):-!.
findLengthOfList([H|Tail],CurrentLen,ResultLen):-NewLen is CurrentLen + 1,
    findLengthOfList(Tail,NewLen,ResultLen),!.

%getPerm(+Alphabet:List,-Perm:List)
%Perm contains permutation of Alphabet
getPerm(Alphabet,Perm):-findLengthOfList(Alphabet,0,N),getPlacement(Alphabet,N,Perm).

%print_all_perm(+Alphabet:List)
%Printing all permutations of Alphabet
print_all_perm(Alphabet):-getPerm(Alphabet,Perm),write(Perm),nl,fail,!.

%print_all_perm(+Alphabet:List,+FilePath:String)
%Print all permutations into file
print_all_perm(Alphabet,FilePath):-tell(FilePath),\+(print_all_perm(Alphabet)),told,!.

%3.3.

%all_subsets(+Alphabet:List,+K:Integer)
%Print all subsets with length K from 0 to K
all_subsets(_,0):-!.
all_subsets(Alphabet,K):- \+ (subset_K(Alphabet,K)), NewK is K - 1, all_subsets(Alphabet,NewK),!.

%subset_K(+Alphabet:List,+K:Integer)
%Print all subsets with length K
subset_K(Alphabet,K):-comb(Alphabet,Result,K),write(Result),nl,fail,!.

%print_all_subsets(+Alphabet:List)
%print all susbsets of Alphabet with length from 0 to length of Alphabet
print_all_subsets(Alphabet):-write([]),nl, findLengthOfList(Alphabet,0,Length), all_subsets(Alphabet,Length),!.

%print_all_subsets(+Alphabet:List,+FilePath:String)
%Print all subsets into file
print_all_subsets(Alphabet,FilePath):-tell(FilePath), print_all_subsets(Alphabet),told,!.


%3.4.
%comb_with_reps(+Alphabet:List,+K:integer,-Result:List)
%Result contains Combination with repeats and length K
comb_with_reps(_,0,[]):- !.
comb_with_reps([El|Alphabet],K,[El|PrevResult]):- KNew is K - 1,
    comb_with_reps([El|Alphabet],KNew,PrevResult).
comb_with_reps([El|Alphabet],K,PrevResult):- comb_with_reps(Alphabet,K,PrevResult).


%print_all_comb_reps(+Alphabet:List,+K:integer)
%Pringing all Combinations with reps and length K
print_all_comb_reps(Alphabet,K):-comb_with_reps(Alphabet,K,Result),write(Result),nl,fail,!.

%print_all_comb_reps(+Alphabet:List,+K:integer,+FilePath:String)
%Print all combination with reps into file
print_all_comb_reps(Alphabet,K,FilePath):-tell(FilePath), \+(print_all_comb_reps(Alphabet,K)),told,!.

%3.7.

%put_symbol(+Symbol:atom,+SymbolPositions:List,+K:integer,+CurIndex:integer,-Result:List)
%Result contains List with Symbol on SymbolPositions positions
put_symbol(_,_,K,K,[]):-!.
put_symbol(Symbol,[NewInd|SymbolPositions],K,SymbolInd,[Symbol|PrevWord]):-NewInd is SymbolInd + 1,put_symbol(Symbol,SymbolPositions,K,NewInd,PrevWord),!.
put_symbol(Symbol,SymbolPositions,K,SymbolInd,[_|PrevWord]):-NewInd is SymbolInd + 1,put_symbol(Symbol,SymbolPositions,K,NewInd,PrevWord),!.

%put_symbol_on_k_pos(+Alphabet:List,+WordsLen:integer,+KPos:Integer,-ResultWord:List,-NewAlphabet:List)
%ResultWord contains word with somesymbol on KPos positions and NewAlphabet contains Alphabet without this somesymbol
put_symbol_on_k_pos(Alphabet,WordsLen,KPos,ResultWord,NewAlphabet):-in_list(Alphabet,TwoTimesSymbol),make_pos_list(WordsLen,0,PosList), 
    comb(PosList,TwoTimesSymbolPositions,KPos), put_symbol(TwoTimesSymbol,TwoTimesSymbolPositions,WordsLen,0,ResultWord),
    delete_elem(Alphabet,TwoTimesSymbol,NewAlphabet).


%place_unique_symbol(+InputWord:List,+InputPl:List,-ResultWord:List)
%ResultWord contains InputWord with symbols from InputPl on free positions
place_unique_symbol([],_,[]):-!.
place_unique_symbol([El|Tail],[Symbol|InputPl],[Symbol|PrevResultWord]):-var(El), place_unique_symbol(Tail,InputPl,PrevResultWord),!.
place_unique_symbol([El|Tail],InputPl,[El|PrevResultWord]):-nonvar(El), place_unique_symbol(Tail,InputPl,PrevResultWord),!.

%main_2_1(+Alphabet:List)
%Print all words with symbols from Alphabet with length 5 and 1 symbols repeating twice
main_2_1(Alphabet):-put_symbol_on_k_pos(Alphabet,5,2,ResWord,ResAlp), getPlacement(ResAlp,3,ResultPl), place_unique_symbol(ResWord,ResultPl,OutputWord),write(OutputWord),nl,fail,!.

%main_2_1(+Alphabet:List,+FilePath:String)
%Print all words into file
main_2_1(Alphabet,FilePath):-tell(FilePath), \+(main_2_1(Alphabet)),told,!.


%make_pos_from_list(+InputList:List,+CurrentIndex:integer,-ResultList:List).
% ResultList contains List of all free indexes from InputList
make_pos_from_list([],_,[]):-!.
make_pos_from_list([Head|InputList],CurrentIndex,[CurrentIndex|PrevResultList]):-var(Head),NewIndex is CurrentIndex + 1, make_pos_from_list(InputList,NewIndex,PrevResultList),!.
make_pos_from_list([Head|InputList],CurrentIndex,PrevResultList):-nonvar(Head),NewIndex is CurrentIndex + 1, make_pos_from_list(InputList,NewIndex,PrevResultList),!.

%3.9.
%place_symbols(+InputWord:List,+Symbol:atom,+SymbolIndexes:List,+CurrentIndex:integer,-ResultWord:List)
%ResultWord contains InputWord with Symbol on SymbolIndexes positions
place_symbols(Word,_,[],_,Word):-!.
place_symbols([El|Tail],Symbol,[CurrentIndex|SymbolIndexesTail],CurrentIndex,[Symbol|PrevWord]):- NewIndex is CurrentIndex + 1,
    place_symbols(Tail,Symbol,SymbolIndexesTail,NewIndex,PrevWord),!.
place_symbols([El|Tail],Symbol,[Ind|SymbolIndexesTail],CurrentIndex,[El|PrevWord]):- NewIndex is CurrentIndex + 1,
    place_symbols(Tail,Symbol,[Ind|SymbolIndexesTail],NewIndex,PrevWord),!.

%put_symbol_exist_indexes(+Alphabet:List,+ExistsIndexes:List,+K:integer,-ResWord:List,-ResAlp:List)
% ResWord contains ExistsIndexes List with some symbol on K free positions
% ResAlp contains Alphabet without this some symbol
put_symbol_exist_indexes(Alphabet,ExistsIndexes,K,ResWord,ResAlp):-in_list(Alphabet,Symbol),make_pos_from_list(ExistsIndexes,1,IndexResult),comb(IndexResult,SymbolPositions,K),
    delete_elem(Alphabet,Symbol,ResAlp),place_symbols(ExistsIndexes,Symbol,SymbolPositions,1,ResWord).

%main_1_2_1_3(+Alphabet:List)
%Main predicate for printing all words with length 7 and 1 symbol repeating 2 times and 1 symbol repeating 3 times
main_1_2_1_3(Alphabet):-put_symbol_on_k_pos(Alphabet,7,2,ResWord,ResAlp), put_symbol_exist_indexes(ResAlp,ResWord,3,ResultWord,ResultAlp),getPlacement(ResultAlp,2,Placement),place_unique_symbol(ResultWord,Placement,OutputWord),write(OutputWord),nl,fail,!.

%main_2_1(+Alphabet:List,+FilePath:String)
%Print all words into file
main_1_2_1_3(Alphabet,FilePath):-tell(FilePath), \+(main_1_2_1_3(Alphabet)),told,!.

% task 6

%create_words(+N:integer,+Fsymbol:atom,K:integer,CSymbol:atom,+M:integer)
%Create and write all words with length N and Fsymbol repeating k and CSymbol repeating M
create_words(N,Fsymbol,K,CSymbol,M):- make_pos_list(N,0,AllPositions), comb(AllPositions,FSymbolPositions,K),
    put_symbol(Fsymbol,FSymbolPositions,N,0,WordWithF),
    make_pos_from_list(WordWithF,1,PossitionsFreeOfF), comb(PossitionsFreeOfF,CSymbolPos,M), place_symbols(WordWithF,CSymbol,CSymbolPos,1,WordWithFC),
    append([a,b,c,d,e,f],[],Alphabet), delete_elem(Alphabet,Fsymbol,AlpWithoutF),delete_elem(AlpWithoutF,CSymbol,AlpWithoutFC),
    NewN is N - K - M, getPlacement(AlpWithoutFC,NewN,RestSymbolPl), place_unique_symbol(WordWithFC,RestSymbolPl,ResultWord),write(ResultWord),write(1),nl,fail.

%read_all(-FilePath:String,-N:integer,-Fsymbol:atom,-K:integer,-CSymbol:atom,-M:integer)
%Main predicate for reading
read_all(FilePath,N,Fsymbol,K,CSymbol,M):-read(FilePath),read(N),read(Fsymbol),read(K),read(CSymbol),read(M),!.

%main_6
%Main predicate for task 6
main_6:-read_all(FilePath,N,Fsymbol,K,CSymbol,M),tell(FilePath),\+ (create_words(N,Fsymbol,K,CSymbol,M)),told,!.




 