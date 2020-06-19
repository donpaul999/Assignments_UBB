#include "Examen.h"
#include <QtWidgets/QApplication>
#include <memory>
#include <cassert>
using namespace std;


int main(int argc, char *argv[])
{
	QApplication a(argc, argv);
	unique_ptr<Repository> repo = make_unique<Repository>("input.txt");
	Service service{ repo.get() };
    std::vector<TElem> users = service.getAllObjects();
    std::vector<Examen*> w;



    for (int i = 0; i < users.size(); i++)
    {
        Examen* t = new Examen();
        service.addObserver(t);
        w.push_back(t);
    }
    for (int i = 0; i < w.size(); i++) {
        w[i]->setWindowTitle(QString::fromStdString(users[i]));
        w[i]->show();
    }
	return a.exec();
}
