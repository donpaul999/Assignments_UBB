#pragma once
#include "Repository.h"
#include "Observer.h"
#include "ExceptionClass.h"
class Service: public Subject
{
private:
	Repository* repo;
public:
	Service() {};
	Service(Repository* repo) {this->repo = repo;};
	const std::vector<std::string>explode(const std::string& stringToExplode, const std::string& separatorsUsed);
	void addStar(std::string name = "", std::string constellation = "", int RA = 0, int Dec = 0, int diameter = 0);//parametrii object;
	void deleteObject();//parametrii object;
	std::vector<Star> searchForStar(std::string input) {
		return repo->searchStarByName(input);
	};//parametrii object;
	std::vector<TElem> getAllObjects() { return repo->getAllObjects(); };
	std::vector <Star> getAllStars() {
		return repo->getAllStars();
	};
	
	std::vector <Star> getStarsWithConstellation(std::string constellation) {
		std::vector <Star> stars =  repo->getStarsWithConstellation(constellation);
		return stars;
	};
};

