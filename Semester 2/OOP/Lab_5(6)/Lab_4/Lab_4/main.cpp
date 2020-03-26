#include <iostream>
#include "Tests.h"
using namespace std;
int main()
{
	Tests testsLists;
	testsLists.runAllTests();
	Repository* repository = new Repository();
	AdminService adminservice{ *repository };
	UserService userservice{ *repository };
	UI ui{ adminservice, userservice };
	ui.runApp();
	return 0;
}