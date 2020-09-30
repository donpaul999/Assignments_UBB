//
// Created by Paul Colta on 04/05/2020.
//

#include "NeonatalUnit.h"

std::string NeonatalUnit::toString(){
    std::string efficient = isEfficient() == 1 ? "efficient" : "not efficient";
    std::string stringToReturn = hospitalName + ",Neonatal Unit," + std::to_string(numberOfDoctors) +","+ std::to_string(numberOfNewborns) + "," + std::to_string(averageGrade)+ ","+efficient;
    return stringToReturn;
}