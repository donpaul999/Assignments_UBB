//
// Created by Paul Colta on 30/04/2020.
//

#include "HTMLRepository.h"
#include <iostream>

void HTMLRepository::writeUserMoviesToFile( std::vector<Movie> movieList, std::string movieFileName) {
    std::ofstream fileOutput(movieFileName);
    fileOutput << "<!DOCTYPE html>\n<html><head><title>Watchlist</title></head><body>\n";
    fileOutput << "<table border=\"1\">\n";
    fileOutput
            << "<tr><td>Title</td><td>Genre</td><td>Year of release</td><td>Number of likes</td><td>Link</td></tr>\n";
    for (const Movie &movieUsed: movieList) {
        fileOutput << "<tr><td>" << movieUsed.getTitle() << "</td>"
                   << "<td>" << movieUsed.getGenre() << "</td>"
                   << "<td>" << movieUsed.getYearOfRelease() << "</td>"
                   << "<td>" << movieUsed.getNumberOfLikes() << "</td>"
                   << "<td><a href=\"" << movieUsed.getTrailer() << "\">"<<movieUsed.getTrailer()<<"</a></td>" << '\n';
    }
    fileOutput << "</table></body></html>";
    fileOutput.close();
}

//Get all the movies in the watch list
std::vector<Movie> HTMLRepository::getAllWatchListMovies()
{
    std::vector<Movie> movieList;
    std::vector<std::string> tokenizedInput;
    std::regex tags("<.*?>");
    std::string textLineFromFile, replacedString;
    std::ifstream fileInput(userFileName); //remove unused lines
    getline(fileInput, textLineFromFile);
    if(textLineFromFile == "")
        return movieList;
    getline(fileInput, textLineFromFile);
    getline(fileInput, textLineFromFile);
    getline(fileInput, textLineFromFile);
    while(!fileInput.eof() && textLineFromFile != ""){
        getline(fileInput, textLineFromFile);
        std::regex_replace(std::back_inserter(replacedString), textLineFromFile.begin(), textLineFromFile.end(), tags, " ");
        textLineFromFile = replacedString;
        replacedString.clear();
        //std::cout << textLineFromFile << '\n';
        tokenizedInput = explode(textLineFromFile, " ");
        if(tokenizedInput.size() > 0) {
            Movie movieUsed = {tokenizedInput[0], tokenizedInput[1], stoi(tokenizedInput[2]), stoi(tokenizedInput[3]),
                               tokenizedInput[4]};
            movieList.push_back(movieUsed);
        }
    }

    return movieList;
}


//Add a certain movie to the watch list
int HTMLRepository::addMovieToWatchlist(const Movie& movieToAdd)
{
    std::vector<Movie> userWatchListFromFile = getAllWatchListMovies();
    auto iteratorWhereMovieIsFound = std::find(userWatchListFromFile.begin(), userWatchListFromFile.end(), movieToAdd);
    if (iteratorWhereMovieIsFound != userWatchListFromFile.end())
        throw RepositoryException(std::string("Movie already in the watch list"));
    userWatchListFromFile.push_back(movieToAdd);
    writeUserMoviesToFile(userWatchListFromFile, userFileName);
    return 1;
}

//
void HTMLRepository::deleteMovieFromWatchlist(const Movie& movieToDelete)
{
    std::vector<Movie> userWatchListFromFile = getAllWatchListMovies();
    auto iteratorWhereMovieIsFound = std::find(userWatchListFromFile.begin(), userWatchListFromFile.end(), movieToDelete);
    if (iteratorWhereMovieIsFound == userWatchListFromFile.end())
        throw RepositoryException(std::string("Movie not in the watch list"));
    userWatchListFromFile.erase(iteratorWhereMovieIsFound);
    writeUserMoviesToFile(userWatchListFromFile, userFileName);
}

//Add a certain movie to the watch list by the title
int HTMLRepository::addMovieToWatchListByTitle(const std::string& titleOfTheMovieToAdd)
{
    std::vector<Movie> userWatchListFromFile = getAllWatchListMovies();
    std::vector<Movie> movieList = loadMoviesFromFile();
    auto iteratorWhereMovieFound = std::find_if(movieList.begin(), movieList.end(), [&titleOfTheMovieToAdd](const Movie& movie) {return movie.getTitle() == titleOfTheMovieToAdd; });
    if (iteratorWhereMovieFound == movieList.end())
        throw RepositoryException(std::
                                  string("Movie with this title not exist."));
    auto secondIteratorWhereMovieIsFound = std::find_if(userWatchListFromFile.begin(), userWatchListFromFile.end(), [&titleOfTheMovieToAdd](const Movie& movie) {return movie.getTitle() == titleOfTheMovieToAdd; });
    if (secondIteratorWhereMovieIsFound != userWatchListFromFile.end() && userWatchListFromFile.size() > 0)
        throw RepositoryException(std::string("Movie already in the watch list"));
    userWatchListFromFile.push_back(*iteratorWhereMovieFound);
    writeUserMoviesToFile(userWatchListFromFile, userFileName);
    return 1;
}

//Get the number of movies in the watch list
int HTMLRepository::getNumberOfMoviesWatchList()
{
    std::vector<Movie>userWatchListFromFile = getAllWatchListMovies();
    return userWatchListFromFile.size();
}