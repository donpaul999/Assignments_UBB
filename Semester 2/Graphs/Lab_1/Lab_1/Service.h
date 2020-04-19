#pragma once
#include "Direct_Graph.h"

class Service
{
private: 
	DIRECT_GRAPH graph;
public:
	Service();
	Service(DIRECT_GRAPH graph);
	vector<int> bfsFromEndToStart(int source);
    pair< vector<vector<int>>, int> floydWarshall(int source, int target);
};

