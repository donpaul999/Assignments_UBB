#include <stdio.h>
#include "UIBakery.h"
#define _CRTDBG_MAP_ALLOC
#include <stdlib.h>
#include <crtdbg.h>
//#include <crtdbg.h>

int main(){
	_CrtDumpMemoryLeaks();
	Repository* repository = createRepository();
	Service* service = createService(repository);
	BakeryUI* ui = createUI(service);
	startAppUI(ui);
	return 0;
	//_CrtDumpMemoryLeaks();
}