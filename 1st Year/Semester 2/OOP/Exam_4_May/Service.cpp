//
// Created by Paul Colta on 04/05/2020.
//

#include "Service.h"
void Service::addNeonatalUnit(std::string nameOfDepartament, int nbOfDoctors, int nbOfMothers, int nbOfBabies, double average){
    NeonatalUnit newUnit{nameOfDepartament, nbOfDoctors, nbOfMothers, nbOfBabies, average};
    repository.addNeonatalUnit(newUnit);
}
void Service::addSurgery(std::string nameOfDepartament, int nbOfDoctors, int nbOfPatients){
    Surgery newUnit{nameOfDepartament, nbOfDoctors, nbOfPatients};
    repository.addSurgery(newUnit);
}
