#include "ExamPractic.h"

ExamPractic::ExamPractic(User& user, Controller& controller, QWidget *parent)
    : QMainWindow(parent), controller(controller), user(user)
{
    ui.setupUi(this);
    connectSignalsAndSlots();
    populateList();
}

void ExamPractic::connectSignalsAndSlots()
{
    QObject::connect(this->ui.issuesListWidget, &QListWidget::itemSelectionChanged, [this]() {
        int selectedIndex = this->getSelectedIndex();
        if (selectedIndex < 0 || this->controller.getAllIssues().size() == 0)
            return;
        Issue selectedIssue = this->controller.getAllIssues()[selectedIndex];
        this->ui.addLineEdit->setText(QString::fromStdString(selectedIssue.getDescription()));
        });
    QObject::connect(this->ui.addButton, &QPushButton::clicked, this, &ExamPractic::addEntity);
    QObject::connect(this->ui.removeButton, &QPushButton::clicked, this, &ExamPractic::removeEntity);
    QObject::connect(this->ui.addButton_2, &QPushButton::clicked, this, &ExamPractic::solveEntity);
}

int ExamPractic::getSelectedIndex() const
{
    QModelIndexList selectedIndexes = this->ui.issuesListWidget->selectionModel()->selectedIndexes();
    if (selectedIndexes.size() == 0) {
        this->ui.addLineEdit->clear();
        return -1;
    }
    int selectedIndex = selectedIndexes.at(0).row();
    return selectedIndex;
}

void ExamPractic::populateList()
{
    this->ui.issuesListWidget->clear();
    if (this->controller.getAllIssues().size() == 0)
        return;
    std::vector<Issue> issues = this->controller.getAllIssues();
    for (Issue& selectedIssue : issues) {

        std::string line = "";

        line += selectedIssue.getDescription();
        line += ",";

        if (selectedIssue.getStatus() == true)
        {
            line += "open,";
        } else {
            line += "closed,";
        }
        
        line += selectedIssue.getReporter();
        line += ",";

        if (selectedIssue.getStatus() == false)
        {
            line += selectedIssue.getSolver();
            line += "\n";
        }
        else {
            line += "\n";
        }
        this->ui.issuesListWidget->addItem(QString::fromStdString(line));
    }
}

void ExamPractic::addEntity()
{
    if (this->user.getType() == "programmer")
    {
        QMessageBox::critical(this, "Error", "Tester required!");
        return;
    }

    std::string description = this->ui.addLineEdit->text().toStdString();
    bool status = true;
    std::string reporter = this->user.getName();

    try {
        this->controller.addIssue(this->user, description, status, reporter);
    }
    catch (std::exception & e)
    {
        QMessageBox::critical(this, "Error", e.what());
        return;
    }
    this->populateList();

    int lastElem = this->controller.getAllIssues().size() - 1;
    this->ui.issuesListWidget->setCurrentRow(lastElem);
}

void ExamPractic::removeEntity()
{
    std::string description = this->ui.addLineEdit->text().toStdString();

    Issue selectedIssue(description);

    try {
        this->controller.removeIssue(description);
    }
    catch (std::exception & e)
    {
        QMessageBox::critical(this, "Error", e.what());
        return;
    }
    this->populateList();

    int lastElem = this->controller.getAllIssues().size() - 1;
    this->ui.issuesListWidget->setCurrentRow(lastElem);
}

void ExamPractic::solveEntity()
{
    if (this->user.getType() == "tester")
    {
        QMessageBox::critical(this, "Error", "Programmer required!");
        return;
    }

    std::string description = this->ui.addLineEdit->text().toStdString();


    try {
        this->controller.resolveIssue(this->user, description);
    }
    catch (std::exception & e)
    {
        QMessageBox::critical(this, "Error", e.what());
        return;
    }
    this->populateList();

    int lastElem = this->controller.getAllIssues().size() - 1;
    this->ui.issuesListWidget->setCurrentRow(lastElem);
}

void ExamPractic::update()
{
    populateList();
}
