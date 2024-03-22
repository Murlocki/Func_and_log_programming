%read_str(-A:List)
%A contains read string
read_str(A):-get0(X),rec_str(X,A).

%rec_str(+ReadElement:integer,-ResultString:List)
%ResultString contains read string
rec_str(10,[]):-!.
rec_str(X,[X|Tail]):-get0(X1),rec_str(X1,Tail),!.

%del_1st(+InputList:List,-Result:List)
%Result contains InputList without 1st element
del_1st([_|T],T):-!.


%delete_last(+InputList:List,-Result:List)
%Result contains InputList without last element
delete_last([_],[]):-!.
delete_last([H|T],[H|Res]):-delete_last(T,Res),!.

%get_vertexes(-V:List)
%V contains read vertexes
get_vertexes(V):-read(NumberOfVertexes),write("List of vertexes"),nl,N1 is NumberOfVertexes+1,get_vertex(V1,N1),del_1st(V1,V).

%get_vertex(-VertexList:List,+N:integer)
%VertexList contains list of N read vertexes
get_vertex([],0):-!.
get_vertex([H|T],N):- read_str(X),name(H,X),N1 is N-1,get_vertex(T,N1),!.

%check_vertex(+VertexList,+V1:atom)
%True if V1 is in VertexList
check_vertex([V1|_],V1):-!.
check_vertex([_|T],V1):-check_vertex(T,V1).

%get_edges(+Vertexes:List,-Edges:List)
%Edges contains list of read edges
get_edges(Vertexes,Edges):-read(EdgeCount),get0(X),get_edges(Vertexes,Edges,EdgeCount,0).

%get_edges(+Vertexes:List,-Edges:List,+M:integer,+CurrentM:integer)
%Edges contain List of read M edges
get_edges(_,[],M,M):-!.
get_edges(Vertexes,[Edge|Tail],M,CurrentM):-get_edge(Vertexes,Edge), NewCurM is CurrentM + 1,get_edges(Vertexes,Tail,M,NewCurM),!.

%get_edge(Vertexes,Edge)
%Edge contains read edge
get_edge(V,[V1,V2]):-write("EDGE"),nl, read_str(X), name(V1,X), check_vertex(V,V1),
    read_str(Y), name(V2,Y), check_vertex(V,V2).

%get_graph(-V:List,-E:List)
%V contains vertexes of graph
%E containe edges of graph
get_graph(V,E):-get_vertexes(V),write("Count Edges"),nl,get_edges(V,E),!.

%in_list1(+InputList:List,+El:atom)
%True if El is in InputList
in_list1([El|_],El):-!.
in_list1([_|Tail],El):-in_list1(Tail,El),!.

%task 1
%euler_N(+Edges:List,-Res:List)
%Res contains euler cycle from Edges
euler_N(Edges,Res):-getPerm(Edges,[StartWay|TailWay]), append([StartWay|TailWay],[StartWay],Way),
    check_way_edges_N(Way),delete_last(Way,Res),!.

%check_way_edges_N(+EdgesWay:List)
%True if EdgesWay exists
check_way_edges_N([_]):-!.
check_way_edges_N([[_,Vert]|[[Vert,OtherVert]|Tail]]):-check_way_edges_N([[Vert,OtherVert]|Tail]),!.
check_way_edges_N([[_,Vert]|[[OtherVert,Vert]|Tail]]):-check_way_edges_N([[Vert,OtherVert]|Tail]),!.

%main_find_euler(-Way:List)
%Way contains euler cycle
main_find_euler(Way):-get_graph(_,E),euler_N(E,Way).

%task 2

%check_all_edges_N(+CurrentVert:atom,+RestVertexes:List,+Edges:List)
%True if CurrentVert have at least one edge with every vert from RestVertexes
check_all_edges_N(_,[],_):-!.
check_all_edges_N(CurrentVert,[H|Tail],Edges):- in_list1(Edges,[CurrentVert,H]),
    check_all_edges_N(CurrentVert,Tail,Edges),!.
check_all_edges_N(CurrentVert,[H|Tail],Edges):- in_list1(Edges,[H,CurrentVert]),
    check_all_edges_N(CurrentVert,Tail,Edges),!.

%check_if_click_N(+ClickToCheck:List,+Edges:List)
%True if ClickToCheck is Clique
check_if_click_N([_],_):-!.
check_if_click_N([CurrentVert|Tail],Edges):- check_all_edges_N(CurrentVert,Tail,Edges),check_if_click_N(Tail,Edges),!.

