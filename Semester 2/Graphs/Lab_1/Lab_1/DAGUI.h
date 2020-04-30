//
// Created by Paul Colta on 25/04/2020.
//

#ifndef LAB_1_DAGUI_H
#define LAB_1_DAGUI_H

#include "Direct_Graph.h"
#include "Activity.h"
#include <vector>
#include <iostream>


class DAGUI {

private:
    DIRECT_GRAPH activityGraph;
    void printActivityGraphDetails();

public:
    DAGUI(const std::string& fileName);
    void runApplication();
};


#endif //LAB_1_DAGUI_H
