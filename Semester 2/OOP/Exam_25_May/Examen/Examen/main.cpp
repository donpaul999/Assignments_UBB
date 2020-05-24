#include "Examen.h"
#include <QtWidgets/QApplication>
#include <memory>
using namespace std;
int main(int argc, char *argv[])
{
	QApplication a(argc, argv);
	unique_ptr<Repository> repo = make_unique<Repository>();
	Service service{ repo.get() };
	Examen w{ service };
	w.show();
	return a.exec();
}
