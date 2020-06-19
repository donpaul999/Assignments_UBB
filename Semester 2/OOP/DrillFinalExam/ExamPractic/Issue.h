#pragma once
#include <string>

class Issue
{
private:
	std::string description;
	bool status;
	std::string reporter;
	std::string solver;
public:
	Issue(std::string newDescription = "", bool newStatus = false, std::string newReporter = "", std::string newSolver = "");
	
	std::string getDescription();
	bool getStatus();
	std::string getReporter();
	std::string getSolver();

	void setDescription(std::string newDescription);
	void setStatus(bool newStatus);
	void setReporter(std::string newReporter);
	void setSolver(std::string newSolver);
	
	~Issue() { };
};

