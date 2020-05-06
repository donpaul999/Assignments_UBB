#include "UIDirectGraph.h"
#include <iostream>
UI::UI(DIRECT_GRAPH graph, Service service)
{
	this->graph = graph;
	this->service = service;
}

void UI::
start_menu()
{
	cout << "1. Print the list of all vertices and edges" << '\n';
	cout << "2. Print the number of vertices" << '\n';
	cout << "3. Test if between 2 vertices exists an edge" << '\n';
	cout << "3. Print the in degree of a vertex" << '\n';
	cout << "3. Print the out degree of a vertex" << '\n';
	cout << "6. Print the outbound edges of a vertex" << '\n';
	cout << "7. Print the inbound edges of a vertex" << '\n';
	cout << "8. Print the cost of an edge" << '\n';
	cout << "9. Modify the cost of an edge" << '\n';
	cout << "10. Add an edge" << '\n';
	cout << "11. Add a vertex" << '\n';
	cout << "12. Remove an edge" << '\n';
	cout << "13. Remove a vertex" << '\n';
    cout << "14. Find shortest path between 2 vertices" << '\n';
    cout << "15. Find the lowest cost walk between the given vertices" << '\n';
	cout << "16. Exit" << '\n';
}

void UI::start_app()
{
	int input;
	while (1) {
		start_menu();
		cout << "Input choice: ";
		cin >> input;
		cout << '\n';
        if (input == 16)
            break;
        if (input == 15)
			choice15();
		if (input == 1)
			choice1();
		if (input == 2)
			choice2();
		if (input == 3)
			choice3();
		if (input == 4)
			choice4();
		if (input == 5)
			choice5();
		if (input == 6)
			choice6();
		if (input == 7)
			choice7();
		if (input == 8)
			choice8();
		if (input == 9)
			choice9();
		if (input == 10)
			choice10();
		if (input == 11)
			choice11();
		if (input == 12)
			choice12();
		if (input == 13)
			choice13();
		if (input == 14)
			choice14();
		cout << "********************************\n\n";

	}
}

void UI::choice1()
{
	auto iteratorVertices = graph.parseThroughVertices();
	cout << "Vertices: \n";
	for (auto it = iteratorVertices.first; it != iteratorVertices.second; it++)
		cout << *it << " ";
	cout << '\n';
	cout << "Edges: \n";
	for (auto it = iteratorVertices.first; it != iteratorVertices.second; ++it) {
		auto iteratorEdgesOut = graph.parseEdgesOut(*it);
		for (auto it2 = iteratorEdgesOut.first; it2 != iteratorEdgesOut.second; ++it2) {
			std::cout << *it << " " << *it2 << " " << graph.getCost(*it, *it2) << '\n';
		}
	}
	cout << "\n";
}

void UI::choice2()
{
	cout << graph.getNumberOfVertices()<<'\n';
}

void UI::choice3()
{
	int source, target;
	cout << "Input first vertex: ";
	cin >> source;
	cout << "Input the second vertex: ";
	cin >> target;
	cout << '\n';
	if (graph.existsEdge(source, target))
		cout << "There is an edge";
	else
		cout << "There is no edge";
	cout << '\n';
}

void UI::choice4()
{
	int source;
	cout << "Input vertex: ";
	cin >> source;
	cout << '\n';
	cout << "In degree is " << graph.inDegree(source);
	cout << '\n';
}

void UI::choice5()
{
	int source;
	cout << "Input vertex: ";
	cin >> source;
	cout << '\n';
	cout << "Out degree is " << graph.outDegree(source);
	cout << '\n';
}

void UI::choice6()
{
	int source;
	cout << "Input vertex: ";
	cin >> source;
	cout << '\n';
	auto iteratorVertex = graph.parseEdgesOut(source);
	for (auto it = iteratorVertex.first; it != iteratorVertex.second; ++it)
		cout << *it << " ";
	cout << '\n';
}

