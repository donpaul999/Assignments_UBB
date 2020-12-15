edge(a, b).
edge(b, c).
edge(c, d).

color(red).
color(green).
color(blue).
color(orange).

city(a).
city(b).
city(c).
city(d).

%is_inside(L:List, E:Element)
% (i,i) deterministic
% 0, if l is empty
% 1, if l1 == E
% is_inside(E, l2..ln) otherwise

is_inside(H, [H|_]):- !.
is_inside(E, [_|T]):-
    is_inside(E, T).


colorMap(Ans) :-
  findall((X, Y), edge(X, Y), Edges),
  findall(X, city(X), Cities),
  findall(hasColor(X, _), member(X, Cities), Ans),
  createConstraint(Edges,Ans),
  colorNodes(Ans).

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

colorNodes([]).
colorNodes([hasColor(_,C)|Nodes]) :-
  color(C),
  colorNodes(Nodes).