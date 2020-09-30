//
// Created by Paul Colta on 23/05/2020.
//

#include "Service.h"

std::vector<int> Service::findHamiltonian(UndirectedGraph graph) {
    return hamCycle(graph);
}


bool isSafe(int v, std::vector<std::vector<bool>> graph, std::vector<int> path, int pos)
{

    if (graph[path[pos - 1]][ v ] == 0)
        return false;
    for (int i = 0; i < pos; i++)
        if (path[i] == v)
            return false;
    return true;
}

bool hamCycleUtil(UndirectedGraph graph,
                  std::vector<int> &path, int pos)
{   if (pos == graph.matrix.size())
    {
        if (graph.matrix[path[pos - 1]][path[0]] == 1)
            return true;
        else
            return false;
    }
    for (int v = 1; v < graph.matrix.size(); v++)
    {
        if (isSafe(v, graph.matrix, path, pos))
        {
            path[pos] = v;

            if (hamCycleUtil (graph, path, pos + 1) == true)
                return true;
            path[pos] = -1;
        }
    }

    return false;
}

std::vector<int> Service::hamCycle(UndirectedGraph graph)
{
    std::vector<int> path(graph.matrix.size(), -1);
    path[0] = 0;
    hamCycleUtil(graph, path, 1);
    return path;
}