%get_max_click(+Vert:atom,+RestVertexes:List,+Edges:List,+K:integer,-ResultClick:List)
%ResultClick contains first click of size K
get_max_click(Vert,RestVertexes,Edges,K,ResultClick):- NewK is K - 1,comb(RestVertexes,Click,NewK),append([Vert],Click,ClickToCheck),
    check_if_click_N(ClickToCheck,Edges), append(ClickToCheck,[],ResultClick),!.

%get_click(+Vert:Input,+RestVertexes:List,+Edges:List,+CurrentClickSize:integer,-ResultClick:List)
%ResultClick contains first click with vertext Vert
get_click(Vert,_,_,1,[Vert]):-!.
get_click(Vert,RestVertexes,Edges,CurrentClickSize,ResultClick):- get_max_click(Vert,RestVertexes,Edges,CurrentClickSize,ResultClick),!.
get_click(Vert,RestVertexes,Edges,CurrentClickSize,ResultClick):-NewClickSize is CurrentClickSize - 1,
    get_click(Vert,RestVertexes,Edges,NewClickSize,ResultClick),!.

%read_2(-Vertexes:List,-Edges:List,-Vert:atom)
%Vertexes contains list of read vertexes
%Edges contains list of read edges
%Vert contains read vert
read_2(Vertexes,Edges,Vert):-get_graph(Vertexes,Edges),write("Input vertex"),nl,read_str(X),name(Vert,X),in_list1(Vertexes,Vert),!.

%get_click(-Click:LiST)
%Click contains first found click
get_click(Click):-read_2(Vertexes,Edges,Vert), delete_elem(Vertexes,Vert,RestVertexes),
   findLengthOfList(Vertexes,0,VertLen), get_click(Vert,RestVertexes,Edges,VertLen,Click).


%task3

:-dynamic vertexNumber/2.

%putVertOnNumber(+Vertexes:List,+NumberList:List)
%Adding facts about number of every vertex
putVertOnNumber([],[]):-!.
putVertOnNumber([Vert|TailVertexes],[Number|TailNumberList]):- assert(vertexNumber(Vert,Number)),
    putVertOnNumber(TailVertexes,TailNumberList),!.

%check_if_sort(+Edges:LiST)
%True if for every edge we have number of vert start smaller than number of vert end
check_if_sort([]):- !.
check_if_sort([[Vert1,Vert2]|EdgesTail]):- vertexNumber(Vert1,Number1),vertexNumber(Vert2,Number2),
    Number1<Number2,check_if_sort(EdgesTail),!.

%print_sort(+NumberList:List,-Vertexes:List)
%Vertexes contains vertexes sorted according to current topoligical sort
print_sort([],[]):-!.
print_sort([SortHead|SortTail],[Vert|VertTail]):-vertexNumber(Vert,SortHead),
    print_sort(SortTail,VertTail),!.

%get_topological_sort(-SortedVertex:List)
%%Vertexes contains vertexes sorted according to first found topoligical sort
get_topological_sort(SortedVertex):- get_graph(Vertexes,Edges), findLengthOfList(Vertexes,0,VertLen),
    make_pos_list(VertLen,0,NumberList),getPerm(NumberList,Sort),
        retractall(vertexNumber(_,_)),putVertOnNumber(Vertexes,Sort),
            check_if_sort(Edges),print_sort(NumberList,SortedVertex),!.


%task 4

%translate_into_edges(+Vertexes:List,+Edges:List)
%Edges contains cycle from Vertexes as edge list
translate_into_edges([_],[]):-!.
translate_into_edges([V1|[V2|VertTail]],[[V1,V2]|EdgeTail]):-translate_into_edges([V2|VertTail],EdgeTail),!.

%translate_into_edges_list(+VertListOfLists:List,+EdgeListofList)
%Use translate_into_edges for every item of VertListOfLists
translate_into_edges_list([],[]):-!.
translate_into_edges_list([VertList|Tail],[EdgeList|EdgeTail]):-translate_into_edges(VertList,EdgeList),
   translate_into_edges_list(Tail,EdgeTail),!.

%read_FMC(-FMC:List,+N:integer)
%FMC contains read FMC of N cycles
read_FMC([],0):-!.
read_FMC([Cycle|FMC],N):-write('Input cycle'),nl,get_vertexes([StVert|TailCycle]),append([StVert|TailCycle],[StVert],Cycle),
    NewN is N-1,read_FMC(FMC,NewN),!.

