#pragma once
#include "Repository.h"
#include "Observer.h"
class Service: public Subject
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

};

