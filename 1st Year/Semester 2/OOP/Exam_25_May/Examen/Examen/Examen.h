#pragma once

#include <QtWidgets/QMainWindow>
#include "ui_Examen.h"
#include "Service.h"
#include "qmessagebox.h"
#include "Car.h"
using namespace std;
typedef Car TElem;

class Examen : public QMainWindow
{
	Q_OBJECT

public:
	Examen(Service& serviceGiven, QWidget *parent = Q_NULLPTR);

private:
	Ui::ExamenClass ui;
	Service& service;
	void connectSignalsAndSlots();
	int getSelectedIndex() const;
	void addObject();
	void deleteObject();
	void populateList();
	void sort();
};
