%in_list(+InputList:List,?El:atom)
%True if El is in InputList
in_list([El|_],El).
in_list([_|T],El):-in_list(T,El).

%razm_povt(+Alphabet:List,+Ncur:integer,+RazmCur:List,-Razm:List)
%Return first placement with repeats
%Otherwise return next placements with repeats
razm_povt(_,0,Razm,Razm):-!.
razm_povt(Alphabet,Ncur,RazmCur,Razm):- in_list(Alphabet,El),NNew is Ncur - 1, razm_povt(Alphabet,NNew,[El|RazmCur],Razm).

