#include "UI.h"
#include <QtWidgets/QApplication>

using namespace std;
int main(int argc, char *argv[])
{

    FileRepository* repository = new FileRepository{"inputFile.txt"};
	AdminService adminservice{repository} ;
	UserService userservice{repository};
	UI ui{ adminservice, userservice };
	ui.runApp();
	return 0;
}