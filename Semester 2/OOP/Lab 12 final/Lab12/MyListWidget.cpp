#include "MyListWidget.h"

MyListWidget::MyListWidget(MyListTableModel* model, QWidget* parent)
	: QWidget(parent), model{ model }
{
	ui.setupUi(this);
	ui.myListTableView->setModel(this->model);
}

MyListWidget::~MyListWidget()
{
}
