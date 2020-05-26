#pragma once
#include "Repository.h"
typedef Car TElem;
class Service
{
private:
	Repository* repo;
public:
	Service() {};
	Service(Repository* repo) {this->repo = repo;};
	const std::vector<std::string>explode(const std::string& stringToExplode, const std::string& separatorsUsed);
	void addObject();//parametrii object;
	void deleteObject();//parametrii object;
	std::vector<TElem> getAllObjects() { return repo->getAllObjects(); };
	std::vector<TElem> getAllObjectsByManufacturer() { return repo->getAllObjectsByFacturer(); };
	int sortByManufacturer(std::string manufacturer);

};

