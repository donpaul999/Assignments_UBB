#pragma once

#include <QtWidgets/QMainWindow>
#include "ui_Lab12.h"
#include "AdminService.h"
#include "UserService.h"

using namespace std;
class Lab12 : public QMainWindow
{
	Q_OBJECT

public:
	Lab12(AdminService& adminService, UserService& userService, QWidget *parent = Q_NULLPTR);

private:
	AdminService& adminService;
	UserService& userService;
	Ui::Lab12Class ui;

	void connectSignalsAndSlots();
    int getSelectedIndex() const;

	void populateList();
	void populateMyList();
	void addMovie();
	void deleteMovie();
	void updateMovie();
	void undo();
	void redo();
    void sortByGenre();
    void updateFileName();
    void updateMyListLocation();
    void openMyList();
    void addToWatchList();
};
