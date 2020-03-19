#include <iostream>
#include "Tests.h"
using namespace std;
int main()
{
	Tests testsLists;
	testsLists.runAllTests();
	cout << "Test Passed";
	Repository* repository{};
	AdminService adminservice{ *repository };
	UserService userservice{ *repository };
	UI ui{ adminservice, userservice };
	ui.runApp();
	return 0;
}