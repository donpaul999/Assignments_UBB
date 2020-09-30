//
// Created by Paul Colta on 24/04/2020.
//

#include "MovieValidator.h"


void MovieValidator::validateMovie(const Movie &movieUsed) {
    std::string title = movieUsed.getTitle();
    int likes = movieUsed.getNumberOfLikes();
    int year = movieUsed.getYearOfRelease();
    std::string link = movieUsed.getTrailer();
    std::string errorsList = "";
    if(title.size() < 2)
        errorsList = "Invalid title ";
    if (likes < 0)
        errorsList += "Invalid likes ";
    if (year < 1900 || year > 9999)
        errorsList += "Invalid year ";
    if (link.find("www.") != 0)
        errorsList += "Invalid trailer ";
    if (errorsList != "")
        throw ValidationException(errorsList);
}