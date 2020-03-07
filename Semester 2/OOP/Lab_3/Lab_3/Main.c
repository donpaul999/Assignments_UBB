#include <stdio.h>
#include "UIBakery.h"
//#include <crtdbg.h>

int main(){
	Repository* repo = createRepository();
	Service* service = createService(repo);
	BakeryUI* ui = createUI(service);
	startAppUI(ui);
	return 0;
	//_CrtDumpMemoryLeaks();
}