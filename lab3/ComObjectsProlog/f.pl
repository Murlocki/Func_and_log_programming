member2(El,[H|T]):-member1(T,El,H).
member1([H|T],EL,EL).
member1([H|T],El,_):-member1(T,El,H).

in_list([El|_],El).
in_list([H|T],El):-in_list(T,El).
