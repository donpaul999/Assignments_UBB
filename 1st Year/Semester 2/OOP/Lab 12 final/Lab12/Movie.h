#pragma once
#include <string>
#include <vector>

class Movie {
private:
    std::string title;
    std::string trailer;
    std::string genre;
    int yearOfRelease;
    int numberOfLikes;

public:
    Movie(const std::string& title = "", const std::string& genre = "", int yearOfRelease = 2020, int numberOfLikes = 0, const std::string& trailer = "");
    Movie(const Movie& movieToUse);
    void setTitle(const std::string& title);
    void setGenre(const std::string& genre);
    void setYearOfRelease(int yearOfRelease);
    void setNumberOfLikes(int numberOfLikes);
    void setTrailer(const std::string& trailer);
    const std::string& getTitle() const;
    int getYearOfRelease() const;
    const std::string& getGenre() const;
    int getNumberOfLikes() const;
    const std::vector<std::string >explode(const std::string& stringToExplode, const std::string& separatorsUsed);
    const std::string& getTrailer() const;
    bool operator==(const Movie& movieToCheck) const;
    void operator=(const Movie& movieToGetValuesFrom);
    bool operator!=(const Movie& movieToCheck) const;
    std::string getOutputForm() const;
    friend std::ostream& operator<< (std::ostream& outStream, const Movie& movieToOutput);
    friend std::istream& operator>> (std::istream& inStream, Movie& movie);

};


