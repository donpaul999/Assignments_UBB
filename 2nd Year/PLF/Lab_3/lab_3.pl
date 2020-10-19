% 5.
% a. Substitute all occurrences of an element of a list with all the elements of another list.
% Eg. subst([1,2,1,3,1,4],1,[10,11],X) produces X=[10,11,2,10,11,3,10,11,4].
% b. For a heterogeneous list, formed from integer numbers and list of numbers,
% replace in every sublist all occurrences of the first element from sublist it a new given list.
% Eg.: [1, [4, 1, 4], 3, 6, [7, 10, 1, 3, 9], 5, [1, 1, 1], 7] si [11, 11] =>
% [1, [11, 11, 1, 11, 11], 3, 6, [11, 11, 10, 1, 3, 9], 5, [11 11 11 11 11 11], 7]

% a.
% inserare(l1...ln, list) =
% 	list, n = 0
% 	l1 + inserare(l2...ln, list), otherwise

% inserare(L:list, List:list, R:list)
% inserare(i, i, o)

% subst(l1...ln, e, list) =
% 	[], n = 0
% 	subst(inserare(list, l2...ln), e, list), l1 = e
% 	l1 + subst(l2...ln, e, list), l1 != e

% subst(L:list, E:number, List:list, R:list)
% subst(i, i, i, o)
inserare([], L, L).
inserare([H|T], L, [H|R]) :-
    inserare(T, L, R).

subst([], _, _, []).
subst([H|T], E, List, R) :- H =:=E,
    inserare(List, T, RI),
    subst(RI, E, List, R).
subst([H|T], E, List, [H|R]) :- H =\= E,
    subst(T, E, List, R).

% b
% heterList(l1...ln, list) =
% 	[], n = 0
% 	l1 + heterList(l2...ln), number(l1) = True
% 	subst(L1...Lm, L1, list), heterList(l2...ln), is_list(l1) = True, where l1 = L1...Lm

% heterList(L:list, R:list)
% heterList(i, o)

heterList([], _, []).
heterList([H|T], E, [H|R]) :- number(H),
    heterList(T, E, R).
heterList([[H|HT]|T], E, [HR|R]) :- subst([H|HT], H, E, HR),
    heterList(T, E, R).


go:-
    subst([1,2,1,3,1,4],1,[10,11],X),
    print(X),
    write('\n'),
    heterList([1, [4, 1, 4], 3, 6, [7, 10, 1, 3, 9], 5, [1, 1, 1], 7], [11, 11], R),
    print(R).