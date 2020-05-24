//
// Created by Paul Colta on 23/05/2020.
//

#ifndef LAB5_UNDIRECTEDGRAPH_H
#define LAB5_UNDIRECTEDGRAPH_H

#include <vector>
#include <map>
#include <fstream>
#include <algorithm>
#include <stdexcept>

class UndirectedGraph {
private:
    int numberOfVertices;
    std::map <int, std::vector <int> > adjacencyList;
    std::vector <std::pair <int, int> > listOfEdges;
    std::vector <int> setOfVertices;
    bool doesVertexExist(int vertex) const;

public:
    UndirectedGraph();
    void loadUnweightedGraphFromFile(const std::string& fileName);
    UndirectedGraph(const UndirectedGraph& graph);
    UndirectedGraph& operator=(const UndirectedGraph& graph);
    int getNumberOfVertices() const;
    std::pair <std::vector <int>::const_iterator, std::vector <int>::const_iterator> parseSetOfVertices() const;
    bool isEdge(int firstVertex, int secondVertex);
    int getDegree(int vertex);
    std::pair <std::vector <int>::const_iterator, std::vector <int>::const_iterator> parseNeighbours(int vertex);
    void addVertex(int vertex);
    void addEdge(int source, int target);
    void removeEdge(int source, int target);
    void removeVertex(int vertex);
};


#endif //LAB5_UNDIRECTEDGRAPH_H
