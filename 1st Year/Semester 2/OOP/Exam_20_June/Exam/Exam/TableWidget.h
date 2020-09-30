#pragma once

#include <QWidget>
#include "ui_TableWidget.h"

class TableWidget : public QWidget
{
	Q_OBJECT

public:
	explicit TableWidget(QWidget *parent = Q_NULLPTR)        : QWidget(parent)
    {
        ui.setupUi(this);
    };
	~TableWidget() override = default;

private:
	Ui::TableWidget ui;
};
