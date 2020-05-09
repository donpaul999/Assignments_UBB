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

pair< vector<vector<int>>, int> Service::floydWarshall(int source, int target) {
    vector<vector<int>> distances;
    vector<vector<int>> positions;
    vector<int> linesDist, vectorEmpty;
    int i, j, k, count = 0;
    for (i = 0; i < graph.vertices.size(); ++i) {
        linesDist.clear();
        vectorEmpty.clear();
        for (j = 0; j < graph.vertices.size(); ++j) {
            try {
                linesDist.push_back(graph.getCost(graph.vertices[i], graph.vertices[j]));
                vectorEmpty.push_back(-1);
            }
            catch (exception e) {
                linesDist.push_back(INF);
                vectorEmpty.push_back(-1);
            }
        }
        distances.push_back(linesDist);
        positions.push_back(vectorEmpty);
    }
    for (k = 0; k < graph.vertices.size(); ++k) {
        for (i = 0; i < graph.vertices.size(); ++i)
            for (j = 0; j < graph.vertices.size(); ++j)
                if (distances[i][k] + distances[k][j] < distances[i][j]) {
                    distances[i][j] = distances[i][k] + distances[k][j];
                    positions[i][j] = k;
                }
        for (int t = 0; t < graph.vertices.size(); ++t){
            for (int w = 0; w < graph.vertices.size(); ++w)
                cout << distances[t][w]<<" ";
            cout << '\n';
        }
        cout << "******\n";

    }
    for (i = 0; i < graph.vertices.size(); ++i){
        for (j = 0; j < graph.vertices.size(); ++j)
            cout << positions[i][j]<<" ";
        cout << '\n';
        }
    cout << "******\n";

    return make_pair(positions, distances[source][target]);
}
