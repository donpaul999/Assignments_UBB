#include "Examen.h"
#include <QModelIndexList>

Examen::Examen(Service& serviceGiven, QWidget *parent)
	: QMainWindow(parent), service(serviceGiven)
{
	ui.setupUi(this);
}

void Examen::connectSignalsAndSlots()
{
    QObject::connect(this->ui.objectListWidget, &QListWidget::itemSelectionChanged, [this](){
        int selectedIndex = this->getSelectedIndex();
        if (selectedIndex < 0 || this->service.getAllObjects().size() == 0)
            return;
        TElem objectUsed = this->service.getAllObjects()[selectedIndex];
        //Exemplu: this->ui.titleLineEdit->setText(QString::fromStdString(movieUsed.getTitle()));
        });
}

int Examen::getSelectedIndex() const
{
    
    QModelIndexList selectedIndexes = this->ui.objectListWidget->selectionModel()->selectedIndexes();
    if (selectedIndexes.size() == 0) {
        /*
        this->ui.titleLineEdit->clear();
        this->ui.genreLineEdit->clear();
        this->ui.yearOfReleaseLineEdit->clear();
        this->ui.numberOfLikesLineEdit->clear();
        this->ui.trailerLineEdit->clear();
        return -1;

        */
    }
	int selectedIndex = selectedIndexes.at(0).row();
	return selectedIndex;
}

void Examen::populateList()
{
    this->ui.objectListWidget->clear();
    if (this->service.getAllObjects().size() == 0)
        return;
    vector<TElem> allObjects = this->service.getAllObjects();
    for (TElem& object : allObjects) {
        //this->ui.objectListWidget->addItem(QString::fromStdString(object.getOutputForm()));
        //getOutputForm e un repr din python, il implementati in domeniu
    }
}
