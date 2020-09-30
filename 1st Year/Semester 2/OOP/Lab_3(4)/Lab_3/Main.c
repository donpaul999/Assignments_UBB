#include <stdio.h>
#include "UIBakery.h"
#include "Tests.h"
#define _CRTDBG_MAP_ALLOC
#include <stdlib.h>
#include <crtdbg.h>

int main(){
	Repository* repository = createRepository(10);
	Service* service = createService(repository);
	BakeryUI* ui = createUI(service);
	runTests();
	startAppUI(ui);
	_CrtDumpMemoryLeaks();
	return 0;

}