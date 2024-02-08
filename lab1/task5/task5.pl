% Create question for acinator
% question(+Object: atom) is det
% True, if query(Prompt) is true
question(human):-query('Is character a human?').
question(positive):-query('Is it a positive character?').
question(force):-query('Is character force sensitive').
question(politician):-query('Is character a politician').
question(military):-query('Has character ever been a military').
question(traitor):-query('Has this character ever been a traitor').

% Create persons for acinator
% person(+Person: atom) is det
% True, if all questions are true
person(darth_Revan):-
    question(human),
    question(positive),
    question(force),
    question(politician),
    question(traitor),
    question(military)
    .
%2
person(anakin_Skywalker):-
    question(human),
    question(force),
    question(traitor),
    question(military),
    question(positive)
    .
%3
person(count_duku):-
    question(human),
    question(force),
    question(politician),
    question(positive),
    question(traitor)
    .
%4
person(darth_Malek):-
    question(force),
    question(politician),
    question(traitor),
    question(military),
    question(human)
    .
%5
person(darth_Malgus):-
    question(human),
    question(force),
    question(traitor),
    question(military)
    .
%13
person(snoke):-
    question(human),
    question(force),
    question(politician)
    .
% 18
person(meme_youngling):-
    question(force),
    question(human),
    question(positive).
%20
person(mandolorian_minister):-
    question(human),
    question(politician),
    question(traitor).
%19
person(farmer_clone):-
    question(military),
    question(human),
    question(traitor).
% 17
person(daughter):-
    question(force),
    question(positive).
%6
person(general_Grivous):-
    question(military),
    question(lightsaber)
    .
%7
person(clone):-
    question(human),
    question(military)
    .
%12
person(grandmof_tarkin):-
    question(human),
    question(politician).
%16
person(hask):-
    question(human),
    question(traitor).
%8
person(b1_droid):-
    question(military)
    .
%9
person(senator):-
    question(politician)
    .
%10
person(human_specie):-
    question(human)
    .
%11
person(r2d2):-
    question(positive)
    .
%14
person(random_mercenary):-
    question(traitor).
%15
person(maul):-
    question(force).

% Get answer for question
% query(+Prompt: atom)
query(Prompt) :- (
    asked(Prompt,Reply) -> true
    ;  nl, write(Prompt), write('(y/n)'),
    read(X), (X = y->Reply=y;Reply=n),
    assert(asked(Prompt,Reply))
), Reply=y.