%read_cycles(-Cycle:List,-FMC:List)
%Cycle and FMC contain read cycle and FMC
read_cycles(Cycle,FMC):-get_vertexes([StVert|TailCycle]),append([StVert|TailCycle],[StVert],Cycle),
    write('Input Count of FMC'),nl,read(N),read_FMC(FMC,N),!.


%check_cycle(+Cycle:List,+CurrentComb:List)
%True if Cycle and CurrentComb are the same cycle

check_cycle([],[]):-!.
check_cycle(Cycle,[[CombEdgeV1,CombEdgeV2]|CombTail]):-in_list1(Cycle,[CombEdgeV1,CombEdgeV2]),delete_elem(Cycle,[CombEdgeV1,CombEdgeV2],NewCycle),
    check_cycle(NewCycle,CombTail),!.
check_cycle(Cycle,[[CombEdgeV1,CombEdgeV2]|CombTail]):-in_list1(Cycle,[CombEdgeV2,CombEdgeV1]),delete_elem(Cycle,[CombEdgeV2,CombEdgeV1],NewCycle),
    check_cycle(NewCycle,CombTail),!.

%appendCycleToCycle(+CycleEdgeList:List,+CurrentCycle:List,-ResultCycle:List)
%ResultCycle contains mod 2 of CycleEdgeList
appendCycleToCycle([],ResultCycle,ResultCycle):-!.
appendCycleToCycle([[CycleEdgeV1,CycleEdgeV2]|CycleTail],CurrentCycle,ResultCycle):-in_list1(CurrentCycle,[CycleEdgeV1,CycleEdgeV2]),
    delete_elem(CurrentCycle,[CycleEdgeV1,CycleEdgeV2],NewCycle),appendCycleToCycle(CycleTail,NewCycle,ResultCycle),!.
appendCycleToCycle([[CycleEdgeV1,CycleEdgeV2]|CycleTail],CurrentCycle,ResultCycle):-in_list1(CurrentCycle,[CycleEdgeV2,CycleEdgeV1]),
    delete_elem(CurrentCycle,[CycleEdgeV2,CycleEdgeV1],NewCycle),appendCycleToCycle(CycleTail,NewCycle,ResultCycle),!.
appendCycleToCycle([CycleEdge|CycleTail],CurrentCycle,ResultCycle):-append(CurrentCycle,[CycleEdge],NewCycle),
    appendCycleToCycle(CycleTail,NewCycle,ResultCycle),!.


%getCycleFromComb(+CycleCombEdges:List,+CurrentCycle:List,-ResultCycle:List)
%ResultCycle contains cycle that was created by mod 2 of all cycles from CycleCombEdges
getCycleFromComb([],ResultCycle,ResultCycle):-!.
getCycleFromComb([CycleEdge|CycleCombEdgesTail],CurrentCycle,ResultCycle):-appendCycleToCycle(CycleEdge,CurrentCycle,NewCurCycle),
    getCycleFromComb(CycleCombEdgesTail,NewCurCycle,ResultCycle),!.

%getCyclesCombs(+FMC:List,+Cycle:List,+K:integer,-CycleComb)
%CycleComb contains combination of cycles from FMC that can create Cycle
getCyclesCombs(_,_,0,[]):-!,fail.
getCyclesCombs(FMC,Cycle,K,CycleComb):-comb(FMC,CycleComb,K), translate_into_edges_list(CycleComb,CycleCombEdges),
    getCycleFromComb(CycleCombEdges,[],ResultCycleFromComb),check_cycle(Cycle,ResultCycleFromComb),!.
getCyclesCombs(FMC,Cycle,K,CycleComb):-NewK is K - 1,getCyclesCombs(FMC,Cycle,NewK,CycleComb),!.


%getCycleFromFMC(-ListOfCycles:List)
%ListOfCycles contains combination of cycles from inputed FMC that can create inputed Cycle
getCycleFromFMC(ListOfCycles):- read_cycles(Cycle,FMC), translate_into_edges(Cycle,CycleEdge),
    findLengthOfList(FMC,0,FMCLen),getCyclesCombs(FMC,CycleEdge,FMCLen,ListOfCycles),!.



%task 5

%get_number_val(-Result:List,+N:integer)
%Result contains list of numbers with length N
get_number_val([],0):-!.
get_number_val([Val|Tail],N):-read(Val),NewN is N-1,get_number_val(Tail,NewN),!.

%put_euristic(+Vertexes:List,+Euristec:List)
%Add Euristec elements to every vertex in Vertexes
put_euristic([],[]):-!.
put_euristic([Vert|TailVertexes],[Euristec|EurTail]):-assert(euristec(Vert,Euristec)),put_euristic(TailVertexes,EurTail),!.

