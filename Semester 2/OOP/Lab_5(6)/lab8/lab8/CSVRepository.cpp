//
// Created by Paul Colta on 16/04/2020.
//

#include "CSVRepository.h"
#include <fstream>

CSVRepository::CSVRepository(const std::string &movieFileName, const std::string &CSVfileName): CSVfileName{CSVfileName}
{}

CSVRepository::~CSVRepository() {
    updateCSVFile();
}

void CSVRepository::updateCSVFile() {
    std::ofstream fileOutput(CSVfileName);
    fileOutput << "Title,Genre,Year of release,Number of likes, Trailer link\n";
    for (const Movie& movieUsed: userWatchList) {
        fileOutput << '"' << movieUsed.getTitle() << "\","
                   << '"' << movieUsed.getGenre() << "\","
                   << '"' << movieUsed.getYearOfRelease() << "\","
                   << '"' << movieUsed.getNumberOfLikes() << "\","
                   << '"' << movieUsed.getTrailer() << '"' << '\n';
    }
    fileOutput.close();
}

void CSVRepository::addMovieToWatchlist(const Movie &movieToAdd) {
    FileRepository::addMovieToWatchlist(movieToAdd);
    updateCSVFile();
}

void CSVRepository::deleteMovieFromWatchlist(const Movie &movieToDelete) {
    FileRepository::deleteMovieFromWatchlist(movieToDelete);
    updateCSVFile();
}

std::string CSVRepository::getFilename() {
    return CSVfileName;
}