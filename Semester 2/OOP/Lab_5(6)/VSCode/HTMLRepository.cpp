//
// Created by Paul Colta on 16/04/2020.
//

#include "HTMLRepository.h"
#include <fstream>


HTMLRepository::HTMLRepository(const std::string &movieFileName, const std::string &HTMLfileName):HTMLfileName{HTMLfileName}{}

HTMLRepository::~HTMLRepository() {
    updateHTMLFile();
}

void HTMLRepository::updateHTMLFile() {
    std::ofstream fileOutput(HTMLfileName);
    fileOutput << "<!DOCTYPE html>\n<html><head><title>Watchlist</title></head><body>\n";
    fileOutput << "<table border=\"1\">\n";
    fileOutput << "<tr><td>Title</td><td>Genre</td><td>Year of release</td><td>Number of likes</td><td>Link</td></tr>\n";
    for (const Movie& movieUsed: userWatchList) {
        fileOutput    << "<tr><td>" << movieUsed.getTitle() << "</td>"
                      << "<td>" << movieUsed.getGenre() << "</td>"
                      << "<td>" << movieUsed.getYearOfRelease() << "</td>"
                      << "<td>" << movieUsed.getNumberOfLikes() << "</td>"
                      << "<td><a href=\"" << movieUsed.getTrailer() << "\">Link</a></td>" << '\n';
    }
    fileOutput << "</table></body></html>";
    fileOutput.close();
}



void HTMLRepository::addMovieToWatchlist(const Movie &movieToAdd) {
    FileRepository::addMovieToWatchlist(movieToAdd);
    updateHTMLFile();
}

void HTMLRepository::deleteMovieFromWatchlist(const Movie &movieToDelete) {
    FileRepository::deleteMovieFromWatchlist(movieToDelete);
    updateHTMLFile();
}

std::string HTMLRepository::getFilename() {
    return HTMLfileName;
}