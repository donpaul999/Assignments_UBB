//
// Created by Paul Colta on 23/05/2020.
//

#include "Service.h"

std::vector<int> Service::findHamiltonian(UndirectedGraph graph) {

}


bool isSafe(int v, UndirectedGraph graph, int path[], int pos)
{
    /* Check if this vertex is an adjacent
    vertex of the previously added vertex. */
    if (graph [path[pos - 1]][ v ] == 0)
        return false;

    /* Check if the vertex has already been included.
    This step can be optimized by creating
    an array of size V */
    for (int i = 0; i < pos; i++)
        if (path[i] == v)
            return false;

    return true;
}

/* A recursive utility function
to solve hamiltonian cycle problem */
bool hamCycleUtil(bool graph[V][V],
                  int path[], int pos)
{
    /* base case: If all vertices are
    included in Hamiltonian Cycle */
    if (pos == V)
    {
        // And if there is an edge from the
        // last included vertex to the first vertex
        if (graph[path[pos - 1]][path[0]] == 1)
            return true;
        else
            return false;
    }

    // Try different vertices as a next candidate
    // in Hamiltonian Cycle. We don't try for 0 as
    // we included 0 as starting point in hamCycle()
    for (int v = 1; v < V; v++)
    {
        /* Check if this vertex can be added
        // to Hamiltonian Cycle */
        if (isSafe(v, graph, path, pos))
        {
            path[pos] = v;

            /* recur to construct rest of the path */
            if (hamCycleUtil (graph, path, pos + 1) == true)
                return true;

            /* If adding vertex v doesn't lead to a solution,
            then remove it */
            path[pos] = -1;
        }
    }

    /* If no vertex can be added to
    Hamiltonian Cycle constructed so far,
    then return false */
    return false;
}

/* This function solves the Hamiltonian Cycle problem
using Backtracking. It mainly uses hamCycleUtil() to
solve the problem. It returns false if there is no
Hamiltonian Cycle possible, otherwise return true
and prints the path. Please note that there may be
more than one solutions, this function prints one
of the feasible solutions. */
bool hamCycle(bool graph[V][V])
{
    int *path = new int[V];
    for (int i = 0; i < V; i++)
        path[i] = -1;

    /* Let us put vertex 0 as the first vertex in the path.
    If there is a Hamiltonian Cycle, then the path can be
    started from any point of the cycle as the graph is undirected */
    path[0] = 0;
    if (hamCycleUtil(graph, path, 1) == false )
    {
        cout << "\nSolution does not exist";
        return false;
    }

    printSolution(path);
    return true;
}
