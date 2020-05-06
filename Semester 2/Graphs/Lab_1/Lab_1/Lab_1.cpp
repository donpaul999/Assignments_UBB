
#include "UIDirectGraph.h"
#include "DAGUI.h"
#include <iostream>
#include <fstream>
using namespace std;




int main()
{

    DIRECT_GRAPH graph;
    graph.readFromFile("input.in");
    Service service{ graph };
    UI u(graph, service);
    u.start_app();

    //DAGUI dag{"DAGFile.txt"};
    //dag.runApplication();
    cout << "Hello World!\n";
}

