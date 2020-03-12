#include <stdio.h>
#include "UIBakery.h"
#define _CRTDBG_MAP_ALLOC
#include <stdlib.h>
#include <crtdbg.h>

int main(){
	Repository* repository = createRepository(10);
	Service* service = createService(repository);
	BakeryUI* ui = createUI(service);
	startAppUI(ui);
	addMaterialService(service, 123, "test", "est", 123, 0);
	//_CrtDumpMemoryLeaks();
	return 0;

}