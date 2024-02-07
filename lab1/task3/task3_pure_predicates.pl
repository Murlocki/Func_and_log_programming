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

%1)
% grand_da(+PossibleGrDa:atom, +PossibleGrPar:atom) is det
% True, if PossibleGrDa is a granddaughter of PossibleGrPar
grand_da(PossibleGrDa,PossibleGrPar):- woman(PossibleGrDa),parent(PossibleParent,PossibleGrDa), parent(PossibleGrPar,PossibleParent).

% grand_date(+InputPerson:atom) is failure
% Print all granddaughters of InputPerson
grand_dats(InputPerson):- woman(PossibleGrDa),parent(PossibleParent,PossibleGrDa), parent(InputPerson,PossibleParent),print(PossibleGrDa), nl, fail.


%2)
% grand_ma_and_son(+PossibleGrMa:atom,+PossibleGrs:atom) is det
% True, if PossibleGrMa is a grandmother of grandson PossibleGrS
grand_ma_and_son(PossibleGrMa,PossibleGrS):- woman(PossibleGrMa), man(PossibleGrS), parent(PossibleParent,PossibleGrS),parent(PossibleGrMa,PossibleParent).
% grand_ma_and_son(+PossibleGrS:atom,+PossibleGrMa:atom) is det
% True, if PossibleGrS is a grandson of grandmother PossibleGrMa
grand_ma_and_son(PossibleGrS,PossibleGrMa):- woman(PossibleGrMa),  man(PossibleGrS), parent(PossibleParent,PossibleGrS),parent(PossibleGrMa,PossibleParent).

%3)
%nephew(+PossibleNephew: atom, PossibleAU:atom) is det
% True if PossibleNephew is a nephew of PossibleAU
nephew(PossibleNephew,PossibleAU):- man(PossibleNephew),parent(PossibleNephewParent,PossibleNephew),PossibleNephewParent\==PossibleAU,parent(CommonGrandparent,PossibleNephewParent),parent(CommonGrandparent,PossibleAU).

% nephews(+InputPerson: atom) is failure
% Print all nephews of InputPerson
nephews(InputPerson):- parent(CommonGrpar,InputPerson),!,parent(CommonGrpar,PossibleNephewParent),InputPerson\==PossibleNephewParent, parent(PossibleNephewParent,PossibleNephew),man(PossibleNephew),print(PossibleNephew),nl,fail.
