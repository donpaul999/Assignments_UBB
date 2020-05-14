#include <QtWidgets/QApplication>
#include <QLabel>
#include <memory>
#include "UserService.h"
#include "AdminService.h"
using namespace std;
int main(int argc, char *argv[])
{
    ifstream fin("configFile.txt");
    std::string memoryOrFile;
    fin >> memoryOrFile;
    fin.close();

    QApplication a(argc, argv);

    unique_ptr<FileRepository> repository = make_unique<FileRepository>("inputFile.txt","txt", memoryOrFile == "memory");

	AdminService adminservice{repository.get()} ;
	UserService userservice{repository.get()};

    return a.exec();
}