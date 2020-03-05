#include "Direct_Graph.h"
#define mp make_pair
#define pb push_back
using namespace std;

DIRECT_GRAPH::DIRECT_GRAPH()
{
    numberOfVertices = 0;
    numberOfEdges = 0;
}

void DIRECT_GRAPH::readFromFile(string filename)
{
    ifstream fin(filename);
    int source, target, cost;
    fin >> numberOfVertices >> numberOfEdges;
    for (int i = 0; i < numberOfVertices; ++i)
    {

        vertices.pb(i);
        edgesIn[i] = vector <int>();    //initialize the edges empty for every vertice
        edgesOut[i] = vector <int>();
    }

    for (int i = 0; i < numberOfEdges; ++i)
    {
        fin >> source >> target >> cost;
        edgesOut[source].pb(target);
        edgesIn[target].pb(source);
        costs[mp(source, target)] = cost;
    }

}

int DIRECT_GRAPH::getNumberOfVertices()
{
    return numberOfVertices;
}

pair<vector<int>::const_iterator, vector<int>::const_iterator> DIRECT_GRAPH::parseThroughVertices()
{
    return { vertices.begin(), vertices.end() };
}

bool DIRECT_GRAPH::existsEdge(int source, int target)
{
    if (!existsVertex(source) || !existsVertex(target))
        throw runtime_error{ "vertices given are invalid" };
    else
        return  find(edgesOut[source].begin(), edgesOut[source].end(), target) != edgesOut[source].end();
}

bool DIRECT_GRAPH::existsVertex(int vertex)
{
    return find(vertices.begin(), vertices.end(), vertex) != vertices.end();
}

bool DIRECT_GRAPH::inDegree(int vertex)
{
    if (!existsVertex(vertex))
        throw runtime_error{ "invalid vertix given!" };
    return edgesIn[vertex].size();
}

bool DIRECT_GRAPH::outDegree(int vertex)
{
    if (!existsVertex(vertex))
        throw runtime_error{ "invalid vertix given!" };
    return edgesIn[vertex].size();
}

pair<vector<int>::const_iterator, vector<int>::const_iterator> DIRECT_GRAPH::parseEdgesOut(int vertex)
{
    if (!existsVertex(vertex))
        throw runtime_error{ "invalid vertex given" };
    return mp( edgesOut[vertex].begin(), edgesOut[vertex].end() );
}

pair<vector<int>::const_iterator, vector<int>::const_iterator> DIRECT_GRAPH::parseEdgesIn(int vertex)
{
    if (!existsVertex(vertex))
        throw runtime_error{ "invalid vertex given" };
    return mp( edgesIn[vertex].begin(), edgesIn[vertex].end() );
}

int DIRECT_GRAPH::getCost(int source, int target)
{
    if (!existsEdge(source, target))
        throw runtime_error{ "no edge" };
    return costs[mp(source, target)];
}

void DIRECT_GRAPH::modifyCost(int source, int target, int newValue)
{
    if (!existsEdge(target, source))
        throw runtime_error{ "no edge" };
    costs[mp(source, target)] = newValue;
}

void DIRECT_GRAPH::addVertex(int vertex)
{
    if (existsVertex(vertex))
        throw runtime_error{ "vertex already in graph" };
    vertices.pb(vertex);
    edgesIn[vertex] = vector <int>();    
    edgesOut[vertex] = vector <int>();
}

void DIRECT_GRAPH::addEdge(int source, int target, int value)
{
    if (existsEdge(source, target))
        throw runtime_error{ "edge already in graph" };
    edgesOut[source].pb(target);
    edgesIn[target].pb(source);
    costs[mp(source, target)] = value;
}

void DIRECT_GRAPH::removeVertex(int vertex)
{
    if (!existsVertex(vertex))
        throw runtime_error("vertex not in graph");
    for (int& sources : edgesOut[vertex]) {
        edgesIn[sources].erase(find(edgesIn[sources].begin(), edgesIn[sources].end(), vertex));
        costs.erase(costs.find(mp(vertex, sources)));
    }

    for (int& sources : edgesIn[vertex]) {
        edgesOut[sources].erase(find(edgesOut[sources].begin(), edgesOut[sources].end(), vertex));
        costs.erase(costs.find(mp(sources, vertex)));
    }
    edgesOut.erase(edgesOut.find(vertex));
    edgesIn.erase(edgesIn.find(vertex));
    vertices.erase(find(vertices.begin(), vertices.end(), vertex));

}

void DIRECT_GRAPH::removeEdge(int source, int target)
{
    if (!existsEdge(source, target))
        throw runtime_error{ "invalid edge" };
    edgesOut[source].erase(find(edgesOut[source].begin(), edgesOut[source].end(), source));
    edgesIn[target].erase(find(edgesIn[target].begin(), edgesIn[target].end(), source));
    costs.erase(costs.find(mp(source, target)));
}