void UI::choice7()
{
	int source;
	cout << "Input vertex: ";
	cin >> source;
	cout << '\n';
	auto iteratorVertex = graph.parseEdgesIn(source);
	for (auto it = iteratorVertex.first; it != iteratorVertex.second; ++it)
		cout << *it << " ";
	cout << '\n';
}

void UI::choice8()
{
	int source, target;
	cout << "Input first vertex: ";
	cin >> source;
	cout << '\n';
	cout << "Input the second vertex: ";
	cin >> target;
	cout << '\n';
	cout << "Cost: " << graph.getCost(source, target);
	cout << '\n';
}

void UI::choice9()
{
	int source, target, cost;
	cout << "Input first vertex: ";
	cin >> source;
	cout << "Input the second vertex: ";
	cin >> target;
	cout << '\n';
	cout << "Input new cost: ";
	cin >> cost;
	cout << '\n';
	graph.modifyCost(source, target, cost);
}

void UI::choice10()
{
	int source, target, cost;
	cout << "Input first vertex: ";
	cin >> source;
	cout << "Input the second vertex: ";
	cin >> target;
	cout << '\n';
	cout << "Input new cost: ";
	cin >> cost;
	cout << '\n';
	graph.addEdge(source, target, cost);
}

void UI::choice11()
{
	int vertex;
	cout << "Input vertex: ";
	cin >> vertex;
	cout << '\n';
	graph.addVertex(vertex);
}

void UI::choice12()
{
	int source, target;
	cout << "Input first vertex: ";
	cin >> source;
	cout << "Input the second vertex: ";
	cin >> target;
	cout << '\n';
	graph.removeEdge(source, target);
}

void UI::choice13()
{
	int vertex;
	cout << "Input vertex: ";
	cin >> vertex;
	cout << '\n';
	graph.removeVertex(vertex);
}

void UI::choice14()
{
	int source, target;
	cout << "Input first vertex: ";
	cin >> source;
	cout << "Input the second vertex: ";
	cin >> target;
	cout << '\n';
	vector<int> listOfVertices = service.bfsFromEndToStart(target);
	int actualVertice = source;
	int length = 0;
	while (actualVertice != target) {
		cout << actualVertice << " ";
		actualVertice = listOfVertices[actualVertice];
		length++;
	}
	cout << '\n';
	cout << "Length of the path: " << length << '\n';
}

void UI::choice15()
{
    int source, target;
    cout << "Input first vertex: ";
    cin >> source;
    cout << "Input the second vertex: ";
    cin >> target;
    cout << '\n';
    pair< vector<vector<int>>, int> lowestCost = service.floydWarshall(source, target);
    if(lowestCost.second > 0 && lowestCost.second != INT_MAX) {
        cout << "Lowest cost of the path: " << lowestCost.second << '\n';
        cout << "Path: \n";
        vector<int> path;
        int i = 0, j = 1, k;
        path.push_back(source);
        path.push_back(target);
        bool isAPath = 1;
        while(isAPath == 1)
        {
            isAPath = 0;

            if(i >= j)
                break;
            if(lowestCost.first[path[i]][path[j]] != -1 && find(path.begin(), path.end(), lowestCost.first[path[i]][path[j]]) == path.end() && i == j - 1)
            {
                isAPath = 1;
                path.insert(path.begin() + i + 1,lowestCost.first[path[i]][path[j]]);
            }
            if(isAPath == 0 && i < path.size() - 1 && j < path.size() - 1 && lowestCost.first[path[i + 1]][path[j + 1]] != -1) {
                isAPath = 1;
                i++;
                j++;
            }
            if(isAPath == 0 && j > 0 && i > 0 && lowestCost.first[path[i - 1]][path[j - 1]] != -1) {
                isAPath = 1;
                j--;
                i--;
            }
        }
        for(k = 0; k < path.size(); ++k)
            cout<<path[k]<<" ";
        cout << '\n';
    }
    else
        cout << "No path between this vertices"<<'\n';
}