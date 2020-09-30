//
// Created by Paul Colta on 23/05/2020.
//

#include "UndirectedGraph.h"

#include <iostream>

void UndirectedGraph::loadUnweightedGraphFromFile(const std::string &fileName) {
    std::ifstream fin(fileName);
    int numberOfEdges;
    int source, target, cost;

    fin >> numberOfVertices >> numberOfEdges;
    for(int i = 0 ;i < numberOfVertices; ++i) {
        std::vector<bool> v;
        for(int j = 0 ;j < numberOfVertices; ++j)
            v.push_back(0);
        matrix.push_back(v);
    }
    for (int i = 0; i < numberOfVertices; ++i) {
        setOfVertices.push_back(i);
        adjacencyList[i] = std::vector<int>();
        adjacencyList[i] = std::vector<int>();
    }

    int realNumber = 0;
    for (int i = 0; i < numberOfEdges; ++i) {
        fin >> source >> target;
        if (!isEdge(source, target)) {
            ++realNumber;
            listOfEdges.push_back({source, target});
            matrix[source][target] = matrix[target][source] = 1;
            adjacencyList[target].push_back(source);
            adjacencyList[source].push_back(target);
        }
    }
    std::cout << realNumber << " edges\n";
    fin.close();
}

bool UndirectedGraph::doesVertexExist(int vertex) const {
    return std::find(setOfVertices.begin(), setOfVertices.end(), vertex) != setOfVertices.end();
}

UndirectedGraph::UndirectedGraph(const UndirectedGraph &graph) {
    numberOfVertices = graph.numberOfVertices;
    adjacencyList = graph.adjacencyList;
    setOfVertices = graph.setOfVertices;
    listOfEdges = graph.listOfEdges;
    matrix = graph.matrix;
}

UndirectedGraph &UndirectedGraph::operator=(const UndirectedGraph &graph) {
    if (this != &graph) {
        numberOfVertices = graph.numberOfVertices;
        adjacencyList = graph.adjacencyList;
        setOfVertices = graph.setOfVertices;
        listOfEdges = graph.listOfEdges;
    }
    return *this;
}

int UndirectedGraph::getNumberOfVertices() const {
    return numberOfVertices;
}

std::pair<std::vector<int>::const_iterator, std::vector<int>::const_iterator>
UndirectedGraph::parseSetOfVertices() const {
    return {setOfVertices.begin(), setOfVertices.end()};
}

bool UndirectedGraph::isEdge(int firstVertex, int secondVertex) {
    if (!doesVertexExist(firstVertex) || !doesVertexExist(secondVertex)) {
        throw std::runtime_error("invalid vertex");
    }
    return std::find(adjacencyList[firstVertex].begin(), adjacencyList[firstVertex].end(), secondVertex) !=
           adjacencyList[firstVertex].end();
}

int UndirectedGraph::getDegree(int vertex) {
    if (!doesVertexExist(vertex)) {
        throw std::runtime_error("invalid vertex");
    }
    return adjacencyList[vertex].size();
}

std::pair<std::vector<int>::const_iterator, std::vector<int>::const_iterator>
UndirectedGraph::parseNeighbours(int vertex) {
    if (!doesVertexExist(vertex)) {
        throw std::runtime_error("invalid vertex");
    }
    return {adjacencyList[vertex].begin(), adjacencyList[vertex].end()};
}

void UndirectedGraph::addVertex(int vertex) {
    if (doesVertexExist(vertex)) {
        throw std::runtime_error("vertex already existing");
    }
    setOfVertices.push_back(vertex);
    adjacencyList[vertex] = std::vector<int>();
    ++numberOfVertices;
}

void UndirectedGraph::addEdge(int source, int target) {
    if (isEdge(source, target)) {
        throw std::runtime_error("edge already existing");
    }
    adjacencyList[source].push_back(target);
    adjacencyList[target].push_back(source);
}

void UndirectedGraph::removeEdge(int source, int target) {
    if (!isEdge(source, target)) {
        throw std::runtime_error("edge doesn't exist");
    }
    adjacencyList[source].erase(std::find(adjacencyList[source].begin(), adjacencyList[source].end(), target));
    adjacencyList[target].erase(std::find(adjacencyList[target].begin(), adjacencyList[target].end(), source));
}

void UndirectedGraph::removeVertex(int vertex) {
    if (!doesVertexExist(vertex))
        throw std::runtime_error("invalid vertex");
    for (const int &neighbour: adjacencyList[vertex]) {
        adjacencyList[neighbour].erase(
                std::find(adjacencyList[neighbour].begin(), adjacencyList[neighbour].end(), vertex));
    }
    adjacencyList.erase(adjacencyList.find(vertex));
    setOfVertices.erase(std::find(setOfVertices.begin(), setOfVertices.end(), vertex));
}

UndirectedGraph::UndirectedGraph() : numberOfVertices{0} {
}



