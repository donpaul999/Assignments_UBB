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
        tokenizedInput = explode(textLineFromFile, " | ");
		Car object{ tokenizedInput[0], tokenizedInput[1], stoi(tokenizedInput[2]), tokenizedInput[3]};
		objectList.push_back(object);
        getline(fileInput, textLineFromFile);
    }
	this->objectList = objectList;
	return objectList;
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
