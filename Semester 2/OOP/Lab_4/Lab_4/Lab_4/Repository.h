#pragma once

#include "DynamicVector.h"
#include "Movie.h"
class Repository
{
private:
    std::vector<Movie> movieList;
public:
    Repository();
    int addMovie(const Movie& movieToAdd);
    int deleteMovie(const Movie& movieToDelete);
    int updateMovie(const Movie& ToUpdateWith);
    Movie getMovieAtPosition(int positionOfMovie);
    int getNumberOfMovies();

};