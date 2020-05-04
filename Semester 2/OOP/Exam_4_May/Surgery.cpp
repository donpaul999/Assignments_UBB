//
// Created by Paul Colta on 04/05/2020.
//

#include "Surgery.h"

std::string Surgery::toString(){
    std::string efficient = isEfficient() == 1 ? "efficient" : "not efficient";
    std::string stringToReturn = hospitalName + ",Surgery," +std::to_string(numberOfDoctors) +","+ std::to_string(numberOfPatients) + ","+efficient;
    return stringToReturn;
}