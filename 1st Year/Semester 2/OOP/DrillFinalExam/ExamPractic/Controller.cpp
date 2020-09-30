#include "Controller.h"

void Controller::addIssue(User user, std::string newDescription , bool newStatus, std::string newReporter , std::string newSolver)
{
	if (newDescription == "")
	{
		throw std::runtime_error("Empty description!");
	}

	if (user.getType() == "programmer")
	{ 
		throw std::runtime_error("User is programmer!");
	}

	std::vector<Issue> issues = this->repository->getIssues();

	for (int i = 0; i < issues.size(); i++)
	{
		if (issues[i].getDescription() == newDescription)
		{
			throw std::runtime_error("Description not unique!");
		}
	}

	Issue newIssue(newDescription, newStatus, newReporter, newSolver);
	this->repository->addIssue(newIssue);
	notify();

}

void Controller::removeIssue(std::string newDescription, bool newStatus, std::string newReporter, std::string newSolver)
{
	if (newDescription == "")
	{
		throw std::runtime_error("Empty description!");
	}

	Issue selectedIssue(newDescription, newStatus, newReporter, newSolver);
	this->repository->removeIssue(selectedIssue);
	notify();

}

void Controller::resolveIssue(User user, std::string newDescription)
{
	if (newDescription == "")
	{
		throw std::runtime_error("Empty description!");
	}

	if (user.getType() == "tester")
	{
		throw std::runtime_error("User is tester!");
	}

	

	this->repository->resolveIssue(newDescription, user.getName());
	notify();
}

std::vector<Issue> Controller::getAllIssues()
{
	return this->repository->getIssues();
}

std::vector<User> Controller::getAllUsers()
{
	return this->repository->getUsers();
}
