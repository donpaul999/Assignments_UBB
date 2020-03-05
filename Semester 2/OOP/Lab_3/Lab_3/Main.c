#include <stdio.h>
#include "UIBakery.h"
//#include <crtdbg.h>

int main(){
	printf( "Hello world!\n");
	Repository* repo = createRepository();
	Service* service = createService(repo);
	addMaterialService(service, 123, "abc", "def", 456);
	BakeryUI* ui = createUI(ui);
	startAppUI(ui);
	return 0;
	//_CrtDumpMemoryLeaks();
}