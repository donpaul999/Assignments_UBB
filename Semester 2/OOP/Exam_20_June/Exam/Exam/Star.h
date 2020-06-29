#pragma once
#include <string>
class Star
{
private:
	std::string name;
	std::string constellation;
	int RA, Dec, diameter;
public:
	Star(std::string name = "", std::string constellation = "", int RA = 0, int Dec = 0, int diameter = 0) {
		this->name = name;
		this->constellation = constellation;
		this->RA = RA;
		this->Dec = Dec;
		this->diameter = diameter;
	}
	std::string getName() { return name; };
	std::string getConstellation() { return constellation; };
	int getRA() { return RA; };
	int getDec() { return Dec; };
	int getDiameter() { return diameter; };
};

