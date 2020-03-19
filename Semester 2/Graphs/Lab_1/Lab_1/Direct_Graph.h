#pragma once
#include <vector>
#include <string>
#include <map>
#include <vector>
#include <fstream>
using namespace std;

class DIRECT_GRAPH {

private:
    int numberOfEdges, numberOfVertices;
    map <int, vector<int>> edgesIn;
    map <int, vector<int>> edgesOut;
    map <pair<int, int>, int>costs;
    vector <int> vertices;

public:
    DIRECT_GRAPH();
    void readFromFile(string filename);
    int getNumberOfVertices();
    pair <vector <int>::const_iterator, vector <int>::const_iterator> parseThroughVertices();
    bool existsEdge(int source, int target);
    bool existsVertex(int vertex);
    bool inDegree(int vertex);
    bool outDegree(int vertex);
    pair <vector <int>::const_iterator, vector <int>::const_iterator> parseEdgesOut(int vertex);
    pair <vector <int>::const_iterator, vector <int>::const_iterator> parseEdgesIn(int vertex);
    int getCost(int source, int target);
    void modifyCost(int source, int target, int newValue);
    void addVertex(int vertex);
    void addEdge(int source, int target, int value);
    void removeVertex(int vertex);
    void removeEdge(int source, int target);


};