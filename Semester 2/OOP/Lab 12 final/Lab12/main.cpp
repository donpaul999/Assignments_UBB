#include <QtWidgets/QApplication>
#include <QLabel>
#include <memory>
#include "UserService.h"
#include "AdminService.h"
#include "Lab12.h"
#include <iostream>
#include <fstream>
#include <cstring>
#include "MyListWidget.h"

using namespace std;
int main(int argc, char* argv[])
{   
    ifstream fin("configFile.txt");
    string memoryOrFile;
    bool inMemory = 0;
    fin >> memoryOrFile;
    if (memoryOrFile == "memory")
        inMemory = 1;
    fin.close();

    QApplication a(argc, argv);

    unique_ptr<FileRepository> repository = make_unique<FileRepository>("input.txt");
    //cout << inMemory;
    repository->changeFileName("input.txt");
    repository->setMemoryOrFile(inMemory);
    AdminService adminservice{ repository.get() };
    UserService userservice{ repository.get() };
    if (inMemory == 0) {
        userservice.changeRepositoryFileName("test.html", "html");
        unique_ptr<MyListTableModel> model = make_unique<MyListTableModel>(userservice.getRepo());
    }
    unique_ptr<MyListTableModel> model = make_unique<MyListTableModel>(repository.get());
    MyListWidget myListWidget{ model.get() };
    myListWidget.show();
    Lab12 gui{adminservice, userservice};
    gui.show();

    return a.exec();
}