#pragma once
#include <vector>
#include <queue>
#include <string>
#include <map>
#include <vector>
#include <fstream>
#include "Activity.h"

using namespace std;

class DIRECT_GRAPH {

private:
    int numberOfEdges, numberOfVertices;
    map <int, vector<int>> edgesIn;
    map <int, vector<int>> edgesOut;
    map <pair<int, int>, int>costs;
    std::vector <Activity> activities;

public:
    vector <int> vertices;
    DIRECT_GRAPH();
    void loadActivityGraphFromFile(const std::string& fileName);
    void readFromFile(string filename);
    int getNumberOfVertices();
    pair <vector <int>::const_iterator, vector <int>::const_iterator> parseThroughVertices();
    bool existsEdge(int source, int target);
    bool existsVertex(int vertex);
    int inDegree(int vertex);
    int outDegree(int vertex);
    pair <vector <int>::const_iterator, vector <int>::const_iterator> parseEdgesOut(int vertex);
    pair <vector <int>::const_iterator, vector <int>::const_iterator> parseEdgesIn(int vertex);
    int getCost(int source, int target);
    void modifyCost(int source, int target, int newValue);
    void addVertex(int vertex);
    void addEdge(int source, int target, int value);
    void removeVertex(int vertex);
    void removeEdge(int source, int target);
    bool activityTopologicalSortingDFS(int node, std::vector<bool> &processed, std::vector<bool> &inProcess, std::vector<int> &sorted);
    std::vector<int> getActivityTopologicalSorting();
    std::vector<Activity> getFullActivityStatus();

};