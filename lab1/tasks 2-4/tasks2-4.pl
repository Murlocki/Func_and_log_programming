man(voeneg).
man(ratibor).
man(boguslav).
man(velerad).
man(duhovlad).
man(svyatoslav).
man(dobrozhir).
man(bogomil).
man(zlatomir).

woman(goluba).
woman(lubomila).
woman(bratislava).
woman(veslava).
woman(zhdana).
woman(bozhedara).
woman(broneslava).
woman(veselina).
woman(zdislava).

parent(voeneg,ratibor).
parent(voeneg,bratislava).
parent(voeneg,velerad).
parent(voeneg,zhdana).

parent(goluba,ratibor).
parent(goluba,bratislava).
parent(goluba,velerad).
parent(goluba,zhdana).

parent(ratibor,svyatoslav).
parent(ratibor,dobrozhir).
parent(lubomila,svyatoslav).
parent(lubomila,dobrozhir).

parent(boguslav,bogomil).
parent(boguslav,bozhedara).
parent(bratislava,bogomil).
parent(bratislava,bozhedara).

parent(velerad,broneslava).
parent(velerad,veselina).
parent(veslava,broneslava).
parent(veslava,veselina).

parent(duhovlad,zdislava).
parent(duhovlad,zlatomir).
parent(zhdana,zdislava).
parent(zhdana,zlatomir).

% Task 2

% 2.1)
% son(+PossibleSon: atom,+PossibleParent: atom) is det
% True, if PossibleSon is a son of PossibleParent
son(PossibleSon,PossibleParent):- man(PossibleSon), parent(PossibleParent,PossibleSon), nl.

% son(+InputParent:atom) is det
% Print first found son of InputParent
son(InputParent):- son(PossibleSon,InputParent), print(PossibleSon), nl, !.

% 2.2)
% sister(+PossibleSister: atom, +InputPerson: atom) is det
% True, if PossibleSister is a sister of InputPerson
sister(PossibleSister, InputPerson):- woman(PossibleSister), \+ (PossibleSister=InputPerson), parent(CommonParent,PossibleSister),parent(CommonParent,InputPerson),nl,!.