:-dynamic vertLen/2.
:-dynamic closedVert/1.
:-dynamic euristec/2.
:-dynamic betterWayFrom/2.

%updateLenghts(+CurrentVert:atom,+CurrentWay:integer,+Edges:List,+Weights:List)
%Update all ways according to A algorithm
updateLenghts(_,_,[],[]):-!.
updateLenghts(CurrentVert,CurrentWay,[[CurrentVert,Vertex]|TailEdges],[_|WeightsTail]):-closedVert(Vertex),
    updateLenghts(CurrentVert,CurrentWay,TailEdges,WeightsTail),!.
updateLenghts(CurrentVert,CurrentWay,[[CurrentVert,Vertex]|TailEdges],[W|WeightsTail]):-vertLen(Vertex,VertWayLen), euristec(Vertex,Eur),VertWayLen > CurrentWay + W + Eur,
    retract(vertLen(Vertex,VertWayLen)),NewWayLength is CurrentWay + W + Eur, assert(vertLen(Vertex,NewWayLength)), retract(betterWayFrom(Vertex,_)),assert(betterWayFrom(Vertex,CurrentVert)),
    updateLenghts(CurrentVert,CurrentWay,TailEdges,WeightsTail),!.
updateLenghts(CurrentVert,CurrentWay,[[CurrentVert,Vertex]|TailEdges],[_|WeightsTail]):-vertLen(Vertex,_),updateLenghts(CurrentVert,CurrentWay,TailEdges,WeightsTail),!.
updateLenghts(CurrentVert,CurrentWay,[[CurrentVert,Vertex]|TailEdges],[W|WeightsTail]):-euristec(Vertex,Eur),VertWayLen is CurrentWay + W + Eur, assert(vertLen(Vertex,VertWayLen)),
    updateLenghts(CurrentVert,CurrentWay,TailEdges,WeightsTail),assert(betterWayFrom(Vertex,CurrentVert)),!.
updateLenghts(CurrentVert,CurrentWay,[_|TailEdges],[_|WeightsTail]):- updateLenghts(CurrentVert,CurrentWay,TailEdges,WeightsTail),!.

%getMinVertex(-MinVertex:atom,+CurrentMinVertex:atom,+Vertexes:List)
%MinVertex contains vertex from Vertexes with min way
getMinVertex(MinVertex,MinVertex,[]):-!.
getMinVertex(MinVertex,CurrentMinVertex,[Vert|VertTail]):- vertLen(Vert,VertWayLen),vertLen(CurrentMinVertex,CurrentMinWayLen),
    VertWayLen < CurrentMinWayLen, NewCurrentMin is Vert,
    getMinVertex(MinVertex,NewCurrentMin,VertTail),!.
getMinVertex(MinVertex,CurrentMinVertex,[_|VertTail]):- getMinVertex(MinVertex,CurrentMinVertex,VertTail),!.

%a_alg(+CurrentVert:atom,+Goal:atom,+Vertexes:List,+Edges:List,+Weights:List,+Euristec:List,-ShortWay:integer)
%ShortWay contains length of shortest way
a_alg(Goal,Goal,_,_,_,_,ShortWay):-vertLen(Goal,ShortWay),!.
a_alg(CurrentVert,Goal,Vertexes,Edges,Weights,Euristec,ShortWay):-
    assert(closedVert(CurrentVert)),delete_elem(Vertexes,CurrentVert,NewVert),
    vertLen(CurrentVert,CurrentLen),updateLenghts(CurrentVert,CurrentLen,Edges,Weights),retract(vertLen(CurrentVert,CurrentLen)),
    vertLen(RandomVert,_), getMinVertex(NextVertex,RandomVert,NewVert),
    a_alg(NextVertex,Goal,NewVert,Edges,Weights,Euristec,ShortWay),!.

%a_alg_s(+Start:atom,+Goal:atom,+Vertexes:List,+Edges:List,+Weights:List,+Euristec:List,-ShortWay:integer)
%ShortWay contains length of shortest way
a_alg_s(Goal,Goal,_,_,_,_,0):-!.
a_alg_s(Start,Goal,Vertexes,Edges,Weights,Euristec,ShortWay):-
    put_euristic(Vertexes,Euristec),
    assert(vertLen(Start,0)),updateLenghts(Start,0,Edges,Weights),retract(vertLen(Start,0)),
    assert(closedVert(Start)),delete_elem(Vertexes,Start,NewVert),
    vertLen(RandomVert,_), getMinVertex(NextVertex,RandomVert,NewVert),
    a_alg(NextVertex,Goal,NewVert,Edges,Weights,Euristec,ShortWay),!.

