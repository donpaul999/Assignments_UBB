#include "Lab12.h"
#include <iostream>

Lab12::Lab12(AdminService& adminServiceGiven, UserService& userServiceGiven, QWidget *parent)
	: QMainWindow(parent), adminService{adminServiceGiven}, userService{userServiceGiven}
{
	ui.setupUi(this);
    this->populateList();
}

void Lab12::populateList()
{
    this->ui.movieListWidget->clear();
    cout << "TEST";
    vector<Movie> allMovies = this->adminService.adminGetMovieList();
    //std::cout <<"S"<<allMovies.size()<<'\n';
    for (Movie& movieUsed : allMovies)
        this->ui.movieListWidget->addItem(QString::fromStdString(movieUsed.getOutputForm()));
}
