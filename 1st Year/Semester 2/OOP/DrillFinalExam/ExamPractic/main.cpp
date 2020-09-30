#include "ExamPractic.h"
#include "Controller.h"
#include <QtWidgets/QApplication>
#include <memory>
#include <cassert>
using namespace std;
int main(int argc, char *argv[])
{
    QApplication a(argc, argv);

    unique_ptr<Repository> repository = make_unique<Repository>("users.txt", "issues.txt");
    Controller controller(repository.get());

    std::vector<User> users = controller.getAllUsers();
    std::vector<ExamPractic*> w;



    for (int i = 0; i < users.size(); i++)
    {
        ExamPractic* t = new ExamPractic(users[i], controller);
        controller.addObserver(t);
        w.push_back(t);
    }
    for (int i = 0; i < w.size(); i++) {
        w[i]->setWindowTitle(QString::fromStdString(users[i].getName() + ": " + users[i].getType()));
        w[i]->show();
    }


    return a.exec();
}
