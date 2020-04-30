#include "UI.h"
using namespace std;
int main()
{

    FileRepository* repository = new FileRepository{"inputFile.txt"};
	AdminService adminservice{repository} ;
	UserService userservice{repository};
	UI ui{ adminservice, userservice };
	ui.runApp();
    
    return 0;
}