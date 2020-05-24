#include "Examen.h"

Examen::Examen(Service& serviceGiven, QWidget *parent)
	: QMainWindow(parent), service(serviceGiven)
{
	ui.setupUi(this);
}

void Examen::connectSignalsAndSlots()
{
}

int Examen::getSelectedIndex() const
{
	return 0;
}

void Examen::populateList()
{
}
