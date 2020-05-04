#include <QtWidgets/QApplication>
#include <QDebug>
#include <QLabel>
#include <QLineEdit>
using namespace std;

int main(int arg, char* argv[]) {
    QApplication a(arg, argv);
    QLineEdit lineEdit ;
    QLabel label ( "&Hello :)" ) ;
    label.setBuddy(&lineEdit);
    return a.exec();
}