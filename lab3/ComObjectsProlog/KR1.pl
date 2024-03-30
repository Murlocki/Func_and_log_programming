
 %Кр 1
 %Вар 1
 %3
sum(0,0):-!.
sum(Number,CurrentSum):- NewNumber is Number div 10, sum(NewNumber,PrevSum), Digit is Number mod 10, CurrentSum is PrevSum + Digit,!.

max_list(InputList,Ind):-max_list_call(InputList,SumMax,0,Ind),!.


max_list_call([],0,_,0):-!.
max_list_call([Head|Tail],CurrentMax,CurrentInd,Ind):- NewCurInd is CurrentInd + 1, max_list_call(Tail,PrevMax,NewCurInd,PrevInd),
    sum(Head,SumHead), SumHead > PrevMax, CurrentMax is SumHead, Ind is CurrentInd,!.
max_list_call([Head|Tail],CurrentMax,CurrentInd,Ind):- NewCurInd is CurrentInd + 1, max_list_call(Tail,PrevMax,NewCurInd,PrevInd),
    sum(Head,SumHead), CurrentMax is PrevMax, Ind is PrevInd,!.

%4
:-dynamic allComb/1.
comb_2(List,K,Sochet):- retractall(allComb(_)),assert(allComb([])),\+(all_comb(List,K)),
    allComb(Sochet),!.

all_comb(List,K):-comb_call(List,K,Z),allComb(X),
    append([Z],X,NewX),retractall(allComb(X)),
    assert(allComb(NewX)),fail,!.


comb_call(_,0,[]):-!.
comb_call([Head|Tail],K,[Head|CurrentComb]):-NewK is K - 1,
    comb_call(Tail,NewK,CurrentComb).
comb_call([Head|Tail],K,CurrentComb):-comb_call(Tail,K,CurrentComb).


%6

in_list([El|_],El).
in_list([Head|Tail],El):-in_list(Tail,El).

delete_elem([],_,[]):-!.
delete_elem([El|Tail],El,Tail):-!.
delete_elem([Head|Tail],El,[Head|Result]):-delete_elem(Tail,El,Result),!.


getPermit([],[]):-!.
getPermit(Alphabet,[El|ResultPerm]):- in_list(Alphabet,El),
    delete_elem(Alphabet,El,NewAlp), getPermit(NewAlp,ResultPerm).

check_if_euler([Head]):-!.
check_if_euler([[_,V2]|[[V2,OtherVert]|Tail]]):- check_if_euler([[V2,OtherVert]|Tail]),!.

getEuler(Vert,Edges,NewEdgesPerm):-getPermit(Edges,[Head|EdgesPermTail]),append([Head|EdgesPermTail],[Head],NewEdgesPerm),
    check_if_euler(NewEdgesPerm),!.


%5
makeNumbPos(0,[]):-!.
makeNumbPos(K,Result):- NewK is K - 1, makeNumbPos(NewK,PrevResult),
    append(PrevResult,[K],Result),!.


delete_elems(Alphabet,[],Alphabet):-!.
delete_elems(Alphabet,[Head|ElemsToDelete],Result):- delete_elem(Alphabet,Head,NewAlp),
    delete_elems(NewAlp,ElemsToDelete,Result),!.

putOnPos(_,_,[],_):-!.
putOnPos([Symbol|WordTail],CurrentPos,[CurrentPos|PositionTail],Symbol):-
    NewCurPos is CurrentPos + 1,putOnPos(WordTail,NewCurPos,PositionTail,Symbol),!.
putOnPos([Sym|WordTail],CurrentPos,[Head|PositionTail],Symbol):-
    NewCurPos is CurrentPos + 1,putOnPos(WordTail,NewCurPos,[Head|PositionTail],Symbol),!.

makeWords:-append([c,d,e,f],[],Alphabet),
    append([_,_,_,_,_,_],[],Word),!,
    makeNumbPos(6,Pos), comb_call(Pos,3,Apositions),
    delete_elems(Pos,Apositions,RestPos),
    comb_call(RestPos,2,BPositions),
    delete_elems(RestPos,BPositions,RestPos2),
    in_list(Alphabet,Symbol),
    putOnPos(Word,1,Apositions,a),
    putOnPos(Word,1,BPositions,b),
    putOnPos(Word,1,RestPos2,Symbol),
    write(Word),write('\n'),fail,!.


%Вариант 2

%3
fib(N,A):- found_fib(N,A),!.

