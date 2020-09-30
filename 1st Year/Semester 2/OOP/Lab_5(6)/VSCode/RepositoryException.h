//
// Created by Paul Colta on 16/04/2020.
//

#ifndef LAB_1_EXCEPTION_H
#define LAB_1_EXCEPTION_H

#include <string>

class RepositoryException: public std::exception {
private:
    std::string messageOfException;
    std::string errorGenerated;

public:
    RepositoryException(std::string messageOfException = "");
    const char* what() const noexcept;
};


#endif //LAB_1_EXCEPTION_H
