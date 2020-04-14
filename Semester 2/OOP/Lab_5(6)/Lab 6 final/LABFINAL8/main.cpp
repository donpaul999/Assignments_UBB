#include "Tests.h"
#include "UI.h"
using namespace std;
int main()
{
    Tests testsLists;
    testsLists.runAllTests();
    /*
	Repository repository{"inputFile.txt"};
	AdminService adminservice{ repository };
	UserService userservice{ repository };
	UI ui{ adminservice, userservice };
	ui.runApp();
    */
    return 0;
}