found_fib(1,0):-!.
found_fib(2,1):-!.
found_fib(N,Fib):- NewN is N - 1, NewN2 is N - 2, found_fib(NewN,F1),
    found_fib(NewN2,F2),Fib is F1 + F2,!.


%4

:-dynamic allRazm/1.

razm(List,K,Razm):-retractall(allRazm(_)),assert(allRazm([])),
    \+(razm_all(List,K)),allRazm(Razm),!.

razm_all(Alphabet,K):-razm_call(Alphabet,K,Razm),allRazm(RList),
    append(RList,[Razm],NewRlist),retractall(allRazm(_)),
    assert(allRazm(NewRlist)),fail,!.
razm_call(_,0,[]):-!.
razm_call(Alphabet,K,[Elem|PrevRazm]):-in_list(Alphabet,Elem), 
    delete_elem(Alphabet,Elem,NewAlp),NewK is K - 1,
    razm_call(NewAlp,NewK,PrevRazm).


%5
razm_repeat(_,0,[]):-!.
razm_repeat(Alphabet,K,[Symbol|PrevPl]):-in_list(Alphabet,Symbol),
    NewK is K - 1,
    razm_repeat(Alphabet,NewK,PrevPl).

printWord([],[]):-!.
printWord([],[H|R2]):-write(H),printWord([],R2),!.
printWord([H|R1],R2):-write(H),printWord(R1,R2),!.

getWords:- razm_call([a,b,c,d,e],3,R1),razm_repeat([v,w,x,y,z],3,R2),
    printWord(R1,R2),nl,fail,!.



%6
check_if_way([Vert],_):-!.
check_if_way([Vert1|[Vert2|Tail]],[[Ver1,Vert2]|Edges]):- check_if_way([Vert2|Tail],[[Ver1,Vert2]|Edges]),!.
check_if_way([Vert1|[Vert2|Tail]],[[Ver2,Vert1]|Edges]):- check_if_way([Vert2|Tail],[[Ver1,Vert2]|Edges]),!.

getGamilton(Vert,Edges,Gam):- getPerm(Vert,[Head|PermTail]),append([Head|PermTail],[Head],Gam),
    check_if_way(Gam,Edges),!.


class PerWithReps{
    public String[] alphabet;
    public String[] currentObject;
    public int k;
    public int n;
    public PerWithReps(String[]alphabet,int k){
        this.alphabet = alphabet;
        this.k=k;
        this.n = alphabet.length;
        this.currentObject = new String[k];
    }
    public void printAllPermRecCall(){
        printAllPermRec(0);
    }
    public void printAllPermRec(int currentK){
        if(currentK==this.k){
            System.out.println(Arrays.toString(this.currentObject));
        }
        else{
            int b = 0;
            for(int i=0;i<this.n;i++){
                if(!Arrays.asList(this.currentObject).contains(this.alphabet[i])){
                    b=b+1;
                }
            }
            if(this.k - currentK == b){
                for(int i=0;i<this.n;i++){
                    if(!Arrays.asList(this.currentObject).contains(this.alphabet[i])){
                        this.currentObject[currentK]=this.alphabet[i];
                        printAllPermRec(currentK+1);
                        this.currentObject[currentK]="";
                    }
                }
            }
            else{
                for(int i=0;i<this.n;i++){
                        this.currentObject[currentK]=this.alphabet[i];
                        printAllPermRec(currentK+1);
                        this.currentObject[currentK]="";
                    }
                }
            }
        }
    }

    %Вне заданий
%Перестановки с повторениями
permutation_with_reps(Alphabet,K):-permutation_with_reps_call(Alphabet,Alphabet,K,Result),write(Result),nl,fail,!.

permutation_with_reps_call(_,_,0,[]):-!.
permutation_with_reps_call(Alphabet,RestAlp,K,[NewSymbol|ResultPerm]):- findLengthOfList(RestAlp,0,RestAlpLen), RestAlpLen is K,
    in_list(RestAlp,NewSymbol), 
    delete_elem(RestAlp,NewSymbol,NewRestAlp), NewK is K - 1,
    permutation_with_reps_call(Alphabet,NewRestAlp,NewK,ResultPerm).
permutation_with_reps_call(Alphabet,RestAlp,K,[NewSymbol|ResultPerm]):- findLengthOfList(RestAlp,0,RestAlpLen), RestAlpLen < K,
    in_list(Alphabet,NewSymbol), 
    delete_elem(RestAlp,NewSymbol,NewRestAlp), NewK is K - 1,
    permutation_with_reps_call(Alphabet,NewRestAlp,NewK,ResultPerm).