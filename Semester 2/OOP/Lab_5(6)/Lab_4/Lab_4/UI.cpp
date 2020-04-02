#include "UI.h"
#include <iostream>
UI::UI(AdminService& adminService, UserService& userService): adminService { adminService } , userService{ userService }{}

void UI::runApp()
{
    std::string aplicationMode = "";
    while (1) {
        std::cout << "Choose mode: ";
        std::getline(std::cin, aplicationMode);
        if (aplicationMode == "mode A") {
            runAdmin();
            break;
        }
        else if (aplicationMode == "mode B") {
             runUser();
             break;
        }
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
    userService.listMoviesByGenre("");
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
    DynamicVector<Movie>listOfMovies = adminService.adminGetMovieList();
    for (int i = 0; i < listOfMovies.size(); ++i)
        std::cout << listOfMovies[i] << '\n';
}

void UI::uiUserAdd() {
    int isFunctionSuccessful = userService.addMovieToWatchList();
    if (isFunctionSuccesful == -1)
        std::cout << "Movie already in the list!\n";

}

void UI::uiUserWatchList()
{
    DynamicVector<Movie>listOfMovies = userService.userGetWatchList();
    for (int i = 0; i < listOfMovies.size(); ++i)
        std::cout << listOfMovies[i] << '\n';
}

void UI::uiUserNext() {
    userService.goToNextMovieByGenre();
}

void UI::uiUserSave()
{
    std::string title;
    std::cin >> title;
    int isFunctionSuccesful = userService.addMovieToWatchListByTitle(title);
    if (isFunctionSuccesful == -1)
        std::cout << "Invalid movie!\n";
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

        int isFunctionSuccessful = userService.listMoviesByGenre(genre);
        if (isFunctionSuccesful == -1)
            std::cout << "No movies with this genre!\n";

        while (isFunctionSuccesful != -1) {
            try {
                currentMovie = userService.getCurrentMovie();
            }
            catch (std::exception &Exception) {
                std::cout << Exception.what() << '\n';
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
