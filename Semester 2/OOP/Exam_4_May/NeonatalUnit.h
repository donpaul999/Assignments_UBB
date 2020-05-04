//
// Created by Paul Colta on 04/05/2020.
//

#ifndef EXAM_4_MAY_NEONATALUNIT_H
#define EXAM_4_MAY_NEONATALUNIT_H
#include "HospitalDepartament.h"

class NeonatalUnit: public HospitalDepartament {
private:
    int numberOfMothers;
    int numberOfNewborns;
    double averageGrade;
public:
    NeonatalUnit(std::string newNameOfHospital="", int newNumberOfDoctor=0, int newNumberOfMothers=0, int newNumberOfNewborns=0, double newAverageGrade=0){this->hospitalName = newNameOfHospital;
                                                                                                                                                            this->numberOfDoctors = newNumberOfDoctor;
                                                                                                                                                            this->numberOfMothers = newNumberOfMothers;
                                                                                                                                                            this->numberOfNewborns = newNumberOfNewborns;
                                                                                                                                                            this->averageGrade = newAverageGrade;    };
    bool isEfficient() override { return averageGrade > 8.5 && numberOfMothers <= numberOfNewborns;};
    std::string toString();

};


#endif //EXAM_4_MAY_NEONATALUNIT_H
