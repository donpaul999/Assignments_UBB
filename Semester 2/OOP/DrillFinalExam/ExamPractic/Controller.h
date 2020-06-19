#pragma once
#include "Repository.h"
#include "Observer.h"
class Controller: public Subject
{
private:
	Repository* repository;
public:
	Controller() {};
	Controller(Repository* newRepository) : repository{ newRepository } { };

	void addIssue(User user, std::string newDescription = "", bool newStatus = true, std::string newReporter = "", std::string newSolver = "");
	void removeIssue(std::string newDescription = "", bool newStatus = true, std::string newReporter = "", std::string newSolver = "");
	void resolveIssue(User user, std::string newDescription = "");

	std::vector<Issue> getAllIssues();
	std::vector<User> getAllUsers();
};

