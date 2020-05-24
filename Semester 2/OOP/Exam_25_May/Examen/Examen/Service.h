#pragma once
#include "Repository.h"
typedef int TElem;
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
};

