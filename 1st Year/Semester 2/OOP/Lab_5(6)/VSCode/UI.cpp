#include "UI.h"
#include <iostream>
#include "ValidationException.h"
#include "RepositoryException.h"
UI::UI(AdminService& adminService, UserService& userService): adminService { adminService } , userService{ userService }{}

void UI::runApp()
{
    std::string aplicationMode = "";
    while (1) {
        std::cout << "Choose mode: ";
        std::cin >> aplicationMode;
        if (aplicationMode == "mode") {
            std::cin >> aplicationMode;
            if (aplicationMode == "A")
                runAdmin();
            else
                if (aplicationMode == "B")
                 runUser();
                else
                    std::cout << "Invalid option!\n";
            break;
        }
        else if (aplicationMode == "fileLocation")
            uiAdminChangeFile();
        else if (aplicationMode == "mylistLocation")
            uiUserChangeFile();
        else if (aplicationMode == "exit")
            break;
         else
            std::cout << "Invalid option!\n";
    }
}

void UI::runAdmin()
{
    std::cout << "Mode changed!\n";
    std::string consoleInput = "";
    int changeMode = 0;
    while (1 && !changeMode) {
        std::cout << "Input: ";
        std::cin >> consoleInput;
        if (consoleInput == "add")
            uiAdminAdd();
        else if (consoleInput == "update")
            uiAdminUpdate();
        else if (consoleInput == "delete")
            uiAdminDelete();
        else if (consoleInput == "list")
            uiAdminList();
        else if (consoleInput == "fileLocation")
            uiAdminChangeFile();
        else if (consoleInput == "mode") {
            std::string modeToChange;
            std::cin >> modeToChange;
            if (modeToChange == "B") {
                changeMode = 1;
                runUser();
            }
        }
        else if (consoleInput == "exit")
            break;
        else
            std::cout << "Invalid input! \n";
        }

}

void UI::runUser()
{
    std::cout << "Mode changed!\n";
    try{
        userService.listMoviesByGenre("");
    }
    catch(std::exception& exception){
        std::cout<<exception.what()<<'\n';
    }
    Movie currentMovie;
    std::string consoleInput = "";
    int changeMode = 0;
    int inAListLoop = 0;
    while (1 && !changeMode && !inAListLoop) {
        std::cout << "Input: ";
        std::cin >> consoleInput;
        if (consoleInput == "list") {
            uiUserList();
            inAListLoop = 1;
        }
        else if (consoleInput == "mode A") {
            std::string modeToChange;
            std::cin >> modeToChange;
            if (modeToChange == "A") {
                changeMode = 1;
                runUser();
            }
        }
        else if (consoleInput == "next") {
            currentMovie = userService.getCurrentMovie();
            std::cout << currentMovie;
            uiUserNext();
        }
        else if (consoleInput == "mylist")
            uiUserWatchList();
        else if (consoleInput == "save")
            uiUserSave();
        else if (consoleInput == "exit")
            break;
        else
            std::cout << "Invalid input! \n";
    }
}

void UI::uiAdminAdd()
{
    std::string title, genre, trailer, consoleInput;
    int yearOfRelease, numberOfLikes;
    /*
    getline(std::cin, consoleInput);
    std::vector<std::string>tokenizedInput = adminService.explode(consoleInput, ',');
    title = tokenizedInput[0].substr(1);
    genre = tokenizedInput[1].substr(1);
    yearOfRelease = stoi(tokenizedInput[2].substr(1));
    numberOfLikes = stoi(tokenizedInput[3].substr(1));
    trailer = tokenizedInput[4].substr(1);
    */
    std::cin >> consoleInput;
    title = consoleInput.substr(0, consoleInput.size() - 1);
    std::cin >> consoleInput;
    genre = consoleInput.substr(0, consoleInput.size() - 1);
    std::cin >> consoleInput;
    yearOfRelease = stoi(consoleInput.substr(0, consoleInput.size() - 1));
    std::cin >> consoleInput;
    numberOfLikes = stoi(consoleInput.substr(0, consoleInput.size() - 1));
    std::cin >> consoleInput;
    trailer = consoleInput;
    try{
        adminService.adminAddMovie(title, genre, yearOfRelease, numberOfLikes, trailer);
    }
    catch(std::exception& exception)
    {
        std::cout << exception.what()<<'\n';
    }

}

void UI::uiAdminDelete()
{
    std::string title, consoleInput;
    getline(std::cin, consoleInput);
    title = consoleInput.substr(1);
    try {
        adminService.adminDeleteMovie(title);
    }
    catch(std::exception& exception)
    {
        std::cout << exception.what()<<'\n';
    }
}

