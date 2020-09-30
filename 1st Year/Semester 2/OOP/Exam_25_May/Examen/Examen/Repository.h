#pragma once
#include <vector>
#include <string>
#include <fstream>
#include <iostream>
#include "Car.h"

typedef Car TElem;

class Repository
{
private:
	std::string fileName;
	std::vector <TElem> objectList;
public:
	Repository(std::string fileName = "") :fileName(fileName) { objectList = loadFromFile(fileName); };
	std::vector <TElem> loadFromFile(std::string fileName);
	const std::vector<std::string>explode(const std::string& stringToExplode, const std::string& separatorsUsed);
	void addObject(TElem objectToAdd);
	void deleteObject(TElem objectToDelete);
	std::vector <TElem> getAllObjects() {
		return objectList;
	};
	std::vector <TElem> getAllObjectsByFacturer() {
		std::vector <TElem> secondObjectList = objectList;
		bool sorted = 0;
		while (!sorted) {
            sorted = 1;
			for (int i = 0; i < secondObjectList.size() - 1; ++i)
				if (secondObjectList[i].getManufacturer() > secondObjectList[i + 1].getManufacturer()) {
					Car aux = secondObjectList[i];
					secondObjectList[i] = secondObjectList[i + 1];
					secondObjectList[i + 1] = aux;
					sorted = 0;
				}
		}
		return secondObjectList;
	};
};

