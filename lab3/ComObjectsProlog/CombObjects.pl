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