//
// Created by Paul Colta on 04/05/2020.
//

#ifndef EXAM_4_MAY_SURGERY_H
#define EXAM_4_MAY_SURGERY_H

#include "HospitalDepartament.h"

class Surgery: public HospitalDepartament {
private:
    int numberOfPatients;
public:
    Surgery(std::string newNameOfHospital="", int newNumberOfDoctors=0, int newNumberOfPatients=0){this->hospitalName = newNameOfHospital; this->numberOfDoctors = newNumberOfDoctors; this->numberOfPatients = newNumberOfPatients;};
    bool isEfficient() override { return numberOfPatients/numberOfDoctors >= 2;};
    std::string toString();

};


#endif //EXAM_4_MAY_SURGERY_H
