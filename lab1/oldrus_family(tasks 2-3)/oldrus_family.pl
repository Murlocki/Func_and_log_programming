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
% son(?PossibleSon: atom,+PossibleParent: atom) is det
% True, if PossibleSon is a son of PossibleParent
son(PossibleSon,PossibleParent):- man(PossibleSon), parent(PossibleParent,PossibleSon), nl.

% son(+InputParent:atom) is det
% Print first found son of InputParent
son(InputParent):- son(PossibleSon,InputParent), print(PossibleSon), nl, !.

% 2.2)
% sister(+PossibleSister: atom, +InputPerson: atom) is det
% True, if PossibleSister is a sister of InputPerson
sister(PossibleSister, InputPerson):- woman(PossibleSister), \+ (PossibleSister=InputPerson), parent(CommonParent,PossibleSister),parent(CommonParent,InputPerson),nl,!.

% sisters(+InputPerson:atom) is failure
% Print all sisters of InputPerson
sisters(InputPerson):- woman(PossibleSister),sister(PossibleSister,InputPerson), print(PossibleSister),nl, fail.


% Task 3

%1)
% grand_par(+PossibleGrCh:atom, +PossibleGrPar:atom) is det
% True, if PossibleGrCh is a grandchild of PossibleGrPar
grand_par(PossibleGrCh,PossibleGrPar):- parent(PossibleParent,PossibleGrCh), parent(PossibleGrPar,PossibleParent).

% grand_da(?PossibleGrDa:atom, +PossibleGrPar:atom) is det
% True, if PossibleGrDa is a granddaughter of PossibleGrPar
grand_da(PossibleGrDa,PossibleGrPar):- woman(PossibleGrDa), grand_par(PossibleGrDa,PossibleGrPar).

% grand_date(+InputPerson:atom) is failure
% Print all granddaughters of InputPerson
grand_dats(InputPerson):- grand_da(PossibleGrDa,InputPerson),print(PossibleGrDa), nl, fail.

%2)
% grand_so(+PossibleGrS:atom, +PossibleGrPar:atom) is det
% True, if PossibleGrS is a grandson of PossibleGrParent
grand_so(PossibleGrS,PossibleGrPar):- man(PossibleGrS), grand_par(PossibleGrS, PossibleGrPar).
% grand_ma_and_son(+PossibleGrMa:atom,+PossibleGrs:atom) is det
% True, if PossibleGrMa is a grandmother of grandson PossibleGrS
grand_ma_and_son(PossibleGrMa,PossibleGrS):- woman(PossibleGrMa), grand_so(PossibleGrS,PossibleGrMa).
% grand_ma_and_son(+PossibleGrS:atom,+PossibleGrMa:atom) is det
% True, if PossibleGrS is a grandson of grandmother PossibleGrMa
grand_ma_and_son(PossibleGrS,PossibleGrMa):- woman(PossibleGrMa), grand_so(PossibleGrS,PossibleGrMa).

%3)
% siblings(+PossibleSib1:atom,+PossibleSib2:atom) is det
% True if PossibleSib1 is a sibling of PossibleSib2
siblings(PossibleSib1,PossibleSib2):- (PossibleSib1 \== PossibleSib2),parent(CommonParent,PossibleSib1),parent(CommonParent,PossibleSib2),!.

% nibling(+PossibleNib: atom, PossibleAU: atom) is det
% True if PossibleNib is a nibling of PossibleAU
nibling(PossibleNib,PossibleAU):- parent(NibParent,PossibleNib),siblings(NibParent,PossibleAU).

% nephew(+PossibleNephew: atom, PossibleAU:atom) is det
% True if PossibleNephew is a nephew of PossibleAU
nephew(PossibleNephew,PossibleAU):- man(PossibleNephew),nibling(PossibleNephew,PossibleAU).

% nephews(+InputPerson: atom) is failure
% Print all nephews of InputPerson
nephews(InputPerson):- nephew(PossibleNephew,InputPerson),print(PossibleNephew),nl,fail.

% Task 3 pure facts

%1)
% grand_da(+PossibleGrDa:atom, +PossibleGrPar:atom) is det
% True, if PossibleGrDa is a granddaughter of PossibleGrPar
grand_da_p(PossibleGrDa,PossibleGrPar):- woman(PossibleGrDa),parent(PossibleParent,PossibleGrDa), parent(PossibleGrPar,PossibleParent).

% grand_date(+InputPerson:atom) is failure
% Print all granddaughters of InputPerson
grand_dats_p(InputPerson):- woman(PossibleGrDa),parent(PossibleParent,PossibleGrDa), parent(InputPerson,PossibleParent),print(PossibleGrDa), nl, fail.


%2)
% grand_ma_and_son(+PossibleGrMa:atom,+PossibleGrs:atom) is det
% True, if PossibleGrMa is a grandmother of grandson PossibleGrS
grand_ma_and_son_p(PossibleGrMa,PossibleGrS):- woman(PossibleGrMa), man(PossibleGrS), parent(PossibleParent,PossibleGrS),parent(PossibleGrMa,PossibleParent).
% grand_ma_and_son(+PossibleGrS:atom,+PossibleGrMa:atom) is det
% True, if PossibleGrS is a grandson of grandmother PossibleGrMa
grand_ma_and_son_p(PossibleGrS,PossibleGrMa):- woman(PossibleGrMa),  man(PossibleGrS), parent(PossibleParent,PossibleGrS),parent(PossibleGrMa,PossibleParent).

%3)
%nephew(+PossibleNephew: atom, PossibleAU:atom) is det
% True if PossibleNephew is a nephew of PossibleAU
nephew_p(PossibleNephew,PossibleAU):- man(PossibleNephew),parent(PossibleNephewParent,PossibleNephew),PossibleNephewParent\==PossibleAU,parent(CommonGrandparent,PossibleNephewParent),parent(CommonGrandparent,PossibleAU).

% nephews(+InputPerson: atom) is failure
% Print all nephews of InputPerson
nephews_p(InputPerson):- parent(CommonGrpar,InputPerson),!,parent(CommonGrpar,PossibleNephewParent),InputPerson\==PossibleNephewParent, parent(PossibleNephewParent,PossibleNephew),man(PossibleNephew),print(PossibleNephew),nl,fail.


