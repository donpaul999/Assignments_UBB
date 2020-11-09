% 11. Colouring a map. n countries are given; write a
% predicate to determine all possibilities of colouring n
% countries with m colours, such that two adjacent countries not
% having the same colour.
% countries:

%is_inside(E:Elem, L:List)
% (i,i) deterministic
% 0, if l is empty
% 1, if l1 == E
% is_inside(E, l2..ln) otherwise
is_inside(H, [H|_]):- !.
is_inside(E, [_|T]):-
    is_inside(E, T).

colorMap(Countries, Edges, Colors, Ans) :-
  findall(hasColor(X, _), member(X, Countries), Ans),
  createConstraint(Edges,Ans),
  colorCountries(Colors, Ans).

%createConstraint(E:List)
% 1, if L is empty
% 0, if C1 == C2, where Ci is Vi's color, where V is part of an edge
% createConstraint(E2...En)
createConstraint([],_).
createConstraint([(V1,V2)|T],Ans):-
  is_inside(hasColor(V1,C1),Ans),
  is_inside(hasColor(V2,C2),Ans),
  dif(C1,C2),
  createConstraint(T,Ans).

colorCountries(_, []).
colorCountries(Colors, [hasColor(_,C)|CC]) :-
  member(C, Colors),
  colorCountries(Colors, CC).