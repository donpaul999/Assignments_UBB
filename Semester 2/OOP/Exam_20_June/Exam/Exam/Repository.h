#pragma once
#include <vector>
#include <string>
#include <fstream>
#include <iostream>
#include <string>
#include "Astronomer.h"
#include "Star.h"
typedef Astronomer TElem;

class Repository
{
private:
	std::string fileName, starFileName;
	std::vector <TElem> objectList;
	std::vector <Star> starList;
public:
	Repository(std::string fileName = "", std::string starFileName = "") :fileName(fileName), starFileName(starFileName) { objectList = loadFromFile(fileName); starList = loadStarsFromFile(starFileName); };
	std::vector <TElem> loadFromFile(std::string fileName);
	std::vector <Star> loadStarsFromFile(std::string fileName);
	void writeToFile(std::string fileName);
	const std::vector<std::string>explode(const std::string& stringToExplode, const std::string& separatorsUsed);
	void addObject(TElem objectToAdd);
	void deleteObject(TElem objectToDelete);
	std::vector <TElem> getAllObjects() {
		return objectList;
	};
	std::vector <Star> getAllStars() {
		return starList;
	};

	std::vector <Star> getStarsWithConstellation(std::string constellation);

	//Insert a star into the starList
	//After that, rewrite the data inside the star file
	void addStar(Star x) {
		starList.push_back(x);
		writeToFile(starFileName);
	};

	std::vector <Star> searchStarByName(std::string input);
};

