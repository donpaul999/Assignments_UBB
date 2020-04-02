#include "Service.h"

Service::Service()
{
}

Service::Service(DIRECT_GRAPH graph)
{
    this->graph = graph;
}

vector<int> Service::bfsFromEndToStart(int source)
{
    queue<int> q;
    vector<int>p(graph.getNumberOfVertices());
    vector<int>l(graph.getNumberOfVertices());
    map<int, bool>visited;
    q.push(source);
    visited[source] = 1;
    l[source] = 0;
    while (!q.empty()) {
        int x = q.front();
        q.pop();
        for (auto it = graph.parseEdgesIn(x).first; it != graph.parseEdgesIn(x).second; ++it)
            if (visited[*it] == 0) {
                q.push(*it);
                visited[*it] = 1;
                l[*it] = l[x] + 1;
                p[*it] = x;
            }
    }
    return p;
}
