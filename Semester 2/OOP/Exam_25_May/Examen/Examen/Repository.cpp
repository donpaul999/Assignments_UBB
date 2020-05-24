#include "Repository.h"

std::vector <TElem> Repository::loadFromFile(std::string fileName)
{
    std::vector<TElem> objectList;
	TElem object;
	std::string textLineFromFile, replacedString;
    std::ifstream fileInput(fileName); //remove unused lines
    getline(fileInput, textLineFromFile);
    std::vector<std::string> tokenizedInput;
    while (!fileInput.eof() && textLineFromFile != "") {
        tokenizedInput = explode(textLineFromFile, "; ");
		//create object;
		objectList.push_back(object);
        getline(fileInput, textLineFromFile);
    }
	return objectList;
}

const std::vector<std::string>Repository::explode(const std::string& stringToExplode, const std::string& separatorsUsed)
{
	std::vector<std::string>trashValues = { " " };
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
	//validari???
	objectList.push_back(objectToAdd);
}

void Repository::deleteObject(TElem objectToDelete)
{
	auto it = std::find(objectList.begin(), objectList.end(), objectToDelete);
	if (it != objectList.end())
		objectList.erase(it);
	//validari pe else
}
