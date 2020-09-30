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
    QPushButton *saveToMyListButton;
    QWidget *layoutWidget;
    QVBoxLayout *verticalLayout_3;
    QListWidget *movieListWidget;
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
    QVBoxLayout *verticalLayout;
    QHBoxLayout *horizontalLayout;
    QPushButton *addButton;
    QPushButton *deleteButton;
    QPushButton *updateButton;
    QHBoxLayout *horizontalLayout_2;
    QPushButton *undoButton;
    QPushButton *redoButton;
    QFormLayout *formLayout_2;
    QLabel *titleLabel_2;
    QLineEdit *fileLocationLineEdit;
    QPushButton *updateFileLocationButton;
    QWidget *widget;
    QFormLayout *formLayout_4;
    QHBoxLayout *horizontalLayout_3;
    QLabel *titleLabel_4;
    QLineEdit *genreToSortByLineEdit;
    QPushButton *sortByGenreButton;
    QPushButton *nextButton;
    QFormLayout *formLayout_3;
    QLabel *titleLabel_3;
    QLineEdit *fileLocationLineEdit_2;
    QVBoxLayout *verticalLayout_2;
    QPushButton *updateMyListLocationButton;
    QPushButton *openWatchListButton;
    QListWidget *myListWidget;
    QMenuBar *menubar;
    QStatusBar *statusbar;

    void setupUi(QMainWindow *Lab12Class)
    {
        if (Lab12Class->objectName().isEmpty())
            Lab12Class->setObjectName(QString::fromUtf8("Lab12Class"));
        Lab12Class->resize(885, 713);
        centralwidget = new QWidget(Lab12Class);
        centralwidget->setObjectName(QString::fromUtf8("centralwidget"));
        saveToMyListButton = new QPushButton(centralwidget);
        saveToMyListButton->setObjectName(QString::fromUtf8("saveToMyListButton"));
        saveToMyListButton->setGeometry(QRect(440, 110, 31, 31));
        layoutWidget = new QWidget(centralwidget);
        layoutWidget->setObjectName(QString::fromUtf8("layoutWidget"));
        layoutWidget->setGeometry(QRect(10, 10, 414, 537));
        verticalLayout_3 = new QVBoxLayout(layoutWidget);
        verticalLayout_3->setObjectName(QString::fromUtf8("verticalLayout_3"));
        verticalLayout_3->setContentsMargins(0, 0, 0, 0);
        movieListWidget = new QListWidget(layoutWidget);
        movieListWidget->setObjectName(QString::fromUtf8("movieListWidget"));

        verticalLayout_3->addWidget(movieListWidget);

        formLayout = new QFormLayout();
        formLayout->setObjectName(QString::fromUtf8("formLayout"));
        titleLabel = new QLabel(layoutWidget);
        titleLabel->setObjectName(QString::fromUtf8("titleLabel"));
        QFont font;
        font.setPointSize(18);
        titleLabel->setFont(font);

        formLayout->setWidget(0, QFormLayout::LabelRole, titleLabel);

        titleLineEdit = new QLineEdit(layoutWidget);
        titleLineEdit->setObjectName(QString::fromUtf8("titleLineEdit"));
        titleLineEdit->setFont(font);

        formLayout->setWidget(0, QFormLayout::FieldRole, titleLineEdit);

        genreLabel = new QLabel(layoutWidget);
        genreLabel->setObjectName(QString::fromUtf8("genreLabel"));
        genreLabel->setFont(font);

        formLayout->setWidget(1, QFormLayout::LabelRole, genreLabel);

        genreLineEdit = new QLineEdit(layoutWidget);
        genreLineEdit->setObjectName(QString::fromUtf8("genreLineEdit"));
        genreLineEdit->setFont(font);

        formLayout->setWidget(1, QFormLayout::FieldRole, genreLineEdit);

        yearOfReleaseLabel = new QLabel(layoutWidget);
        yearOfReleaseLabel->setObjectName(QString::fromUtf8("yearOfReleaseLabel"));
        yearOfReleaseLabel->setFont(font);

        formLayout->setWidget(2, QFormLayout::LabelRole, yearOfReleaseLabel);

        yearOfReleaseLineEdit = new QLineEdit(layoutWidget);
        yearOfReleaseLineEdit->setObjectName(QString::fromUtf8("yearOfReleaseLineEdit"));
        yearOfReleaseLineEdit->setFont(font);

        formLayout->setWidget(2, QFormLayout::FieldRole, yearOfReleaseLineEdit);

        numberOfLikesLabel = new QLabel(layoutWidget);
        numberOfLikesLabel->setObjectName(QString::fromUtf8("numberOfLikesLabel"));
        numberOfLikesLabel->setFont(font);

        formLayout->setWidget(3, QFormLayout::LabelRole, numberOfLikesLabel);

        numberOfLikesLineEdit = new QLineEdit(layoutWidget);
        numberOfLikesLineEdit->setObjectName(QString::fromUtf8("numberOfLikesLineEdit"));
        numberOfLikesLineEdit->setFont(font);

        formLayout->setWidget(3, QFormLayout::FieldRole, numberOfLikesLineEdit);

        trailerLabel = new QLabel(layoutWidget);
        trailerLabel->setObjectName(QString::fromUtf8("trailerLabel"));
        trailerLabel->setFont(font);

        formLayout->setWidget(4, QFormLayout::LabelRole, trailerLabel);

        trailerLineEdit = new QLineEdit(layoutWidget);
        trailerLineEdit->setObjectName(QString::fromUtf8("trailerLineEdit"));
        trailerLineEdit->setFont(font);

        formLayout->setWidget(4, QFormLayout::FieldRole, trailerLineEdit);


        verticalLayout_3->addLayout(formLayout);

        verticalLayout = new QVBoxLayout();
        verticalLayout->setObjectName(QString::fromUtf8("verticalLayout"));
        horizontalLayout = new QHBoxLayout();
        horizontalLayout->setObjectName(QString::fromUtf8("horizontalLayout"));
        addButton = new QPushButton(layoutWidget);
        addButton->setObjectName(QString::fromUtf8("addButton"));

        horizontalLayout->addWidget(addButton);

        deleteButton = new QPushButton(layoutWidget);
        deleteButton->setObjectName(QString::fromUtf8("deleteButton"));

        horizontalLayout->addWidget(deleteButton);

        updateButton = new QPushButton(layoutWidget);
        updateButton->setObjectName(QString::fromUtf8("updateButton"));

        horizontalLayout->addWidget(updateButton);


        verticalLayout->addLayout(horizontalLayout);

        horizontalLayout_2 = new QHBoxLayout();
        horizontalLayout_2->setObjectName(QString::fromUtf8("horizontalLayout_2"));
        undoButton = new QPushButton(layoutWidget);
        undoButton->setObjectName(QString::fromUtf8("undoButton"));

        horizontalLayout_2->addWidget(undoButton);

        redoButton = new QPushButton(layoutWidget);
        redoButton->setObjectName(QString::fromUtf8("redoButton"));

        horizontalLayout_2->addWidget(redoButton);


        verticalLayout->addLayout(horizontalLayout_2);


        verticalLayout_3->addLayout(verticalLayout);

        formLayout_2 = new QFormLayout();
        formLayout_2->setObjectName(QString::fromUtf8("formLayout_2"));
        titleLabel_2 = new QLabel(layoutWidget);
        titleLabel_2->setObjectName(QString::fromUtf8("titleLabel_2"));
        titleLabel_2->setFont(font);

        formLayout_2->setWidget(0, QFormLayout::LabelRole, titleLabel_2);

        fileLocationLineEdit = new QLineEdit(layoutWidget);
        fileLocationLineEdit->setObjectName(QString::fromUtf8("fileLocationLineEdit"));
        fileLocationLineEdit->setFont(font);

        formLayout_2->setWidget(0, QFormLayout::FieldRole, fileLocationLineEdit);


        verticalLayout_3->addLayout(formLayout_2);

        updateFileLocationButton = new QPushButton(layoutWidget);
        updateFileLocationButton->setObjectName(QString::fromUtf8("updateFileLocationButton"));

        verticalLayout_3->addWidget(updateFileLocationButton);

        widget = new QWidget(centralwidget);
        widget->setObjectName(QString::fromUtf8("widget"));
        widget->setGeometry(QRect(490, 10, 414, 369));
        formLayout_4 = new QFormLayout(widget);
        formLayout_4->setObjectName(QString::fromUtf8("formLayout_4"));
        formLayout_4->setContentsMargins(0, 0, 0, 0);
        horizontalLayout_3 = new QHBoxLayout();
        horizontalLayout_3->setObjectName(QString::fromUtf8("horizontalLayout_3"));
        titleLabel_4 = new QLabel(widget);
        titleLabel_4->setObjectName(QString::fromUtf8("titleLabel_4"));
        titleLabel_4->setFont(font);

        horizontalLayout_3->addWidget(titleLabel_4);

        genreToSortByLineEdit = new QLineEdit(widget);
        genreToSortByLineEdit->setObjectName(QString::fromUtf8("genreToSortByLineEdit"));
        genreToSortByLineEdit->setFont(font);

        horizontalLayout_3->addWidget(genreToSortByLineEdit);


        formLayout_4->setLayout(1, QFormLayout::LabelRole, horizontalLayout_3);

        sortByGenreButton = new QPushButton(widget);
        sortByGenreButton->setObjectName(QString::fromUtf8("sortByGenreButton"));

        nextButton = new QPushButton(widget);
        sortByGenreButton->setObjectName(QString::fromUtf8("nextButton"));

        formLayout_4->setWidget(2, QFormLayout::LabelRole, sortByGenreButton);
        formLayout_4->setWidget(3, QFormLayout::LabelRole, nextButton);

        formLayout_3 = new QFormLayout();
        formLayout_3->setObjectName(QString::fromUtf8("formLayout_3"));
        titleLabel_3 = new QLabel(widget);
        titleLabel_3->setObjectName(QString::fromUtf8("titleLabel_3"));
        titleLabel_3->setFont(font);

        formLayout_3->setWidget(0, QFormLayout::LabelRole, titleLabel_3);

        fileLocationLineEdit_2 = new QLineEdit(widget);
        fileLocationLineEdit_2->setObjectName(QString::fromUtf8("fileLocationLineEdit_2"));
        fileLocationLineEdit_2->setFont(font);

        formLayout_3->setWidget(0, QFormLayout::FieldRole, fileLocationLineEdit_2);


        formLayout_4->setLayout(4, QFormLayout::LabelRole, formLayout_3);

        verticalLayout_2 = new QVBoxLayout();
        verticalLayout_2->setObjectName(QString::fromUtf8("verticalLayout_2"));
        updateMyListLocationButton = new QPushButton(widget);
        updateMyListLocationButton->setObjectName(QString::fromUtf8("updateMyListLocationButton"));

        verticalLayout_2->addWidget(updateMyListLocationButton);

        openWatchListButton = new QPushButton(widget);
        openWatchListButton->setObjectName(QString::fromUtf8("openWatchListButton"));

        verticalLayout_2->addWidget(openWatchListButton);


        formLayout_4->setLayout(5, QFormLayout::LabelRole, verticalLayout_2);

        myListWidget = new QListWidget(widget);
        myListWidget->setObjectName(QString::fromUtf8("myListWidget"));

        formLayout_4->setWidget(0, QFormLayout::SpanningRole, myListWidget);

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
        saveToMyListButton->setText(QCoreApplication::translate("Lab12Class", ">", nullptr));
        titleLabel->setText(QCoreApplication::translate("Lab12Class", "Title", nullptr));
        genreLabel->setText(QCoreApplication::translate("Lab12Class", "Genre", nullptr));
        yearOfReleaseLabel->setText(QCoreApplication::translate("Lab12Class", "Year of Release", nullptr));
        numberOfLikesLabel->setText(QCoreApplication::translate("Lab12Class", "Number of likes", nullptr));
        trailerLabel->setText(QCoreApplication::translate("Lab12Class", "Trailer", nullptr));
        addButton->setText(QCoreApplication::translate("Lab12Class", "Add", nullptr));
        deleteButton->setText(QCoreApplication::translate("Lab12Class", "Delete", nullptr));
        updateButton->setText(QCoreApplication::translate("Lab12Class", "Update", nullptr));
        undoButton->setText(QCoreApplication::translate("Lab12Class", "Undo", nullptr));
        redoButton->setText(QCoreApplication::translate("Lab12Class", "Redo", nullptr));
        titleLabel_2->setText(QCoreApplication::translate("Lab12Class", "FileLocation", nullptr));
        fileLocationLineEdit->setText(QString());
        updateFileLocationButton->setText(QCoreApplication::translate("Lab12Class", "Update File", nullptr));
        titleLabel_4->setText(QCoreApplication::translate("Lab12Class", "Sort by Genre", nullptr));
        sortByGenreButton->setText(QCoreApplication::translate("Lab12Class", "Sort", nullptr));
        nextButton->setText(QCoreApplication::translate("Lab12Class", "Next", nullptr));
        titleLabel_3->setText(QCoreApplication::translate("Lab12Class", "MyList Location", nullptr));
        fileLocationLineEdit_2->setText(QString());
        updateMyListLocationButton->setText(QCoreApplication::translate("Lab12Class", "Update File", nullptr));
        openWatchListButton->setText(QCoreApplication::translate("Lab12Class", "Open Watch List", nullptr));
    } // retranslateUi

};

namespace Ui {
    class Lab12Class: public Ui_Lab12Class {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_LAB12_H
