#include <QtWidgets/QApplication>
#include <QLabel>
#include "GUI.h"
#include <memory>

using namespace std;
int main(int argc, char *argv[])
{

    QApplication a(argc, argv);

    unique_ptr<FileRepository> repository = make_unique<FileRepository>("inputFile.txt");
	AdminService adminservice{repository.get()} ;
	UserService userservice{repository.get()};
    GUI gui{adminservice, userservice};
    gui.show();
    return a.exec();
}