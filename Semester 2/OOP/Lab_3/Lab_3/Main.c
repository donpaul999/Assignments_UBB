#include <stdio.h>
#include "UIBakery.h"
//#include <crtdbg.h>

int main(){
	printf( "Hello world!\n");
	Repository* repo = createRepository();
	Service* service = createService(repo);
	printf("%d\n", addMaterialService(service, 123, "abc", "def", 456));
	printf("%d\n", addMaterialService(service, 124, "abc", "def", 456));
	printf("%d\n", addMaterialService(service, 125, "abc", "def", 456));
	printf("%d\n", addMaterialService(service, 126, "abc", "def", 456));
	BakeryUI* ui = createUI(service);
	startAppUI(ui);
	return 0;
	//_CrtDumpMemoryLeaks();
}