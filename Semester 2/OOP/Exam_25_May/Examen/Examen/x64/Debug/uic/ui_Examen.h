/********************************************************************************
** Form generated from reading UI file 'Examen.ui'
**
** Created by: Qt User Interface Compiler version 5.14.2
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_EXAMEN_H
#define UI_EXAMEN_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QListWidget>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QToolBar>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_ExamenClass
{
public:
    QWidget *centralWidget;
    QListWidget *objectListWidget;
    QMenuBar *menuBar;
    QToolBar *mainToolBar;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *ExamenClass)
    {
        if (ExamenClass->objectName().isEmpty())
            ExamenClass->setObjectName(QString::fromUtf8("ExamenClass"));
        ExamenClass->resize(600, 400);
        centralWidget = new QWidget(ExamenClass);
        centralWidget->setObjectName(QString::fromUtf8("centralWidget"));
        objectListWidget = new QListWidget(centralWidget);
        objectListWidget->setObjectName(QString::fromUtf8("objectListWidget"));
        objectListWidget->setGeometry(QRect(60, 20, 256, 192));
        ExamenClass->setCentralWidget(centralWidget);
        menuBar = new QMenuBar(ExamenClass);
        menuBar->setObjectName(QString::fromUtf8("menuBar"));
        menuBar->setGeometry(QRect(0, 0, 600, 21));
        ExamenClass->setMenuBar(menuBar);
        mainToolBar = new QToolBar(ExamenClass);
        mainToolBar->setObjectName(QString::fromUtf8("mainToolBar"));
        ExamenClass->addToolBar(Qt::TopToolBarArea, mainToolBar);
        statusBar = new QStatusBar(ExamenClass);
        statusBar->setObjectName(QString::fromUtf8("statusBar"));
        ExamenClass->setStatusBar(statusBar);

        retranslateUi(ExamenClass);

        QMetaObject::connectSlotsByName(ExamenClass);
    } // setupUi

    void retranslateUi(QMainWindow *ExamenClass)
    {
        ExamenClass->setWindowTitle(QCoreApplication::translate("ExamenClass", "Examen", nullptr));
    } // retranslateUi

};

namespace Ui {
    class ExamenClass: public Ui_ExamenClass {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_EXAMEN_H
