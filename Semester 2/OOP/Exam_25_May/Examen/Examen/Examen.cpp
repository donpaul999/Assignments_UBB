#include "Examen.h"
#include <QModelIndexList>

Examen::Examen(Service& serviceGiven, QWidget *parent)
	: QMainWindow(parent), service(serviceGiven)
{
	ui.setupUi(this);
    populateList();
    connectSignalsAndSlots();
}

void Examen::connectSignalsAndSlots()
{
    QObject::connect(this->ui.objectListWidget, &QListWidget::itemSelectionChanged, [this](){
        int selectedIndex = this->getSelectedIndex();
        if (selectedIndex < 0 || this->service.getAllObjectsByManufacturer().size() == 0)
            return;
        TElem objectUsed = this->service.getAllObjectsByManufacturer()[selectedIndex];
        this->ui.manLineEdit->setText(QString::fromStdString(objectUsed.getManufacturer()));
        });
    QObject::connect(this->ui.sortButton, &QPushButton::clicked, this, &Examen::sort);

}

int Examen::getSelectedIndex() const
{
    
    QModelIndexList selectedIndexes = this->ui.objectListWidget->selectionModel()->selectedIndexes();
    if (selectedIndexes.size() == 0) {
        this->ui.manLineEdit->clear();
        return -1;
    }
	int selectedIndex = selectedIndexes.at(0).row();
	return selectedIndex;
}

void Examen::addObject()
{
    //Preluam datele din line edit
    try {
        this->service.addObject();
    }
    catch (std::exception & e) {
        QMessageBox::critical(this, "Error", e.what());
        return;
    }
}

void Examen::deleteObject()
{
    //Preluam datele din line edit
    try {
        this->service.deleteObject();
    }
    catch (std::exception & e) {
        QMessageBox::critical(this, "Error", e.what());
        return;
    }
}

void Examen::populateList()
{
    this->ui.objectListWidget->clear();
    if (this->service.getAllObjectsByManufacturer().size() == 0)
        return;
    vector<TElem> allObjects = this->service.getAllObjectsByManufacturer();
    for (TElem& object : allObjects) {
        this->ui.objectListWidget->addItem(QString::fromStdString(object.getOutputForm()));
    }
}

void Examen::sort()
{
    std::string fact = this->ui.manLineEdit->text().toStdString();
    string size = std::to_string(this->service.sortByManufacturer(fact));
    this->ui.nbOfCarsLineEdit->setText(QString::fromStdString(size));
   
}
