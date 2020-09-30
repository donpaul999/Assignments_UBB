/********************************************************************************
** Form generated from reading UI file 'TableWidget.ui'
**
** Created by: Qt User Interface Compiler version 5.14.2
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_TABLEWIDGET_H
#define UI_TABLEWIDGET_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_TableWidget
{
public:

    void setupUi(QWidget *TableWidget)
    {
        if (TableWidget->objectName().isEmpty())
            TableWidget->setObjectName(QString::fromUtf8("TableWidget"));
        TableWidget->resize(400, 300);

        retranslateUi(TableWidget);

        QMetaObject::connectSlotsByName(TableWidget);
    } // setupUi

    void retranslateUi(QWidget *TableWidget)
    {
        TableWidget->setWindowTitle(QCoreApplication::translate("TableWidget", "TableWidget", nullptr));
    } // retranslateUi

};

namespace Ui {
    class TableWidget: public Ui_TableWidget {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_TABLEWIDGET_H
