#include "Movie.h"
#include <iostream>
#include <algorithm>

//Movie constructors

Movie::Movie(const std::string& title, const std::string& genre, int yearOfRelease, int numberOfLikes, const std::string& trailer): title(title),
																																	genre(genre), 
																																	yearOfRelease(yearOfRelease),
																																	numberOfLikes(numberOfLikes),
																																	trailer(trailer) {}


Movie::Movie(const Movie& movieToUse):title{movieToUse.title},
                                       genre{movieToUse.genre},
                                       yearOfRelease{movieToUse.yearOfRelease},
                                       numberOfLikes{movieToUse.numberOfLikes},
                                       trailer{movieToUse.trailer}
                                       {}
//End of Movie constructors



void Movie::setTitle(const std::string& title)
{
	Movie::title = title;

}

void Movie::setGenre(const std::string& genre)
{
	Movie::genre = genre;
}

void Movie::setYearOfRelease(int yearOfRelease)
{
	Movie::yearOfRelease = yearOfRelease;
}

void Movie::setNumberOfLikes(int numberOfLikes)
{
	Movie::numberOfLikes = numberOfLikes;
}

void Movie::setTrailer(const std::string& trailer)
{
    Movie::trailer = trailer;
}

const std::string& Movie::getTitle() const
{
	return title;
}

int Movie::getYearOfRelease() const
{
	return yearOfRelease;
}

const std::string& Movie::getGenre() const
{
	return genre;
}

int Movie::getNumberOfLikes() const
{
	return numberOfLikes;
}

const std::string& Movie::getTrailer() const
{
	return trailer;
}

//Operators overload

bool Movie::operator==(const Movie& movieToCheck) const
{
	return title == movieToCheck.getTitle(); //maybe there can be 2 movies with the same title
}

void Movie::operator=(const Movie& movieToGetValuesFrom)
{
	title = movieToGetValuesFrom.getTitle();
	genre = movieToGetValuesFrom.getGenre();
	yearOfRelease = movieToGetValuesFrom.getYearOfRelease();
	numberOfLikes = movieToGetValuesFrom.getNumberOfLikes();
	trailer = movieToGetValuesFrom.getTrailer();
}

bool Movie::operator!=(const Movie& movieToCheck) const
{
	return !(movieToCheck == *this);
}

std::ostream& operator<<(std::ostream& outStream, const Movie& movieToOutput)
{
	outStream << movieToOutput.getOutputForm();
	return outStream;}

std::istream& operator>> (std::istream& inStream, Movie& movie) {
	std::string movieString;
	std::getline(inStream, movieString);
	std::vector <std::string> tokens;
	if (movieString == "") {
		return inStream;
	}
	tokens = movie.explode(movieString, ",:");
	movie.title = tokens[0].substr(1);
	movie.genre = tokens[1].substr(1);
	movie.yearOfRelease = std::stoi(tokens[2].substr(1));
	movie.numberOfLikes = std::stoi(tokens[3].substr(1));
	movie.trailer = tokens[4].substr(1);
	movie.trailer = movie.trailer.substr(0, movie.trailer.size() - 1);
	return inStream;
}
//End of operators overload



/*
	Explode a string after certain separators - stringToExplode - string to be separated
											  separatorsUsed - characters used to divide the string
*/
const std::vector<std::string>Movie::explode(const std::string& stringToExplode, const std::string& separatorsUsed)
{
	std::vector<std::string>trashValues = { "Title", " Genre", " Year of Release", " Likes", " Trailer" };
	std::string partialStringObtained{ "" };
	std::vector<std::string> explodedString;

	for (auto iterator : stringToExplode)
	{
		if (iterator != separatorsUsed[0] && iterator != separatorsUsed[1])
			partialStringObtained += iterator;
		else
			if ((iterator == separatorsUsed[0] || iterator == separatorsUsed[1]) && partialStringObtained != "") {
				if (find(trashValues.begin(), trashValues.end(), partialStringObtained) == trashValues.end()) {
					explodedString.push_back(partialStringObtained);
				}
				partialStringObtained = "";
			}
	}
	if (partialStringObtained != "")
		explodedString.push_back(partialStringObtained);
	return explodedString;
}


//This is how a movie is represented
std::string Movie::getOutputForm() const
{
	std::string finalOutputForm = "Title: " + title + ", Genre: " + genre + ", Year of Release: " + std::to_string(yearOfRelease) + ", Likes: " + std::to_string(numberOfLikes) + ", Trailer: " + trailer + ";";
	return finalOutputForm;
}

