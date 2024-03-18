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
getCyclesCombs(FMC,Cycle,0,[]):-!,fail.
getCyclesCombs(FMC,Cycle,K,CycleComb):-comb(FMC,CycleComb,K), translate_into_edges_list(CycleComb,CycleCombEdges),
    getCycleFromComb(CycleCombEdges,[],ResultCycleFromComb),check_cycle(Cycle,ResultCycleFromComb),!.
getCyclesCombs(FMC,Cycle,K,CycleComb):-NewK is K - 1,getCyclesCombs(FMC,Cycle,NewK,CycleComb),!.


%getCycleFromFMC(-ListOfCycles:List)
%ListOfCycles contains combination of cycles from inputed FMC that can create inputed Cycle
getCycleFromFMC(ListOfCycles):- read_cycles(Cycle,FMC), translate_into_edges(Cycle,CycleEdge),
    findLengthOfList(FMC,0,FMCLen),getCyclesCombs(FMC,CycleEdge,FMCLen,ListOfCycles),!.

