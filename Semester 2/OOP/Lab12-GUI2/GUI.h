//
// Created by Paul Colta on 09/05/2020.
//

#ifndef LAB11_GUI_GUI_H
#define LAB11_GUI_GUI_H
#include "AdminService.h"
#include "UserService.h"
#include <qwidget.h>
#include <qlistwidget.h>
#include <qlineedit.h>
#include <qpushbutton.h>

class GUI: public QWidget{
private:
    AdminService& adminService;
    UserService& userService;
    QListWidget* movieListWidget;
    QLineEdit* titleLineEdit, *genreLineEdit, *yearLineEdit, *numberOfLikesLineEdit, *trailerLineEdit,*updateFileLineEdit;
    QPushButton* addButton, * deleteButton, *updateButton, *undoButton, *redoButton, *updateFileButton;
public:

    GUI(AdminService& adminService, UserService& userService);
private:
    void initGUI();
    void populateList();
    void connectSignalsAndSlots();
    int getSelectedIndex() const;
    void addMovie();
    void deleteMovie();
    void undo();
    void redo();
    void updateFile();
    void updateMovie();
};


#endif //LAB11_GUI_GUI_H
