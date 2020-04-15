
#include "UIDirectGraph.h"
#include <iostream>
#include <fstream>
using namespace std;




int main()
{
    DIRECT_GRAPH graph;
    graph.readFromFile("graph1k.txt");
    Service service{ graph };
    UI u(graph, service);
    u.start_app();
    cout << "Hello World!\n";
}

