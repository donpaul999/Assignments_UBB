//
// Created by Paul Colta on 16/04/2020.
//

#include "ValidationException.h"
ValidationException::ValidationException(std::string messageOfException):messageOfException{messageOfException} {
    errorGenerated = "Validation error: " + messageOfException;
}

const char *ValidationException::what() const noexcept{
    return errorGenerated.c_str();
}