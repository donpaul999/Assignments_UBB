#include "Examen.h"

Examen::Examen(Service& serviceGiven, QWidget *parent)
	: QMainWindow(parent), service(serviceGiven)
{
	ui.setupUi(this);
}
