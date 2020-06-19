#include "Issue.h"

Issue::Issue(std::string newDescription, bool newStatus, std::string newReporter, std::string newSolver)
{
	this->description = newDescription;
	this->status = newStatus;
	this->reporter = newReporter;
	this->solver = newSolver;
}

std::string Issue::getDescription()
{
	return this->description;
}

bool Issue::getStatus()
{
	return this->status;
}

std::string Issue::getReporter()
{
	return this->reporter;
}

std::string Issue::getSolver()
{
	return this->solver;
}

void Issue::setDescription(std::string newDescription)
{
	this->description = newDescription;
}

void Issue::setStatus(bool newStatus)
{
	this->status = newStatus;
}

void Issue::setReporter(std::string newReporter)
{
	this->reporter = newReporter;
}

void Issue::setSolver(std::string newSolver)
{
	this->solver = newSolver;
}
