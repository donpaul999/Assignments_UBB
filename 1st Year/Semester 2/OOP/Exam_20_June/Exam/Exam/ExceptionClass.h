#pragma once
#include <string>

class ExceptionClass: public std::exception {
private:
    std::string messageOfException;
    std::string errorGenerated;

public:
    ExceptionClass(std::string messageOfException = ""): messageOfException{messageOfException} {
        errorGenerated = "Repository error: " + messageOfException;
    };
    const char* what() const noexcept{return errorGenerated.c_str();};
};

