/********************************************************************************
** Form generated from reading UI file 'Lab12.ui'
**
** Created by: Qt User Interface Compiler version 5.14.2
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_LAB12_H
#define UI_LAB12_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QFormLayout>
#include <QtWidgets/QHBoxLayout>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QListWidget>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QVBoxLayout>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_Lab12Class
{
public:
    QWidget *centralwidget;
    QListWidget *movieListWidget;
    QPushButton *updateFileLocationButton;
    QListWidget *myListWidget;
    QPushButton *saveToMyListButton;
    QWidget *layoutWidget;
    QFormLayout *formLayout_3;
    QLabel *titleLabel_3;
    QLineEdit *fileLocationLineEdit_2;
    QWidget *layoutWidget1;
    QFormLayout *formLayout;
    QLabel *titleLabel;
    QLineEdit *titleLineEdit;
    QLabel *genreLabel;
    QLineEdit *genreLineEdit;
    QLabel *yearOfReleaseLabel;
    QLineEdit *yearOfReleaseLineEdit;
    QLabel *numberOfLikesLabel;
    QLineEdit *numberOfLikesLineEdit;
    QLabel *trailerLabel;
    QLineEdit *trailerLineEdit;
    QWidget *layoutWidget2;
    QVBoxLayout *verticalLayout;
    QHBoxLayout *horizontalLayout;
    QPushButton *addButton;
    QPushButton *deleteButton;
    QHBoxLayout *horizontalLayout_2;
    QPushButton *undoButton;
    QPushButton *redoButton;
    QWidget *layoutWidget3;
    QFormLayout *formLayout_2;
    QLabel *titleLabel_2;
    QLineEdit *fileLocationLineEdit;
    QWidget *layoutWidget4;
    QVBoxLayout *verticalLayout_3;
    QVBoxLayout *verticalLayout_2;
    QPushButton *updateMyListLocationButton;
    QPushButton *openWatchListButton;
    QPushButton *openMyListButton;
    QMenuBar *menubar;
    QStatusBar *statusbar;

    void setupUi(QMainWindow *Lab12Class)
    {
        if (Lab12Class->objectName().isEmpty())
            Lab12Class->setObjectName(QString::fromUtf8("Lab12Class"));
        Lab12Class->resize(885, 713);
        centralwidget = new QWidget(Lab12Class);
        centralwidget->setObjectName(QString::fromUtf8("centralwidget"));
        movieListWidget = new QListWidget(centralwidget);
        movieListWidget->setObjectName(QString::fromUtf8("movieListWidget"));
        movieListWidget->setGeometry(QRect(20, 10, 291, 281));
        updateFileLocationButton = new QPushButton(centralwidget);
        updateFileLocationButton->setObjectName(QString::fromUtf8("updateFileLocationButton"));
        updateFileLocationButton->setGeometry(QRect(170, 600, 141, 31));
        myListWidget = new QListWidget(centralwidget);
        myListWidget->setObjectName(QString::fromUtf8("myListWidget"));
        myListWidget->setGeometry(QRect(530, 10, 291, 281));
        saveToMyListButton = new QPushButton(centralwidget);
        saveToMyListButton->setObjectName(QString::fromUtf8("saveToMyListButton"));
        saveToMyListButton->setGeometry(QRect(360, 130, 112, 32));
        layoutWidget = new QWidget(centralwidget);
        layoutWidget->setObjectName(QString::fromUtf8("layoutWidget"));
        layoutWidget->setGeometry(QRect(525, 330, 291, 37));
        formLayout_3 = new QFormLayout(layoutWidget);
        formLayout_3->setObjectName(QString::fromUtf8("formLayout_3"));
        formLayout_3->setContentsMargins(0, 0, 0, 0);
        titleLabel_3 = new QLabel(layoutWidget);
        titleLabel_3->setObjectName(QString::fromUtf8("titleLabel_3"));
        QFont font;
        font.setPointSize(18);
        titleLabel_3->setFont(font);

        formLayout_3->setWidget(0, QFormLayout::LabelRole, titleLabel_3);

        fileLocationLineEdit_2 = new QLineEdit(layoutWidget);
        fileLocationLineEdit_2->setObjectName(QString::fromUtf8("fileLocationLineEdit_2"));
        fileLocationLineEdit_2->setFont(font);

        formLayout_3->setWidget(0, QFormLayout::FieldRole, fileLocationLineEdit_2);

        layoutWidget1 = new QWidget(centralwidget);
        layoutWidget1->setObjectName(QString::fromUtf8("layoutWidget1"));
        layoutWidget1->setGeometry(QRect(20, 300, 291, 201));
        formLayout = new QFormLayout(layoutWidget1);
        formLayout->setObjectName(QString::fromUtf8("formLayout"));
        formLayout->setContentsMargins(0, 0, 0, 0);
        titleLabel = new QLabel(layoutWidget1);
        titleLabel->setObjectName(QString::fromUtf8("titleLabel"));
        titleLabel->setFont(font);

        formLayout->setWidget(0, QFormLayout::LabelRole, titleLabel);

        titleLineEdit = new QLineEdit(layoutWidget1);
        titleLineEdit->setObjectName(QString::fromUtf8("titleLineEdit"));
        titleLineEdit->setFont(font);

        formLayout->setWidget(0, QFormLayout::FieldRole, titleLineEdit);

        genreLabel = new QLabel(layoutWidget1);
        genreLabel->setObjectName(QString::fromUtf8("genreLabel"));
        genreLabel->setFont(font);

        formLayout->setWidget(1, QFormLayout::LabelRole, genreLabel);

        genreLineEdit = new QLineEdit(layoutWidget1);
        genreLineEdit->setObjectName(QString::fromUtf8("genreLineEdit"));
        genreLineEdit->setFont(font);

        formLayout->setWidget(1, QFormLayout::FieldRole, genreLineEdit);

        yearOfReleaseLabel = new QLabel(layoutWidget1);
        yearOfReleaseLabel->setObjectName(QString::fromUtf8("yearOfReleaseLabel"));
        yearOfReleaseLabel->setFont(font);

        formLayout->setWidget(2, QFormLayout::LabelRole, yearOfReleaseLabel);

        yearOfReleaseLineEdit = new QLineEdit(layoutWidget1);
        yearOfReleaseLineEdit->setObjectName(QString::fromUtf8("yearOfReleaseLineEdit"));
        yearOfReleaseLineEdit->setFont(font);

        formLayout->setWidget(2, QFormLayout::FieldRole, yearOfReleaseLineEdit);

        numberOfLikesLabel = new QLabel(layoutWidget1);
        numberOfLikesLabel->setObjectName(QString::fromUtf8("numberOfLikesLabel"));
        numberOfLikesLabel->setFont(font);

        formLayout->setWidget(3, QFormLayout::LabelRole, numberOfLikesLabel);

        numberOfLikesLineEdit = new QLineEdit(layoutWidget1);
        numberOfLikesLineEdit->setObjectName(QString::fromUtf8("numberOfLikesLineEdit"));
        numberOfLikesLineEdit->setFont(font);

        formLayout->setWidget(3, QFormLayout::FieldRole, numberOfLikesLineEdit);

        trailerLabel = new QLabel(layoutWidget1);
        trailerLabel->setObjectName(QString::fromUtf8("trailerLabel"));
        trailerLabel->setFont(font);

        formLayout->setWidget(4, QFormLayout::LabelRole, trailerLabel);

        trailerLineEdit = new QLineEdit(layoutWidget1);
        trailerLineEdit->setObjectName(QString::fromUtf8("trailerLineEdit"));
        trailerLineEdit->setFont(font);

        formLayout->setWidget(4, QFormLayout::FieldRole, trailerLineEdit);

        layoutWidget2 = new QWidget(centralwidget);
        layoutWidget2->setObjectName(QString::fromUtf8("layoutWidget2"));
        layoutWidget2->setGeometry(QRect(20, 470, 291, 91));
        verticalLayout = new QVBoxLayout(layoutWidget2);
        verticalLayout->setObjectName(QString::fromUtf8("verticalLayout"));
        verticalLayout->setContentsMargins(0, 0, 0, 0);
        horizontalLayout = new QHBoxLayout();
        horizontalLayout->setObjectName(QString::fromUtf8("horizontalLayout"));
        addButton = new QPushButton(layoutWidget2);
        addButton->setObjectName(QString::fromUtf8("addButton"));

        horizontalLayout->addWidget(addButton);

        deleteButton = new QPushButton(layoutWidget2);
        deleteButton->setObjectName(QString::fromUtf8("deleteButton"));

        horizontalLayout->addWidget(deleteButton);


        verticalLayout->addLayout(horizontalLayout);

        horizontalLayout_2 = new QHBoxLayout();
        horizontalLayout_2->setObjectName(QString::fromUtf8("horizontalLayout_2"));
        undoButton = new QPushButton(layoutWidget2);
        undoButton->setObjectName(QString::fromUtf8("undoButton"));

        horizontalLayout_2->addWidget(undoButton);

        redoButton = new QPushButton(layoutWidget2);
        redoButton->setObjectName(QString::fromUtf8("redoButton"));

        horizontalLayout_2->addWidget(redoButton);


        verticalLayout->addLayout(horizontalLayout_2);

        layoutWidget3 = new QWidget(centralwidget);
        layoutWidget3->setObjectName(QString::fromUtf8("layoutWidget3"));
        layoutWidget3->setGeometry(QRect(25, 570, 291, 37));
        formLayout_2 = new QFormLayout(layoutWidget3);
        formLayout_2->setObjectName(QString::fromUtf8("formLayout_2"));
        formLayout_2->setContentsMargins(0, 0, 0, 0);
        titleLabel_2 = new QLabel(layoutWidget3);
        titleLabel_2->setObjectName(QString::fromUtf8("titleLabel_2"));
        titleLabel_2->setFont(font);

        formLayout_2->setWidget(0, QFormLayout::LabelRole, titleLabel_2);

        fileLocationLineEdit = new QLineEdit(layoutWidget3);
        fileLocationLineEdit->setObjectName(QString::fromUtf8("fileLocationLineEdit"));
        fileLocationLineEdit->setFont(font);

        formLayout_2->setWidget(0, QFormLayout::FieldRole, fileLocationLineEdit);

        layoutWidget4 = new QWidget(centralwidget);
        layoutWidget4->setObjectName(QString::fromUtf8("layoutWidget4"));
        layoutWidget4->setGeometry(QRect(690, 370, 126, 85));
        verticalLayout_3 = new QVBoxLayout(layoutWidget4);
        verticalLayout_3->setObjectName(QString::fromUtf8("verticalLayout_3"));
        verticalLayout_3->setContentsMargins(0, 0, 0, 0);
        verticalLayout_2 = new QVBoxLayout();
        verticalLayout_2->setObjectName(QString::fromUtf8("verticalLayout_2"));
        updateMyListLocationButton = new QPushButton(layoutWidget4);
        updateMyListLocationButton->setObjectName(QString::fromUtf8("updateMyListLocationButton"));

        verticalLayout_2->addWidget(updateMyListLocationButton);

        openWatchListButton = new QPushButton(layoutWidget4);
        openWatchListButton->setObjectName(QString::fromUtf8("openWatchListButton"));

        verticalLayout_2->addWidget(openWatchListButton);


        verticalLayout_3->addLayout(verticalLayout_2);

        openMyListButton = new QPushButton(layoutWidget4);
        openMyListButton->setObjectName(QString::fromUtf8("openMyListButton"));

        verticalLayout_3->addWidget(openMyListButton);

        Lab12Class->setCentralWidget(centralwidget);
        menubar = new QMenuBar(Lab12Class);
        menubar->setObjectName(QString::fromUtf8("menubar"));
        menubar->setGeometry(QRect(0, 0, 885, 21));
        Lab12Class->setMenuBar(menubar);
        statusbar = new QStatusBar(Lab12Class);
        statusbar->setObjectName(QString::fromUtf8("statusbar"));
        Lab12Class->setStatusBar(statusbar);

        retranslateUi(Lab12Class);

        QMetaObject::connectSlotsByName(Lab12Class);
    } // setupUi

    void retranslateUi(QMainWindow *Lab12Class)
    {
        Lab12Class->setWindowTitle(QCoreApplication::translate("Lab12Class", "MainWindow", nullptr));
        updateFileLocationButton->setText(QCoreApplication::translate("Lab12Class", "Update File", nullptr));
        saveToMyListButton->setText(QCoreApplication::translate("Lab12Class", ">", nullptr));
        titleLabel_3->setText(QCoreApplication::translate("Lab12Class", "MyList Location", nullptr));
        fileLocationLineEdit_2->setText(QString());
        titleLabel->setText(QCoreApplication::translate("Lab12Class", "Title", nullptr));
        genreLabel->setText(QCoreApplication::translate("Lab12Class", "Genre", nullptr));
        yearOfReleaseLabel->setText(QCoreApplication::translate("Lab12Class", "Year of Release", nullptr));
        numberOfLikesLabel->setText(QCoreApplication::translate("Lab12Class", "Number of likes", nullptr));
        trailerLabel->setText(QCoreApplication::translate("Lab12Class", "Trailer", nullptr));
        addButton->setText(QCoreApplication::translate("Lab12Class", "Add", nullptr));
        deleteButton->setText(QCoreApplication::translate("Lab12Class", "Delete", nullptr));
        undoButton->setText(QCoreApplication::translate("Lab12Class", "Undo", nullptr));
        redoButton->setText(QCoreApplication::translate("Lab12Class", "Redo", nullptr));
        titleLabel_2->setText(QCoreApplication::translate("Lab12Class", "FileLocation", nullptr));
        fileLocationLineEdit->setText(QString());
        updateMyListLocationButton->setText(QCoreApplication::translate("Lab12Class", "Update File", nullptr));
        openWatchListButton->setText(QCoreApplication::translate("Lab12Class", "Open Watch List", nullptr));
        openMyListButton->setText(QCoreApplication::translate("Lab12Class", "Open My List In Window", nullptr));
    } // retranslateUi

};

namespace Ui {
    class Lab12Class: public Ui_Lab12Class {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_LAB12_H
