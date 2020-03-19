#include "Movie.h"
#include <sstream>

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

bool Movie::operator==(const Movie& movieToCheck) const
{
	return title == movieToCheck.title; //maybe there can be 2 movies with the same title
}

bool Movie::operator!=(const Movie& movieToCheck) const
{
	return !(movieToCheck == *this);
}

std::string Movie::getOutputForm() const
{
	std::string finalOutputForm = "Title: " + title + ", Genre: " + genre + ", Year of Release: " + std::to_string(yearOfRelease) + ", Likes: " + std::to_string(numberOfLikes) + ", Trailer: " + trailer + ";";
	return finalOutputForm;
}

std::ostream& operator<<(std::ostream& outStream, const Movie& movieToOutput)
{
	outStream <<movieToOutput.getOutputForm();
	return outStream;
}

