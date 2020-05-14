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

	//void connectSignalsAndSlots();

	void populateList();
	//void addMovie();
	//void deleteMovie();
	//void updateMovie();

};
