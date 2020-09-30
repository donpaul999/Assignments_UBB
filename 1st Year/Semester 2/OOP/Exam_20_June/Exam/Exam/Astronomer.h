#pragma once
#include <string>
class Astronomer
{
private:
	std::string name;
	std::string constellation;
public:
	Astronomer(std::string name = "", std::string constellation = "") {
		this->name = name; this->constellation = constellation;
	};
	std::string getName() { return name; };
	std::string getConstellation() { return constellation; };
	bool operator==(const Astronomer& a) {
		return a.name == name;
	}
};

