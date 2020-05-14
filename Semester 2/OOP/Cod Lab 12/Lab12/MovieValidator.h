//
// Created by Paul Colta on 24/04/2020.
//

#ifndef LAB8_MOVIEVALIDATOR_H
#define LAB8_MOVIEVALIDATOR_H
#include "Movie.h"
#include "ValidationException.h"

class MovieValidator {
public:
    static void validateMovie(const Movie& movieUsed);
};

#endif //LAB8_MOVIEVALIDATOR_H
