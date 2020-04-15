#include "Service.h"
#include <iostream>
#include <exception>
#define INF 1000000
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

int Service::floydWarshall(int source, int target) {
    vector<vector<int>> distances;
    vector<int> linesDist;
    int i, j, k, count = 0;
    for (i = 0; i < graph.vertices.size(); ++i) {
        linesDist.clear();
        for (j = 0; j < graph.vertices.size(); ++j) {
            try {
                linesDist.push_back(graph.getCost(graph.vertices[i], graph.vertices[j]));
            }
            catch (exception e) {
                linesDist.push_back(INF);
            }
            //std::cout << count++<<'\n';
        }
        distances.push_back(linesDist);
    }
    for (k = 0; k < graph.vertices.size(); ++k)
        for (i = 0; i < graph.vertices.size(); ++i)
            for (j = 0; j < graph.vertices.size(); ++j)
                if(distances[i][k] + distances[k][j] < distances[i][j]) {
                    //std::cout << count++<<'\n';
                    distances[i][j] = distances[i][k] + distances[k][j];
                }
    return distances[source][target];
}
