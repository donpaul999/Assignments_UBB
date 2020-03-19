#include "UI.h"
#include <iostream>
UI::UI(AdminService& adminService, UserService& userService): adminService { adminService } , userService{ userService }{}

void UI::runApp()
{
    std::string aplicationMode = "";
    while (1) {
        std::cout << "Input app mode: ";
        std::cin >> aplicationMode;
        if (aplicationMode == "admin")
            runAdmin(); 
        else if (aplicationMode == "user")
            runUser();
        else if (aplicationMode == "exit")
            break;
         else
            std::cout << "Invalid option!\n";
    }
}

void UI::runAdmin()
{
    std::string consoleInput = "";
    while (1) {
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
        else if (consoleInput == "exit")
            break;
        else
            std::cout << "Invalid input! \n";
        }

}

void UI::runUser()
{
    std::string consoleInput = "";
    while (1) {
        std::cout << "Input: ";
        std::cin >> consoleInput;
        if (consoleInput == "list")
            uiAdminList();
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
    getline(std::cin, consoleInput);
    std::vector<std::string>tokenizedInput = adminService.explode(consoleInput, ',');
    title = tokenizedInput[0].substr(1);
    genre = tokenizedInput[1].substr(1);
    yearOfRelease = stoi(tokenizedInput[2].substr(1));
    numberOfLikes = stoi(tokenizedInput[3].substr(1));
    trailer = tokenizedInput[4].substr(1);
    int isFunctionSuccessful = adminService.adminAddMovie(title, genre, yearOfRelease, numberOfLikes, trailer);
    //int isFunctionSuccessful = adminService.adminAddMovie(movieRead);
    if (isFunctionSuccessful == -1) {
        std::cout << "Movie already in the list!\n";
    }
}

void UI::uiAdminDelete()
{
    std::string title, consoleInput;
    getline(std::cin, consoleInput);
    title = consoleInput.substr(1);
    int isFunctionSuccessful = adminService.adminDeleteMovie(title);
    if (isFunctionSuccessful == -1) {
        std::cout << "Movie is not in the list!\n";
    }
}

void UI::uiAdminUpdate()
{
    std::string title, genre, trailer, consoleInput;
    int yearOfRelease, numberOfLikes;
    getline(std::cin, consoleInput);
    std::vector<std::string>tokenizedInput = adminService.explode(consoleInput, ',');
    title = tokenizedInput[0].substr(1);
    genre = tokenizedInput[1].substr(1);
    yearOfRelease = stoi(tokenizedInput[2].substr(1));
    numberOfLikes = stoi(tokenizedInput[3].substr(1));
    trailer = tokenizedInput[4].substr(1);
    int isFunctionSuccessful = adminService.adminUpdateMovie(title, genre, yearOfRelease, numberOfLikes, trailer);
    //int isFunctionSuccessful = adminService.adminAddMovie(movieRead);
    if (isFunctionSuccessful == -1) {
        std::cout << "Movie is not in the list!\n";
    }
}

void UI::uiAdminList()
{
    std::vector<Movie>listOfMovies = adminService.adminGetMovieList();
    for (int i = 0; i < listOfMovies.size(); ++i)
        std::cout << listOfMovies[i] << '\n';
}

void UI::uiUserList()
{
    std::vector<Movie>listOfMovies = userService.userGetMovieList();
    for (int i = 0; i < listOfMovies.size(); ++i)
        std::cout << listOfMovies[i] << '\n';
}
