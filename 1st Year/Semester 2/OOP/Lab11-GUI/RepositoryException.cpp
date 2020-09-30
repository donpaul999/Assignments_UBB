//
// Created by Paul Colta on 16/04/2020.
//
#include "RepositoryException.h"

RepositoryException::RepositoryException(std::string messageOfException): messageOfException{messageOfException} {
    errorGenerated = "Repository error: " + messageOfException;
}

const char *RepositoryException::what() const noexcept{
    return errorGenerated.c_str();
}