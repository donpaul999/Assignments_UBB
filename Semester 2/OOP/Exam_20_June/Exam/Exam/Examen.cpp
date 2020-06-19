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
        if (selectedIndex < 0 || this->service.getAllObjects().size() == 0)
            return;
        TElem objectUsed = this->service.getAllObjects()[selectedIndex];
        this->ui.manLineEdit->setText(QString::fromStdString(objectUsed));
        });
    QObject::connect(this->ui.addButton, &QPushButton::clicked, this, &Examen::addFunction);

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
    if (this->service.getAllObjects().size() == 0)
        return;
    vector<TElem> allObjects = this->service.getAllObjects();
    for (TElem& object : allObjects) {
        this->ui.objectListWidget->addItem(QString::fromStdString(object));
    }
}

void Examen::addFunction()
{
}

void Examen::update() {
    populateList();
}