void UI::uiAdminUpdate()
{
    std::string title, genre, trailer, consoleInput;
    int yearOfRelease, numberOfLikes;/*
    getline(std::cin, consoleInput);
    std::vector<std::string>tokenizedInput = adminService.explode(consoleInput, ',');
    title = tokenizedInput[0].substr(1);
    genre = tokenizedInput[1].substr(1);
    yearOfRelease = stoi(tokenizedInput[2].substr(1));
    numberOfLikes = stoi(tokenizedInput[3].substr(1));
    trailer = tokenizedInput[4].substr(1);
    */
    std::cin >> consoleInput;
    title = consoleInput.substr(0, consoleInput.size() - 1);
    std::cin >> consoleInput;
    genre = consoleInput.substr(0, consoleInput.size() - 1);
    std::cin >> consoleInput;
    yearOfRelease = stoi(consoleInput.substr(0, consoleInput.size() - 1));
    std::cin >> consoleInput;
    numberOfLikes = stoi(consoleInput.substr(0, consoleInput.size() - 1));
    std::cin >> consoleInput;
    trailer = consoleInput;
    try{
        adminService.adminUpdateMovie(title, genre, yearOfRelease, numberOfLikes, trailer);
    }
    catch(std::exception& exception)
    {
        std::cout << exception.what()<<'\n';
    }
}

void UI::uiAdminChangeFile()
{
    std::string consoleInput, extensionFile;
    std::getline(std::cin, consoleInput, '.');
    std::cin >> extensionFile;
    consoleInput += '.' + extensionFile;
    try{
        adminService.changeRepositoryFileName(consoleInput.substr(1));
    }
    catch(std::exception& exception)
    {
        std::cout << exception.what()<<'\n';
    }
}

void UI::uiAdminList()
{
    std::vector<Movie>listOfMovies = adminService.adminGetMovieList();
    for (int i = 0; i < listOfMovies.size(); ++i)
        std::cout << listOfMovies[i] << '\n';
}

void UI::uiUserAdd() {
    try{
        userService.addMovieToWatchList();
    }
    catch(std::exception& exception)
    {
        std::cout << exception.what()<<'\n';
    }

}

void UI::uiUserWatchList()
{
    std::string command;
    if(userService.isRepositoryCSV())
        command = "open -a 'Numbers.app' " + userService.getFileName();
    if(userService.isRepositoryHTML())
        command = "open -a 'Google Chrome.app' " + userService.getFileName();
    std::cout << userService.getFileName() << '\n';
    system(command.c_str());
}

void UI::uiUserNext() {
    userService.goToNextMovieByGenre();
}

void UI::uiUserSave()
{
    std::string title;
    std::cin >> title;
    try{
        userService.addMovieToWatchListByTitle(title);
    }
    catch(std::exception& exception)
    {
        std::cout << exception.what()<<'\n';
    }
}

void UI::uiUserList()
{

        std::string consoleInput,genre = "";
        Movie currentMovie;
        int numberOfLikes = -1;
        getline(std::cin, consoleInput);
        if (consoleInput.find(',') != std::string::npos) {
            std::vector<std::string>tokenizedInput = adminService.explode(consoleInput, ',');
            genre = tokenizedInput[0].substr(1);
            int numberOfLikes = stoi(tokenizedInput[1].substr(1));
        }
        else
            if (consoleInput.size() > 0)
                genre = consoleInput.substr(1);
        int isFunctionSuccessful = 1;
        try{
            userService.listMoviesByGenre(genre);
        }
        catch(std::exception& exception)
        {
            isFunctionSuccessful = -1;
            std::cout << exception.what()<<'\n';
        }
        while (isFunctionSuccessful != -1) {
            try {
                currentMovie = userService.getCurrentMovie();
            }
            catch (std::exception &exception) {
                std::cout << exception.what() << '\n';
            }
            std::cout << currentMovie << '\n';
            std::cout << "Input: ";
            std::cin >> consoleInput;
            if (consoleInput == "add") {
                uiUserAdd();
                uiUserNext();
            }
            else if (consoleInput == "next")
                uiUserNext();
            else if (consoleInput == "exit")
                break;
            else
                std::cout << "Invalid option\n";
        }

}

void UI::uiUserChangeFile()
{
    std::string consoleInput, extensionFile;
    std::getline(std::cin, consoleInput, '.');
    std::cin >> extensionFile;
    consoleInput += '.' + extensionFile;
    try{
        userService.changeRepositoryFileName(consoleInput.substr(1), extensionFile);
    }
    catch(std::exception& exception){
        std::cout << exception.what()<<'\n';
    }

}