% 11. “Colouring” a map. n countries are given; write a predicate to determine all possibilities of colouring n
% countries with m colours, such that two adjacent countries not having the same colour
%SOL 1

color(red).
color(blue).
color(green).

country(romania).
country(france).
country(russia).


edge(romania, france).
edge(france, russia).


colorMap(Ans) :-
  findall((X, Y), edge(X, Y), Edges),
  findall(X, country(X), Countries),
  findall(hasColor(X, _), member(X, Countries), Ans),
  createConstraint(Edges,Ans),
  colorCountries(Ans).

createConstraint([],_).
createConstraint([(V1,V2)|T],Ans):-
  member(hasColor(V1,C1),Ans),
  member(hasColor(V2,C2),Ans),
  dif(C1,C2),
  createConstraint(T,Ans).


colorCountries([]).
colorCountries([hasColor(_,C)|Nodes]) :-
  color(C),
  colorCountries(Nodes).
