#include "Service.h"

const std::vector<std::string>Service::explode(const std::string& stringToExplode, const std::string& separatorsUsed)
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

void Service::addObject()
{
	TElem objectToAdd;
	//creare obiect;
	repo->addObject(objectToAdd);
}

void Service::deleteObject()
{
	TElem objectToDelete;
	//creare obiect;
	repo->deleteObject(objectToDelete);
}
//input - name of manufacturer
//return - the number of cars with the given manufacturer
int Service::sortByManufacturer(std::string manufacturer)
{
	std::vector<TElem>objectList = repo->getAllObjectsByFacturer(), secondObjectList;
	for (int i = 0; i < objectList.size(); ++i)
		if (objectList[i].getManufacturer() == manufacturer)
			secondObjectList.push_back(objectList[i]);
	return secondObjectList.size();

}

