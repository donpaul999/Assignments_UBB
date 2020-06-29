#include "Examen.h"
#include <QtWidgets/QApplication>
#include <memory>
#include <cassert>
using namespace std;

void testAdd() {
    unique_ptr<Repository> repo = make_unique<Repository>("test.txt");
    Service serv{ repo.get() };
    serv.addStar("test", "123", 3, 5, 123);
    assert(serv.getAllStars().size() == 1);
    try {
        serv.addStar("", "123", 3, 5, 123);
        assert(false);
    }
    catch (exception & e) {
        assert(true);
    }
}

int main(int argc, char *argv[])
{
    testAdd();
	QApplication a(argc, argv);
	unique_ptr<Repository> repo = make_unique<Repository>("input.txt", "input2.txt");
	Service service{ repo.get() };
    std::vector<TElem> users = service.getAllObjects();
    std::vector<Examen*> w;

    for (int i = 0; i < users.size(); ++i) {
        TableModel* model = new TableModel{ service };
        Examen* t = new Examen {users[i],service, model };
        service.addObserver(t);
        w.push_back(t);
    }

    for (int i = 0; i < w.size(); i++) {
        w[i]->setWindowTitle(QString::fromStdString(users[i].getName()));
        w[i]->show();
    }
	return a.exec();
}
