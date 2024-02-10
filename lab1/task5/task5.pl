% Create question for acinator
% question(+Object: atom) is det
% True, if query(Prompt) is true
question(human,Answer):-query('Is character a human?',Answer).
question(positive,Answer):-query('Is it a positive character?',Answer).
question(force,Answer):-query('Is character force sensitive',Answer).
question(politician,Answer):-query('Is character a politician',Answer).
question(military,Answer):-query('Has character ever been a military',Answer).
question(traitor,Answer):-query('Has this character ever been a traitor',Answer).

% Create persons for acinator
% person(+Person: atom) is det
% True, if all questions are true
person(darth_Revan):-
    question(human,y),
    question(positive,y),
    question(force,y),
    question(politician,y),
    question(traitor,y),
    question(military,y)
    .
%2
person(anakin_Skywalker):-
    question(human,y),
    question(force,y),
    question(traitor,y),
    question(military,y),
    question(positive,y),
    question(politician,y)
    .
%3
person(count_duku):-
    question(human,y),
    question(force,y),
    question(politician,y),
    question(positive,y),
    question(traitor,y),
    question(military,n)
    .
%4
person(darth_Malek):-
    question(force,y),
    question(politician,y),
    question(traitor,y),
    question(military,y),
    question(human,y),
    question(positive,n)
    .
%5
person(darth_Malgus):-
    question(human,y),
    question(force,y),
    question(traitor,y),
    question(military,y),
    question(positive,n),
    question(politician,n)
    .
%13
person(snoke):-
    question(human,y),
    question(force,y),
    question(politician,y),
    question(military,n),
    question(positive,n),
    question(traitor,n)
    .
% 18
person(meme_youngling):-
    question(force,y),
    question(human,y),
    question(positive,y),
    question(politician,n),
    question(military,n),
    question(traitor,n).
%20
person(mandolorian_minister):-
    question(human,y),
    question(politician,y),
    question(traitor,y),
    question(force,n),
    question(positive,n),
    question(military,n).
%19
person(farmer_clone):-
    question(military,y),
    question(human,y),
    question(traitor,y),
    question(force,n),
    question(positive,n),
    question(politician,n).
% 17
person(daughter):-
    question(force,y),
    question(positive,y),
    question(human,n),
    question(politician,n),
    question(military,n),
    question(traitor,n).
%6
person(savage_oppress):-
    question(military,y),
    question(force,y),
    question(human,n),
    question(positive,n),
    question(politician,n),
    question(traitor,n)
    .
%7
person(clone):-
    question(human,y),
    question(military,y),
    question(force,n),
    question(positive,n),
    question(politician,n),
    question(traitor,n)
    .
%12
person(grandmof_tarkin):-
    question(human,y),
    question(politician,y),
    question(force,n),
    question(positive,n),
    question(military,n),
    question(traitor,n).
%16
person(hask):-
    question(human,y),
    question(traitor,y),
    question(force,n),
    question(positive,n),
    question(politician,n),
    question(military,n).
%8
person(b1_droid):-
    question(military,y),
    question(force,n),
    question(human,n),
    question(positive,n),
    question(politician,n),
    question(traitor,n)
    .
%9
person(senator):-
    question(politician,y),
    question(force,n),
    question(human,n),
    question(positive,n),
    question(military,n),
    question(traitor,n)
    .
%10
person(human_specie):-
    question(human,y),
    question(force,n),
    question(positive,n),
    question(politician,n),
    question(military,n),
    question(traitor,n)
    .
%11
person(r2d2):-
    question(positive,y),
    question(force,n),
    question(human,n),
    question(politician,n),
    question(military,n),
    question(traitor,n)
    .
%14
person(random_mercenary):-
    question(traitor,y),
     question(force,n),
    question(human,n),
    question(positive,n),
    question(politician,n),
    question(military,n).
%15
person(maul):-
    question(force,y),
    question(human,n),
    question(positive,n),
    question(politician,n),
    question(military,n),
    question(traitor,n).









