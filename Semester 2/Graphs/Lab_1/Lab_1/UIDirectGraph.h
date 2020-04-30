#pragma once
#include "Direct_Graph.h"
#include "Service.h"
class UI {
private:
    DIRECT_GRAPH graph;
    Service service;
    void choice1();
    void choice2();
    void choice3();
    void choice4();
    void choice5();
    void choice6();
    void choice7();
    void choice8();
    void choice9();
    void choice10();
    void choice11();
    void choice12();
    void choice13();
    void choice14();
    void choice15();

public:
    UI(DIRECT_GRAPH graph, Service service);
    static void start_menu();
    void start_app();
};