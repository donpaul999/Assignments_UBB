/********************************************************************************
** Form generated from reading UI file 'MyListWidget.ui'
**
** Created by: Qt User Interface Compiler version 5.14.2
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_MYLISTWIDGET_H
#define UI_MYLISTWIDGET_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QTableView>
#include <QtWidgets/QVBoxLayout>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_MyListWidget
{
public:
    QVBoxLayout *verticalLayout;
    QTableView *myListTableView;

    void setupUi(QWidget *MyListWidget)
    {
        if (MyListWidget->objectName().isEmpty())
            MyListWidget->setObjectName(QString::fromUtf8("MyListWidget"));
        MyListWidget->resize(580, 398);
        verticalLayout = new QVBoxLayout(MyListWidget);
        verticalLayout->setSpacing(6);
        verticalLayout->setContentsMargins(11, 11, 11, 11);
        verticalLayout->setObjectName(QString::fromUtf8("verticalLayout"));
        myListTableView = new QTableView(MyListWidget);
        myListTableView->setObjectName(QString::fromUtf8("myListTableView"));

        verticalLayout->addWidget(myListTableView);


        retranslateUi(MyListWidget);

        QMetaObject::connectSlotsByName(MyListWidget);
    } // setupUi

    void retranslateUi(QWidget *MyListWidget)
    {
        MyListWidget->setWindowTitle(QCoreApplication::translate("MyListWidget", "MyListWidget", nullptr));
    } // retranslateUi

};

namespace Ui {
    class MyListWidget: public Ui_MyListWidget {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_MYLISTWIDGET_H
