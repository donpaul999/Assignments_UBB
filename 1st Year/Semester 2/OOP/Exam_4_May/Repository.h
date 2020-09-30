//
// Created by Paul Colta on 04/05/2020.
//

#ifndef EXAM_4_MAY_REPOSITORY_H
#define EXAM_4_MAY_REPOSITORY_H
#include <string>
#include <fstream>
#include <vector>
#include "NeonatalUnit.h"
#include "Surgery.h"

class Repository {
private:
    std::string fileName;
    bool inFileOrMemory;
    std::vector <NeonatalUnit> listOfNeonatalUnits;
    std::vector <Surgery> listOfSurgeries;
public:
    explicit Repository(const std::string fileName=""){this->fileName = fileName; this->inFileOrMemory = fileName != "";};
    void addNeonatalUnit(NeonatalUnit newUnit);
    void addSurgery(Surgery newSurgery);
    std::vector <NeonatalUnit> getAllNeonatalUnits();
    std::vector <Surgery> getAllSurgeries();
    std::vector <NeonatalUnit> getAllEfficientNeonatalUnits();
    std::vector <Surgery> getAllEfficientSurgery();
    void writeFile();
    void changeFile(std::string fileName){inFileOrMemory = 1; this->fileName = fileName;};
};


#endif //EXAM_4_MAY_REPOSITORY_H
