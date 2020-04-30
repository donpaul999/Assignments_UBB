#include <QtWidgets/QApplication>
#include <QDebug>
#include <QLabel>

using namespace std;

int main(int arg, char* argv[]) {
    QApplication a(arg, argv);
    QLabel label("TEST");
    label.show();
    return a.exec();
}