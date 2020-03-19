#include <iostream>
#include "UI.h"
using namespace std;
int main()
{
	Repository* repository = new Repository();
	AdminService adminservice{ *repository };
	UserService userservice{ *repository };
	UI ui{ adminservice, userservice };
	ui.runApp();
	return 0;
}