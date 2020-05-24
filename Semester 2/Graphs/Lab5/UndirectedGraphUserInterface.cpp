//
// Created by Paul Colta on 23/05/2020.
//

#include "UndirectedGraphUserInterface.h"

UndirectedGraphUserInterface::UndirectedGraphUserInterface(const std::string& fileName): graph{UndirectedGraph()}
{
    graph.loadUnweightedGraphFromFile(fileName);
}

void UndirectedGraphUserInterface::runApplication() {
    int choice = 0;
    while (choice != 13) {
        printUserOptions();
        std::cout << "Your choice?: ";
        std::cin >> choice;
        try {
            switch (choice) {
                case 1: option1(); break;
                case 2: option2(); break;
                case 3: option3(); break;
                case 4: option4(); break;
                case 5: option5(); break;
                case 6: option6(); break;
                case 7: option7(); break;
                case 8: option8(); break;
                case 9: option9(); break;
                case 10:option10(); break;
                default: break;
            }
        }
        catch (std::runtime_error& error) {
            std::cout << error.what() << '\n';
        }
    }
}

void UndirectedGraphUserInterface::printUserOptions() {
    std::cout << "1. Print the list of the vertices and all the edges\n";
    std::cout << "2. Print the number of vertices\n";
    std::cout << "3. Test if there is an edge between two nodes\n";
    std::cout << "4. Print the degree of a node\n";
    std::cout << "5. Print the neighbours of a node\n";
    std::cout << "6. Add an edge\n";
    std::cout << "7. Remove an edge\n";
    std::cout << "8. Add a node\n";
    std::cout << "9. Remove a node\n";
    std::cout << "10. Find an Hamiltonian cycle\n";
    std::cout << "11. Exit\n";
}

void UndirectedGraphUserInterface::option1() {
    auto iteratorPair = graph.parseSetOfVertices();
    for (auto it = iteratorPair.first; it != iteratorPair.second; ++it)
        std::cout << *it << " ";
    std::cout << '\n';
    for (auto it = iteratorPair.first; it != iteratorPair.second; ++it) {
        auto outboundIteratorPair = graph.parseNeighbours(*it);
        for (auto it2 = outboundIteratorPair.first; it2 != outboundIteratorPair.second; ++it2) {
            if (*it < *it2)
                std::cout << *it << " " << *it2 << '\n';
        }
    }
}

void UndirectedGraphUserInterface::option2() {
    std::cout << graph.getNumberOfVertices() << '\n';
}

void UndirectedGraphUserInterface::option3() {
    int source, target;
    std::cout << "Provide the source: ";
    std::cin >> source;
    std::cout << "\nProvide the target: ";
    std::cin >> target;
    if (graph.isEdge(source, target))
        std::cout << "\nThere exists edge between " << source << " and " << target << "\n";
    else
        std::cout << "\nThere is no edge between " << source << " and " << target << "\n";
}

void UndirectedGraphUserInterface::option4() {
    int vertex;
    std::cout << "Provide the vertex: ";
    std::cin >> vertex;
    std::cout << "\nOut degree: " << graph.getDegree(vertex) << '\n';
}

void UndirectedGraphUserInterface::option5() {
    int vertex;
    std::cout << "Provide the vertex: ";
    std::cin >> vertex;
    std::cout << '\n';
    auto iteratorPair = graph.parseNeighbours(vertex);
    if (iteratorPair.first == iteratorPair.second) {
        std::cout << "No neighbours for vertex " << vertex << '\n';
    }
    else {
        for (auto it = iteratorPair.first; it != iteratorPair.second; ++it)
            std::cout << *it << " ";
        std::cout << '\n';
    }
}

void UndirectedGraphUserInterface::option6() {
    int source, target, cost;
    std::cout << "Provide the source: ";
    std::cin >> source;
    std::cout << "\nProvide the target: ";
    std::cin >> target;
    graph.addEdge(source, target);
    std::cout << "\nAdded edge from " << source << " to " << target << '\n';
}

void UndirectedGraphUserInterface::option7() {
    int source, target;
    std::cout << "Provide the source: ";
    std::cin >> source;
    std::cout << "\nProvide the target: ";
    std::cin >> target;
    graph.removeEdge(source, target);
    std::cout << "\nRemoved edge from " << source << " to " << target << '\n';
}

void UndirectedGraphUserInterface::option8() {
    int vertex;
    std::cout << "Provide the vertex: ";
    std::cin >> vertex;
    graph.addVertex(vertex);
    std::cout << "\nAdded vertex " << vertex << '\n';
}

void UndirectedGraphUserInterface::option9() {
    int vertex;
    std::cout << "Provide the vertex: ";
    std::cin >> vertex;
    graph.removeVertex(vertex);
    std::cout << "\nRemoved vertex " << vertex << '\n';
}

void UndirectedGraphUserInterface::option10() {

}
