#pragma once
#include <string>

class User
{
private:
	std::string name;
	std::string type;
public:
	User() { this->name = ""; this->type = ""; }
	User(std::string newName, std::string newType);

	std::string getType();
	std::string getName();
};

