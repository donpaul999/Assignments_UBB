//
// Created by Paul Colta on 04/05/2020.
//

#ifndef EXAM_4_MAY_UI_H
#define EXAM_4_MAY_UI_H

#include "Service.h"

class UI {
private:
    bool isFileOrMem;
    Service service;
public:
    UI(bool fileName, Service newService);
    void runApp();
    void addUnit();
    void list();
    void changeFile();
    const std::vector<std::string>explode(const std::string& stringToExplode, const std::string& separatorsUsed);

};


#endif //EXAM_4_MAY_UI_H
