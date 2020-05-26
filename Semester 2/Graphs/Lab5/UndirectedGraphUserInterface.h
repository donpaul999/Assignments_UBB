//
// Created by Paul Colta on 23/05/2020.
//

#ifndef LAB5_UNDIRECTEDGRAPHUSERINTERFACE_H
#define LAB5_UNDIRECTEDGRAPHUSERINTERFACE_H

#include "UndirectedGraph.h"
#include <iostream>
#include "Service.h"
class UndirectedGraphUserInterface {
private:
    UndirectedGraph graph;
    Service s;
    void option1();
    void option2();
    void option3();
    void option4();
    void option5();
    void option6();
    void option7();
    void option8();
    void option9();
    void option10();


public:
    explicit UndirectedGraphUserInterface(const std::string& fileName);
    void printUserOptions();
    void runApplication();
};


#endif //LAB5_UNDIRECTEDGRAPHUSERINTERFACE_H
