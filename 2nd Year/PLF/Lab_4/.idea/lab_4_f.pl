check([], _).
check([(H,CH),(S,CS)|T], Edges):-
    member((H,S), Edges),
    CH \== CS,
    check([(_,CS)|T], Edges).

allcombinations(_, _, _, N, N, _).
allcombinations([H|T], Edges, [CH|CT], N, CI, Ans):-
    NI is CI + 1,
    allcombinations(T, Edges, [CH|CT], N, NI, [Ans|(H,CH)])
