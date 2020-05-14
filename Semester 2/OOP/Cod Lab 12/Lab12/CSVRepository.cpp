//
// Created by Paul Colta on 30/04/2020.
//

#include "CSVRepository.h"


void CSVRepository::writeUserMoviesToFile( std::vector<Movie> movieList, std::string movieFileName) {
    std::ofstream fileOutput(movieFileName);
    fileOutput << "Title,Genre,Year of release,Number of likes, Trailer link\n";
    if(movieList.empty())
        return;
    for (const Movie& movieUsed: movieList) {
        fileOutput << '"' << movieUsed.getTitle() << "\","
                   << '"' << movieUsed.getGenre() << "\","
                   << '"' << movieUsed.getYearOfRelease() << "\","
                   << '"' << movieUsed.getNumberOfLikes() << "\","
                   << '"' << movieUsed.getTrailer() << '"' << '\n';
    }
    fileOutput.close();
}
//Get all the movies in the watch list
std::vector<Movie> CSVRepository::getAllWatchListMovies()
{
    std::vector<Movie> movieList;
    std::vector<std::string> tokenizedInput;
    std::string textLineFromFile, replacedString;
    std::ifstream fileInput(userFileName); //remove unused lines
    getline(fileInput, textLineFromFile);
    try {
        while (!fileInput.eof() && textLineFromFile != "") {
            getline(fileInput, textLineFromFile);
            tokenizedInput = explode(textLineFromFile, "\"");
            if(tokenizedInput.size() != 0) {
                Movie movieUsed = {tokenizedInput[0], tokenizedInput[2], stoi(tokenizedInput[4]),
                                   stoi(tokenizedInput[6]),
                                   tokenizedInput[8]};
                movieList.push_back(movieUsed);
            }
        }
    }
    catch(std::exception& e){
        return std::vector<Movie>();
    }
    fileInput.close();
    return movieList;

}


//Add a certain movie to the watch list
int CSVRepository::addMovieToWatchlist(const Movie& movieToAdd)
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
void CSVRepository::deleteMovieFromWatchlist(const Movie& movieToDelete)
{
    std::vector<Movie> userWatchListFromFile = getAllWatchListMovies();
    auto iteratorWhereMovieIsFound = std::find(userWatchListFromFile.begin(), userWatchListFromFile.end(), movieToDelete);
    if (iteratorWhereMovieIsFound == userWatchListFromFile.end())
        throw RepositoryException(std::string("Movie not in the watch list"));
    userWatchListFromFile.erase(iteratorWhereMovieIsFound);
    writeUserMoviesToFile(userWatchListFromFile, userFileName);
}

//Add a certain movie to the watch list by the title
int CSVRepository::addMovieToWatchListByTitle(const std::string& titleOfTheMovieToAdd)
{
    std::vector<Movie> userWatchListFromFile = getAllWatchListMovies();
    std::vector<Movie> movieList = loadMoviesFromFile();
    auto iteratorWhereMovieFound = std::find_if(movieList.begin(), movieList.end(), [&titleOfTheMovieToAdd](const Movie& movie) {return movie.getTitle() == titleOfTheMovieToAdd; });
    if (iteratorWhereMovieFound == movieList.end())
        throw RepositoryException(std::
                                  string("Movie with this title not exist."));
    auto secondIteratorWhereMovieIsFound = std::find_if(userWatchListFromFile.begin(), userWatchListFromFile.end(), [&titleOfTheMovieToAdd](const Movie& movie) {return movie.getTitle() == titleOfTheMovieToAdd; });
    if (secondIteratorWhereMovieIsFound != userWatchListFromFile.end())
        throw RepositoryException(std::string("Movie already in the watch list"));
    userWatchListFromFile.push_back(*iteratorWhereMovieFound);
    writeUserMoviesToFile(userWatchListFromFile, userFileName);
    return 1;
}

//Get the number of movies in the watch list
int CSVRepository::getNumberOfMoviesWatchList()
{
    std::vector<Movie>userWatchListFromFile = getAllWatchListMovies();
    return userWatchListFromFile.size();
}