%getData(-Vertexes:List,-Edges:List,-Weights:List,-Euristec:List,-Start:atom,-Goal:atom)
%Vertexes contains inputed vertexes
%Edges contains inputed Edges
%Weights contains inputed weights
%Euristec contains inputed euristec values
%Start contains start vertex
%Goal contains goal vertex
getData(Vertexes,Edges,Weights,Euristec,Start,Goal):-write("Input graph"),nl,get_graph(Vertexes,Edges),findLengthOfList(Edges,0,N),findLengthOfList(Vertexes,0,K),
    write("Input weights"),nl,get_number_val(Weights,N),
    write("Input euristic"),nl,get_number_val(Euristec,K),
    read_str(_),
    write("Input start vert"),nl,read_str(StartCodes),name(Start,StartCodes),
    write("Input goal vert"),nl,read_str(GoalCodes),name(Goal,GoalCodes),!.

%getBackWay(+Start:atom,+Vertex:atom,-ShortWayReverse:List)
%ShortWayReverse contains reverse shortest way
getBackWay(Start,Start,[]):-!.
getBackWay(Start,Vertex,[PreviousVertex|BackWayTail]):- betterWayFrom(Vertex,PreviousVertex), getBackWay(Start,PreviousVertex,BackWayTail),!.

%getShortestWay(-ShortWayLength:integer,-ShortWay:List)
%ShortWayLength contains length of shortest way
%ShortWay contains short way
getShortestWay(ShortWayLength,ShortWay):-retractall(euristec(_,_)),getData(Vertexes,Edges,Weights,Euristec,Start,Goal),
    retractall(closedVert(_)),retractall(vertLen(_,_)),retractall(betterWayFrom(_,_)),
    a_alg_s(Start,Goal,Vertexes,Edges,Weights,Euristec,ShortWayLength),getBackWay(Start,Goal,ShortWayReverse),append([Goal],ShortWayReverse,ShortWayReverseFull),reverse(ShortWayReverseFull,ShortWay),!.


%task 8

:-dynamic vertexFunc/2.

%placePerm(+Vertexes:List,+VertexPerm:List)
% Create pairs of VertexPerm elements and Vertexes elements
placePerm([],[]):-!.
placePerm([Vert1|V1Tail],[El|PermTail]):- assert(vertexFunc(Vert1,El)), placePerm(V1Tail,PermTail),!.

%check_edge_number(+Vert:atom,+Edges1:List,+Edges2:List,-NewEdges1:List,-NewEdges2:List)
%NewEdges1 contains elements of Edges1 without equal edges from Edges2
%NewEdges2 contains elements of Edges2 without equal edges from Edges1
check_edge_number(Vert,[],ResultEdges2,[],ResultEdges2):-!.
check_edge_number(Vert,[[Vert,EndVert]|Edges1Tail],Edges2,ResultEdges1,ResultEdges2):- vertexFunc(EndVert,EndVertP), vertexFunc(Vert,VertP),
    in_list1(Edges2,[VertP,EndVertP]), delete_elem(Edges2,[VertP,EndVertP],NewEdges2),
        check_edge_number(Vert,Edges1Tail,NewEdges2,ResultEdges1,ResultEdges2),!.
check_edge_number(Vert,[Edge1|Edges1Tail],Edges2,[Edge1|ResultEdges1],ResultEdges2):- check_edge_number(Vert,Edges1Tail,Edges2,ResultEdges1,ResultEdges2),!.


%check_vertex_auto(+Vertexes:List,+Edges1:List,+Edges2:List)
%True if current placement in vertexFunc is automorphism of graph with vertexes from Vertexes and edges from Edges1
check_vertex_auto([],[],[]):-!.
check_vertex_auto([Vert|VertTail],Edges1,Edges2):- check_edge_number(Vert,Edges1,Edges2,NewEdges1,NewEdges2),check_vertex_auto(VertTail,NewEdges1,NewEdges2),!.

%check_if_auto
%True if second inputed graph is automorphism of first inputed graph
check_if_auto:-write("Input first graph"),nl,get_graph(V1,E1),
    write("Input second graph"),nl,get_graph(V2,E2),getPerm(V2,V2Perm),
    retractall(vertexFunc(_,_)), placePerm(V1,V2Perm),
    check_vertex_auto(V1,E1,E2),!.
