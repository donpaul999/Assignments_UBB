//
// Created by Paul Colta on 04/05/2020.
//

#ifndef EXAM_4_MAY_SERVICE_H
#define EXAM_4_MAY_SERVICE_H
#include "Repository.h"

class Service {
private:
    Repository repository;
public:
    Service(){};
    Service(Repository newRepository){this->repository = newRepository;};
    void addNeonatalUnit(std::string nameOfDepartament, int nbOfDoctors, int nbOfMothers, int nbOfBabies, double average);
    void addSurgery(std::string nameOfDepartament, int nbOfDoctors, int nbOfPatients);
    std::vector <NeonatalUnit> getAllNeonatalUnits(){return repository.getAllNeonatalUnits();};
    std::vector <Surgery> getAllSurgeries(){return repository.getAllSurgeries();};
    std::vector <NeonatalUnit> getAllEfficientNeonatalUnits(){return repository.getAllEfficientNeonatalUnits();};
    std::vector <Surgery> getAllEfficientSurgery(){return repository.getAllEfficientSurgery();};
    void changeFile(std::string fileName){return repository.changeFile(fileName);};
};


#endif //EXAM_4_MAY_SERVICE_H
