//
// Created by Paul Colta on 16/04/2020.
//

#ifndef LAB_1_VALIDATIONEXCEPTION_H
#define LAB_1_VALIDATIONEXCEPTION_H
#include <string>

class ValidationException:  public std::exception {
private:
    std::string messageOfException;
    std::string errorGenerated;

public:
    ValidationException(std::string messageOfException = "");
    const char* what() const noexcept;
};



#endif //LAB_1_VALIDATIONEXCEPTION_H
