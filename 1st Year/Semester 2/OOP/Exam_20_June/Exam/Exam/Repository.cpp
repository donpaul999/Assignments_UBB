#include "Repository.h"
#include <iostream>

std::vector <TElem> Repository::loadFromFile(std::string fileName)
{
    std::vector<TElem> objectList;
	
	std::string textLineFromFile, replacedString;
    std::ifstream fileInput(fileName); //remove unused lines
    std::vector<std::string> tokenizedInput;
    getline(fileInput, textLineFromFile);
    while (!fileInput.eof() && textLineFromFile != "") {
        tokenizedInput = explode(textLineFromFile, ",");
		Astronomer object{ tokenizedInput[0], tokenizedInput[1]};
		objectList.push_back(object);
        getline(fileInput, textLineFromFile);
    }
	
	return objectList;
	fileInput.close();

}

std::vector<Star> Repository::loadStarsFromFile(std::string fileName)
{
	std::vector<Star> objectList;

	std::string textLineFromFile, replacedString;
	std::ifstream fileInput(fileName); //remove unused lines
	std::vector<std::string> tokenizedInput;
	getline(fileInput, textLineFromFile);
	while (!fileInput.eof() && textLineFromFile != "") {
		tokenizedInput = explode(textLineFromFile, ",");
		Star object{ tokenizedInput[0], tokenizedInput[1], stoi(tokenizedInput[2]), stoi(tokenizedInput[3]) };
		objectList.push_back(object);
		getline(fileInput, textLineFromFile);
	}

	return objectList;
	fileInput.close();
}

void Repository::writeToFile(std::string fileName)
{
	bool sortat = 0; 
	while (!sortat) {
		sortat = 1;
		for (int i = 0; i < starList.size() - 1; ++i)
			if (starList[i].getConstellation() > starList[i + 1].getConstellation())
			{
				Star aux = starList[i];
				starList[i] = starList[i + 1];
				starList[i + 1] = aux;
				sortat = 0;
			}
	}
	std::ofstream fileOutput(fileName); 

	for (int i = 0; i < starList.size(); ++i)
		fileOutput << starList[i].getName() << "," << starList[i].getConstellation() << "," << starList[i].getRA() << "," << starList[i].getDec() << "," << starList[i].getDiameter() << '\n';

}

const std::vector<std::string>Repository::explode(const std::string& stringToExplode, const std::string& separatorsUsed)
{
	std::vector<std::string>trashValues = {};
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

void Repository::addObject(TElem objectToAdd)
{
	objectList.push_back(objectToAdd);
}

void Repository::deleteObject(TElem objectToDelete)
{
	auto it = std::find(objectList.begin(), objectList.end(), objectToDelete);
	if (it != objectList.end())
		objectList.erase(it);
	//validari pe else
}

std::vector<Star> Repository::getStarsWithConstellation(std::string constellation)
{
	std::vector<Star> stars;
	for (int i = 0; i < starList.size(); ++i)
		if (starList[i].getConstellation() == constellation)
			stars.push_back(starList[i]);
	return stars;
}

std::vector<Star> Repository::searchStarByName(std::string input)
{
	std::vector <Star> stars;
	for (int i = 0; i < starList.size(); ++i)
		if (starList[i].getName().find(input) != std::string::npos) {
			stars.push_back(starList[i]);
		}
	return stars;
}

