% 11. “Colouring” a map. n countries are given; write a predicate to determine all possibilities of colouring n
% countries with m colours, such that two adjacent countries not having the same colour

allColorsForCity([],_,[]):- !.
allColorsForCity([H|T], City, [cityColor(City,H)|Ans]):-
    allColorsForCity(T, City, Ans).

colorCity(_, [], []):- !.
colorCity(Colors, [H|T], [R|Ans]) :-
  allColorsForCity(Colors, H, R),
  colorCity(Colors, T, Ans).

go2:-
    allColorsForCity([red,yellow, blue], x, R),
    print(R).

go3:-
    colorCity([red,yellow,blue], [a,b,c], R),
    print(R).









