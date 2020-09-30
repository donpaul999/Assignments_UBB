#include "Examen.h"
#include <QtWidgets/QApplication>
#include <memory>
#include <cassert>
using namespace std;

void test_sortByManufacturer(){
    unique_ptr<Repository> repo = make_unique<Repository>("test.txt");
    Service service{ repo.get() };
    assert(service.sortByManufacturer("Matiz") == 0);
    assert(service.sortByManufacturer("Mercedes-Benz") == 2);
}


int main(int argc, char *argv[])
{
    test_sortByManufacturer();
	QApplication a(argc, argv);
	unique_ptr<Repository> repo = make_unique<Repository>("input.txt");
	Service service{ repo.get() };
	Examen w{ service };
	w.show();
	return a.exec();
}
