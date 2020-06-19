#pragma once
#include "Issue.h"
#include "User.h"
#include <vector>
#include <fstream>

class Repository
{
private:
	std::vector<User> users;
	std::vector<Issue> issues;

    std::string usersFileName;
    std::string issuesFileName;

public:
	Repository() {
        this->usersFileName = "";
        this->issuesFileName = "";
    };

    Repository(std::string newUsersFileName, std::string newIssuesFileName);

    void addIssue(Issue newIssue);
    void removeIssue(Issue selectedIssue);
    void resolveIssue(std::string dscription, std::string programmer);

	std::vector<User> readUsers();
	std::vector<Issue> readIssues();
    void writeIssues(std::vector<Issue> outputIssues);

    std::vector<Issue> getIssues();
    std::vector<User> getUsers();
    


    const std::vector<std::string>explode(const std::string& stringToExplode, const std::string& separatorsUsed)
    {
        std::vector<std::string>trashValues = {};
        std::string partialStringObtained{ "" };
        std::vector<std::string> explodedString;

        for (auto iterator : stringToExplode)
        {
            if (iterator != separatorsUsed[0] && iterator != separatorsUsed[1])
                partialStringObtained += iterator;
            else
                if ((iterator == separatorsUsed[0] || iterator == separatorsUsed[1]) && partialStringObtained != "") {
                    if (find(trashValues.begin(), trashValues.end(), partialStringObtained) == trashValues.end()) {
                        explodedString.push_back(partialStringObtained);
                    }
                    partialStringObtained = "";
                }
        }
        if (partialStringObtained != "")
            explodedString.push_back(partialStringObtained);
        return explodedString;
    }

};

