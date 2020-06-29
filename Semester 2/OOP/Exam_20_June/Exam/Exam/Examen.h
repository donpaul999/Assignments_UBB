#pragma once
#include <string>
#include <QtWidgets/QMainWindow>
#include "ui_Examen.h"
#include "Service.h"
#include "qmessagebox.h"
#include "TableModel.h"
using namespace std;

class Examen : public QMainWindow, public Observer
{
	Q_OBJECT

public:
	Examen(Astronomer& a, Service& serviceGiven, TableModel* model, QWidget *parent = Q_NULLPTR);

private:
	Ui::Examen ui;
	vector<Star> starList;
	TableModel* model;
	Service& service;
	bool constellation;
	Astronomer& astronomer;
	void connectSignalsAndSlots();
	//int getSelectedIndex() const;
	void addObject();
	void searchStar();
	void addFunction();
	void update() override;
	void starsWithConstellation();
};
