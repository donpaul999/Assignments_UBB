#include "User.h"

User::User(std::string newName, std::string newType)
{
	this->name = newName;
	this->type = newType;
}

std::string User::getType()
{
	return this->type;
}

std::string User::getName()
{
	return this->name;
}
