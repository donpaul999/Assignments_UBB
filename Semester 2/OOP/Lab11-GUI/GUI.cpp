//
// Created by Paul Colta on 09/05/2020.
//

#include "GUI.h"
#include <qlayout.h>
#include <QFormLayout>
#include <qgridlayout.h>
#include "Movie.h"
#include <vector>
#include <qmessagebox.h>
#include <exception>

GUI::GUI(AdminService &adminService, UserService &userService): adminService{adminService}, userService{userService}{initGUI(); populateList(); connectSignalsAndSlots();}


void GUI::initGUI() {
    this->movieListWidget = new QListWidget();

    this->titleLineEdit = new QLineEdit();
    this->genreLineEdit = new QLineEdit();
    this->yearLineEdit = new QLineEdit();
    this->numberOfLikesLineEdit = new QLineEdit();
    this->trailerLineEdit = new QLineEdit();

    this->addButton = new QPushButton("Add");
    this->deleteButton = new QPushButton("Delete");
    this->undoButton = new QPushButton("Undo");
    this->redoButton = new QPushButton("Redo");


    QVBoxLayout* mainLayout = new QVBoxLayout{this};
    mainLayout->addWidget(this->movieListWidget);

    QFormLayout* movieDetailsLayout = new QFormLayout();
    movieDetailsLayout->addRow("Title", this->titleLineEdit);
    movieDetailsLayout->addRow("Genre", this->genreLineEdit);
    movieDetailsLayout->addRow("Year of release", this->yearLineEdit);
    movieDetailsLayout->addRow("Number of likes", this->numberOfLikesLineEdit);
    movieDetailsLayout->addRow("Trailer", this->trailerLineEdit);

    mainLayout->addLayout(movieDetailsLayout);

    QGridLayout* buttonsLayout = new QGridLayout();
    buttonsLayout->addWidget(this->addButton, 0, 0);
    buttonsLayout->addWidget(this->deleteButton, 0, 1);
    buttonsLayout->addWidget(this->undoButton, 0, 2);
    buttonsLayout->addWidget(this->redoButton, 0, 3);

    mainLayout->addLayout(buttonsLayout);

}

void GUI::populateList() {
    this->movieListWidget->clear();
    std::vector<Movie> allMovies = this->adminService.adminGetMovieList();
    for(Movie& movieUsed: allMovies)
        this->movieListWidget->addItem(QString::fromStdString(movieUsed.getOutputForm()));
}

void GUI::connectSignalsAndSlots() {
    QObject::connect(this->movieListWidget, &QListWidget::itemSelectionChanged, [this](){
        int selectedIndex = this->getSelectedIndex();
        if(selectedIndex < 0)
            return;
        Movie movieUsed = this->adminService.adminGetMovieList()[selectedIndex];
        this->titleLineEdit->setText(QString::fromStdString(movieUsed.getTitle()));
        this->genreLineEdit->setText(QString::fromStdString(movieUsed.getGenre()));
        this->yearLineEdit->setText(QString::fromStdString(std::to_string(movieUsed.getYearOfRelease())));
        this->numberOfLikesLineEdit->setText(QString::fromStdString(std::to_string(movieUsed.getNumberOfLikes())));
        this->trailerLineEdit->setText(QString::fromStdString(movieUsed.getTrailer()));
    });

    QObject::connect(this->addButton, &QPushButton::clicked, this, &GUI::addMovie);
    QObject::connect(this->deleteButton, &QPushButton::clicked, this, &GUI::deleteMovie);
    QObject::connect(this->undoButton, &QPushButton::clicked, this, &GUI::undo);
    QObject::connect(this->redoButton, &QPushButton::clicked, this, &GUI::redo);
}

int GUI::getSelectedIndex() const {
    QModelIndexList selectedIndexes = this->movieListWidget->selectionModel()->selectedIndexes();
    if(selectedIndexes.size() == 0){
        this->titleLineEdit->clear();
        this->genreLineEdit->clear();
        this->yearLineEdit->clear();
        this->numberOfLikesLineEdit->clear();
        this->trailerLineEdit->clear();
        return -1;
    }

    int selectedIndex = selectedIndexes.at(0).row();
    return selectedIndex;
}

void GUI::addMovie() {
    int selectedIndex = this->getSelectedIndex();
    if(selectedIndex < 0){
        QMessageBox::critical(this, "Error", "No movie selected!");
        return;
    }
    std::string title = this->titleLineEdit->text().toStdString();
    std::string genre = this->genreLineEdit->text().toStdString();
    std::string stringYearOfRelease = this->yearLineEdit->text().toStdString();
    std::string stringNumberOfLikes = this->numberOfLikesLineEdit->text().toStdString();
    std::string trailer = this->trailerLineEdit->text().toStdString();

    int yearOfRelease = stoi(stringYearOfRelease);
    int numberOfLikes = stoi(stringNumberOfLikes);
    Movie movieUsed{title, genre, yearOfRelease, numberOfLikes, trailer};
    std::vector<Movie>movieList = this->adminService.adminGetMovieList();
    if(std::find(movieList.begin(), movieList.end(), movieUsed) != movieList.end())
        try{
            this->adminService.adminUpdateMovie(title, genre, yearOfRelease, numberOfLikes, trailer);
        }
        catch(std::exception& e){
            QMessageBox::critical(this, "Error", e.what());
            return;
        }
    else
        try {
            this->adminService.adminAddMovie(title, genre, yearOfRelease, numberOfLikes, trailer);
        }
        catch(std::exception& e){
            QMessageBox::critical(this, "Error", e.what());
            return;
        }
    this->populateList();

    int lastElem = this->adminService.adminGetMovieList().size() - 1;
    this->movieListWidget->setCurrentRow(lastElem);
}

void GUI::deleteMovie() {
    int selectedIndex = this->getSelectedIndex();
    if(selectedIndex < 0){
        QMessageBox::critical(this, "Error", "No movie selected!");
        return;
    }
    std::string title = this->titleLineEdit->text().toStdString();
    try{
        this->adminService.adminDeleteMovie(title);
    }
    catch(std::exception& e){
        QMessageBox::critical(this, "Error", e.what());
        return;
    }

    this->populateList();

    int lastElem = this->adminService.adminGetMovieList().size() - 1;
    this->movieListWidget->setCurrentRow(lastElem);

}

void GUI::undo() {
    try{
        this->adminService.undo();
    }
    catch(std::exception& e){
        QMessageBox::critical(this, "Error", e.what());
        return;
    }
    this->populateList();
    int lastElem = this->adminService.adminGetMovieList().size() - 1;
    this->movieListWidget->setCurrentRow(lastElem);
}

void GUI::redo() {
    try{
        this->adminService.redo();
    }
    catch(std::exception& e){
        QMessageBox::critical(this, "Error", e.what());
        return;
    }
    this->populateList();
    int lastElem = this->adminService.adminGetMovieList().size() - 1;
    this->movieListWidget->setCurrentRow(lastElem);

}
