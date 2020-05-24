#pragma once
#include <vector>
#include <string>
#include <fstream>
typedef int TElem;

class Repository
{
private:
	std::string fileName;
	std::vector <TElem> objectList;
public:
	Repository(std::string fileName = "") :fileName(fileName) { if (fileName.size() > 0) objectList = loadFromFile(fileName); };
	std::vector <TElem> loadFromFile(std::string fileName);
	const std::vector<std::string>explode(const std::string& stringToExplode, const std::string& separatorsUsed);
	void addObject(TElem objectToAdd);
	void deleteObject(TElem objectToDelete);
};

