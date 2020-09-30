#include "Examen.h"
#include <QModelIndexList>

Examen::Examen(Astronomer& a, Service& serviceGiven, TableModel* model, QWidget *parent)
	: QMainWindow(parent), service(serviceGiven), astronomer(a), constellation(false), model(model)
{
	ui.setupUi(this);
    connectSignalsAndSlots();
    ui.tableView->setModel(this->model);
    model->setStarList(service.getAllStars());
}

void Examen::connectSignalsAndSlots()
{

    QObject::connect(this->ui.addPushButton, &QPushButton::clicked, this, &Examen::addObject);
    QObject::connect(this->ui.starsCheck, &QCheckBox::stateChanged, this, &Examen::starsWithConstellation);
    QObject::connect(this->ui.searchLineEdit, &QLineEdit::textChanged, this, &Examen::searchStar);

}


void Examen::addObject()
{
    std::string name = this->ui.nameLineEdit->text().toStdString();
    std::string constell = this->ui.constellationLineEdit->text().toStdString();
    std::string RAs = this->ui.raLineEdit->text().toStdString();
    std::string Dec = this->ui.DecLineEdit->text().toStdString();
    std::string diam = this->ui.diameterLineEdit->text().toStdString();
    int RA, Deci, diami;
    if (RAs != "" && Dec != "" && diam != "") {
        RA = stoi(RAs);
        Deci = stoi(Dec);
        diami = stoi(diam);
    }

    try {
        model->addInList(name, constell, RA, Deci, diami);
    }
    catch (std::exception & e) {
        QMessageBox::critical(this, "Error", e.what());
        return;
    }
}

void Examen::searchStar()
{
    std::string name = this->ui.searchLineEdit->text().toStdString();
    model->searchByName(name);
}



void Examen::addFunction()
{
}

void Examen::update() {
    model->setStarList(service.getAllStars());
    model->layoutChanged();
    constellation = false;
}

void Examen::starsWithConstellation()
{
    if (constellation == false) {
        vector<Star> stars = service.getStarsWithConstellation(astronomer.getConstellation());
        model->setStarList(stars);
        constellation = true;
    }
    else {
        model->setStarList(service.getAllStars());
        constellation = false;
    }
}
