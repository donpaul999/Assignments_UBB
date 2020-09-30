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

//Parameters- the data for a star
//If the name/diameter is invalid, throw an exception error
//else call Repo function to add a star
void Service::addStar(std::string name, std::string constellation, int RA, int Dec, int diameter)
{
	if (name == "")
		throw ExceptionClass("Empty Name");
	if(diameter <= 0)
		throw ExceptionClass("Invalid diameter");
	Star x(name, constellation, RA, Dec, diameter);
	std::vector<Star> starList = repo->getAllStars();
	for(int i = 0; i < starList.size(); ++i)
		if(x.getName() == starList[i].getName())
			throw ExceptionClass("Name already used");
	repo->addStar(x);
	notify();
}


void Service::deleteObject()
{
	TElem objectToDelete;
	//creare obiect;
	repo->deleteObject(objectToDelete);
    notify();

}


