//
// Created by Paul Colta on 04/05/2020.
//

#ifndef EXAM_4_MAY_HOSPITALDEPARTAMENT_H
#define EXAM_4_MAY_HOSPITALDEPARTAMENT_H

#include <string>

class HospitalDepartament {
protected:
    std::string hospitalName;
    int numberOfDoctors;
public:
    virtual bool isEfficient(){return false;};
    virtual std::string toString(){return "";};
};


#endif //EXAM_4_MAY_HOSPITALDEPARTAMENT_H
