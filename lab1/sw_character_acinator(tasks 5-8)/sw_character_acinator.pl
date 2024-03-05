% Find person basing on answers to questions
% main() is nondet
% True if we find person basing on answers to questions
main :-
    retractall(askFalseQuestions(_)),
    retractall(asked(_,_)),
    person(Character),
    !,
    nl,
    write('The character is '), write(Character), write(.), nl.
% Works when we cannot find the person
% main() is det
main :-
    nl,
    write('The character cannot be recognized.'), nl.





% Create question for acinator
% question(+Object: atom,+Answer:atom) is det
% True, if query(Prompt,Answer) is true
question(human,Answer):-query('Is character a human?',Answer).
question(positive,Answer):-query('Is it a positive character?',Answer).
question(force,Answer):-query('Is character force sensitive',Answer).
question(politician,Answer):-query('Is character a politician',Answer).
question(military,Answer):-query('Has character ever been a military',Answer).
question(traitor,Answer):-query('Has this character ever been a traitor',Answer).
question(deathStarPlans,Answer):-query('Does this character have death star plans',Answer).
question(mother_talzin,Answer):-query('Is this character a relative of Mother Talzin',Answer).
% Create persons for acinator
% person(+Person: atom) is det
% True, if all questions are true
person(darth_Revan):-
    question(human,y),
    question(positive,y),
    question(force,y),
    question(politician,y),
    question(traitor,y),
    question(military,y),
    question(deathStarPlans,n),
     question(mother_talzin,n)
    .
%2
person(anakin_Skywalker):-
    question(human,y),
    question(force,y),
    question(traitor,y),
    question(military,y),
    question(positive,y),
    question(politician,y),
    question(deathStarPlans,n),
     question(mother_talzin,n)
    .
%3
person(count_duku):-
    question(human,y),
    question(force,y),
    question(politician,y),
    question(positive,y),
    question(traitor,y),
    question(military,n),
    question(deathStarPlans,n),
     question(mother_talzin,n)
    .
%4
person(darth_Malek):-
    question(force,y),
    question(politician,y),
    question(traitor,y),
    question(military,y),
    question(human,y),
    question(positive,n),
    question(deathStarPlans,n),
     question(mother_talzin,n)
    .
%5
person(darth_Malgus):-
    question(human,y),
    question(force,y),
    question(traitor,y),
    question(military,y),
    question(positive,n),
    question(politician,n),
    question(deathStarPlans,n),
     question(mother_talzin,n)
    .
%6
person(snoke):-
    question(human,y),
    question(force,y),
    question(politician,y),
    question(military,n),
    question(positive,n),
    question(traitor,n),
    question(deathStarPlans,n),
     question(mother_talzin,n)
    .
% 7
person(meme_youngling):-
    question(force,y),
    question(human,y),
    question(positive,y),
    question(politician,n),
    question(military,n),
    question(traitor,n),
    question(deathStarPlans,n),
     question(mother_talzin,n)
.
% 8
person(mandolorian_minister):-
    question(human,y),
    question(politician,y),
    question(traitor,y),
    question(force,n),
    question(positive,n),
    question(military,n),
    question(deathStarPlans,n),
     question(mother_talzin,n)
.
% 9
person(farmer_clone):-
    question(military,y),
    question(human,y),
    question(traitor,y),
    question(force,n),
    question(positive,n),
    question(politician,n),
    question(deathStarPlans,n),
     question(mother_talzin,n)
.
% 10
person(daughter):-
    question(force,y),
    question(positive,y),
    question(human,n),
    question(politician,n),
    question(military,n),
    question(traitor,n),
    question(deathStarPlans,n),
     question(mother_talzin,n)
.
% 11
person(savage_oppress):-
    question(military,y),
    question(force,y),
    question(human,n),
    question(positive,n),
    question(politician,n),
    question(traitor,n),
    question(deathStarPlans,n),
     question(mother_talzin,n)
    .
% 12
person(clone):-
    question(human,y),
    question(military,y),
    question(force,n),
    question(positive,n),
    question(politician,n),
    question(traitor,n),
    question(deathStarPlans,n),
     question(mother_talzin,n)
    .
% 13
person(grandmof_tarkin):-
    question(human,y),
    question(politician,y),
    question(force,n),
    question(positive,n),
    question(military,n),
    question(traitor,n),
    question(deathStarPlans,n),
     question(mother_talzin,n).
% 14
person(hask):-
    question(human,y),
    question(traitor,y),
    question(force,n),
    question(positive,n),
    question(politician,n),
    question(military,n),
    question(deathStarPlans,n),
     question(mother_talzin,n).
% 15
person(b1_droid):-
    question(military,y),
    question(force,n),
    question(human,n),
    question(positive,n),
    question(politician,n),
    question(traitor,n),
    question(deathStarPlans,n),
     question(mother_talzin,n)
    .
% 16
person(senator):-
    question(politician,y),
    question(force,n),
    question(human,n),
    question(positive,n),
    question(military,n),
    question(traitor,n),
    question(deathStarPlans,n),
     question(mother_talzin,n)
    .
% 17
person(human_specie):-
    question(human,y),
    question(force,n),
    question(positive,n),
    question(politician,n),
    question(military,n),
    question(traitor,n),
    question(deathStarPlans,n),
     question(mother_talzin,n)
    .
% 18
person(r2d2):-
    question(positive,y),
    question(force,n),
    question(human,n),
    question(politician,n),
    question(military,n),
    question(traitor,n),
    question(deathStarPlans,y)
    .
% 19
person(c3po):-
    question(positive,y),
    question(force,n),
    question(human,n),
    question(politician,n),
    question(military,n),
    question(traitor,n),
    question(deathStarPlans,n),
    question(mother_talzin,n)
    .
% 20
person(random_mercenary):-
    question(traitor,y),
    question(force,n),
    question(human,n),
    question(positive,n),
    question(politician,n),
    question(military,n),
    question(deathStarPlans,n),
    question(mother_talzin,n).
% 21
person(maul):-
    question(force,y),
    question(human,n),
    question(positive,n),
    question(politician,n),
    question(military,n),
    question(traitor,n),
    question(deathStarPlans,n),
    question(mother_talzin,y).
%22
person(ventress):-
    question(force,y),
    question(human,n),
    question(positive,n),
    question(politician,n),
    question(military,n),
    question(traitor,n),
    question(deathStarPlans,n),
    question(mother_talzin,n).



% Check if there is more than 1 person that fits to current question
% check_persons() is nondet
% Always true
check_persons:-(person(FirstPerson),person(SecondPerson),FirstPerson\==SecondPerson)->(retractall(askFalseQuestions(_)),true);true.


% Add answers to questions and check if input answer is equal to
% user_reply
%  query(+Prompt:atom, +Answer:atom) is det
%  True if Answer is equal to Reply
% Otherwise true if askFalseQuestions(1) is true
query(Prompt,Answer) :-
    (   (asked(Prompt, Reply);askFalseQuestions(1))-> true
    ;   nl, write(Prompt), write(' (y/n)? '),
        read(X),(X = y -> Reply = y ; Reply = n),
        assert(askFalseQuestions(1)),
        assert(asked(Prompt, Reply)),
        check_persons
    ),
    (asked(Prompt,Reply))->(Reply=Answer);
    (askFalseQuestions(1)->true).

