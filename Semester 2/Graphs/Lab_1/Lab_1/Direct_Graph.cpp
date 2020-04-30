#include "Direct_Graph.h"
#define mp make_pair
#define pb push_back
#include <iostream>
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
        edgesIn[i] = vector <int>();
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

void DIRECT_GRAPH::loadActivityGraphFromFile(const std::string &fileName) {
    std::ifstream fin(fileName);
    int index, duration, precedentActivitiesNumber, precedentActivity;
    fin >> numberOfVertices;
    numberOfVertices += 2; // will add dummy start and end activity
    activities = std::vector<Activity>(numberOfVertices);
    Activity startActivity{0, 0};
    activities[0] = startActivity;

    for (int i = 0; i < numberOfVertices; ++i) {
        vertices.push_back(i);
        edgesIn[i] = std::vector<int>();
        edgesOut[i] = std::vector<int>();
    }

    for (int i = 1; i < numberOfVertices - 1; ++i) {
        fin >> index >> precedentActivitiesNumber;
        if (precedentActivitiesNumber == 0) {
            edgesIn[index].push_back(0);
            edgesOut[0].push_back(index);
        }
        while (precedentActivitiesNumber--) {
            fin >> precedentActivity;
            edgesIn[index].push_back(precedentActivity);
            edgesOut[precedentActivity].push_back(index);
        }
        fin >> duration;
        activities[index] = Activity{index, duration};
    }
    activities[numberOfVertices - 1] = Activity{numberOfVertices - 1, 0};
    for (int i = 1; i < numberOfVertices - 1; ++i) {
        if (outDegree(i) == 0) {
            edgesIn[numberOfVertices - 1].push_back(i);
            edgesOut[i].push_back(numberOfVertices - 1);
        }
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

int DIRECT_GRAPH::inDegree(int vertex)
{
    if (!existsVertex(vertex))
        throw runtime_error{ "invalid vertix given!" };
    return edgesIn[vertex].size();
}

int DIRECT_GRAPH::outDegree(int vertex)
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

bool DIRECT_GRAPH::activityTopologicalSortingDFS(int node, std::vector<bool> &processed, std::vector<bool> &inProcess,
                                                  std::vector<int> &sorted) {
    inProcess[node] = true;
    bool outcome;
    for (const int &neighbour: edgesOut[node]) {
        if (inProcess[neighbour]) {
            return false;
        }
        if (!processed[neighbour]) {
            outcome = activityTopologicalSortingDFS(neighbour, processed, inProcess, sorted);
            if (!outcome) {
                return false;
            }
        }
    }
    inProcess[node] = false;
    processed[node] = true;
    sorted.push_back(node);
    return true;
}

std::vector<int> DIRECT_GRAPH::getActivityTopologicalSorting() {
    std::vector<int> sorted;
    std::vector<bool> processed = std::vector<bool>(numberOfVertices, false);
    std::vector<bool> inProcess = std::vector<bool>(numberOfVertices, false);
    bool outcome = activityTopologicalSortingDFS(0, processed, inProcess, sorted);
    if (!outcome) {
        throw std::runtime_error{"The graph IS NOT A DAG"};
    } else {
        std::reverse(sorted.begin(), sorted.end());
        return sorted;
    }
}

std::vector<Activity> DIRECT_GRAPH::getFullActivityStatus() {
    std::vector<int> sorted = getActivityTopologicalSorting();

    int index;
    int maxTime, minTime;
    int maxEarliestEndTime = -1;
    for (int i = 1; i < numberOfVertices - 1; ++i) {
        index = sorted[i];
        maxTime = -1;
        for (const int &neighbour: edgesIn[index]) {
            maxTime = std::max(maxTime, activities[neighbour].getEarliestEndTime());
        }
        activities[index].setEarliestStartTime(maxTime);
        activities[index].setEarliestEndTime(maxTime + activities[index].getDuration());
        maxEarliestEndTime = std::max(maxEarliestEndTime, activities[index].getEarliestEndTime());
    }

    activities[numberOfVertices - 1].setEarliestEndTime(maxEarliestEndTime);
    activities[numberOfVertices - 1].setEarliestStartTime(maxEarliestEndTime);
    activities[numberOfVertices - 1].setLatestStartTime(maxEarliestEndTime);
    activities[numberOfVertices - 1].setLatestEndTime(maxEarliestEndTime);
    for (int i = numberOfVertices - 2; i >= 1; --i) {
        index = sorted[i];
        minTime = 2e9;
        for (const int &neighbour: edgesOut[index]) {
            minTime = std::min(minTime, activities[neighbour].getLatestStartTime());
        }
        activities[index].setLatestEndTime(minTime);
        activities[index].setLatestStartTime(minTime - activities[index].getDuration());
    }

    std::vector<Activity> sortedActivities;
    for (int i = 1; i < numberOfVertices - 1; ++i) {
        sortedActivities.push_back(activities[sorted[i]]);
    }
    return sortedActivities;
}