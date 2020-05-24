#pragma once

#include <QtWidgets/QMainWindow>
#include "ui_Examen.h"
#include "Service.h"

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

	void populateList();
};
