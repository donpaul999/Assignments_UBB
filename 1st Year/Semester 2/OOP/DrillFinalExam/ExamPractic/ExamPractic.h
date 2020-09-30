#pragma once
#include "Controller.h"
#include "qmessagebox.h"

#include <QtWidgets/QMainWindow>
#include "ui_ExamPractic.h"

class ExamPractic : public QMainWindow, public Observer
{
    Q_OBJECT

public:
    ExamPractic(User& user, Controller& controller, QWidget *parent = Q_NULLPTR);

private:
    Ui::ExamPracticClass ui;
    Controller& controller;
	User& user;

	void connectSignalsAndSlots();
	int getSelectedIndex() const;
	void populateList();

	void addEntity();
	void removeEntity();
	void solveEntity();

	void update() override;
};
