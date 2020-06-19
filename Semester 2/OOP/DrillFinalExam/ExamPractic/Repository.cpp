#include "Repository.h"

Repository::Repository(std::string newUsersFileName, std::string newIssuesFileName)
{
    this->usersFileName = newUsersFileName;
    this->issuesFileName = newIssuesFileName;
    this->users = readUsers();
    this->issues = readIssues();
}

void Repository::addIssue(Issue newIssue)
{
    this->issues.push_back(newIssue);
    this->writeIssues(this->issues);
}

void Repository::removeIssue(Issue selectedIssue)
{
    for(int i = 0 ; i < issues.size(); ++i){
        if (issues[i].getDescription() == selectedIssue.getDescription())
        {
            this->issues.erase(issues.begin() + i);
            break;
        }
    }
    this->writeIssues(this->issues);
}

void Repository::resolveIssue(std::string description, std::string programmer)
{
    for (int i = 0; i < issues.size(); ++i) {
        if (issues[i].getDescription() == description)
        {
            this->issues[i].setSolver(programmer);
            this->issues[i].setStatus(false);
        }
    }
}

std::vector<User> Repository::readUsers()
{
	std::vector<User> inputUsers;

    std::string line;

    std::ifstream fileInput(this->usersFileName);

    std::vector<std::string> elementsFromLine;
    getline(fileInput, line);

    while (!fileInput.eof() && line != "")
    {
        elementsFromLine = explode(line, ", ");
        User newUser(elementsFromLine[0], elementsFromLine[1]);

        inputUsers.push_back(newUser);
        getline(fileInput, line);
    }
    fileInput.close();
    return inputUsers;
}

std::vector<Issue> Repository::readIssues()
{
    std::vector<Issue> inputIssues;

    std::string line;

    std::ifstream fileInput(this->issuesFileName);

    std::vector<std::string> elementsFromLine;
    getline(fileInput, line);

    while (!fileInput.eof() && line != "")
    {
        elementsFromLine = explode(line, ",");
        if (elementsFromLine[1] == "open")
        {
            Issue newIssue(elementsFromLine[0], true, elementsFromLine[2]);
            inputIssues.push_back(newIssue);
        } else {
            Issue newIssue(elementsFromLine[0], false, elementsFromLine[2], elementsFromLine[3]);
            inputIssues.push_back(newIssue);
        }

        getline(fileInput, line);
    }
    fileInput.close();
    return inputIssues;
}

void Repository::writeIssues(std::vector<Issue> outputIssues)
{
    std::string line;

    std::ofstream fileOutput(this->issuesFileName);

    for (Issue issueToPrint : outputIssues)
    {
        fileOutput << issueToPrint.getDescription() << ",";

        if (issueToPrint.getStatus() == true)
        {
            fileOutput << "open,";
        } else {
            fileOutput << "closed,";
        }

        fileOutput << issueToPrint.getReporter() << ",";

        if (issueToPrint.getStatus() == true)
        {
            fileOutput << issueToPrint.getSolver() << "\n";
        }
        else {
            fileOutput << "\n";
        }
    }
    fileOutput.close();
}

std::vector<Issue> Repository::getIssues()
{
    return this->issues;
}

std::vector<User> Repository::getUsers()
{
    return this->users;
}

