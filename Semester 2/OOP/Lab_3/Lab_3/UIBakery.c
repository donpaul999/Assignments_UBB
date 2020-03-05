#include "UIBakery.h"
#include <stdio.h>

BakeryUI* createUI(Service* service)
{
	BakeryUI* appUI = (BakeryUI*)malloc(sizeof(BakeryUI));
	appUI->bakeryService = service;
	return appUI;
}

void destroyUI(BakeryUI* bakeryUI)
{
	destroyService(bakeryUI->bakeryService);
	free(bakeryUI);
}

void startAppUI(BakeryUI* bakeryUI)
{
	char consoleInput[50];
	while (1) {
		printf("Input: ");
		scanf("%s", consoleInput);
		if (strcmp(consoleInput, "exit") == 0)
			break;
		if (strcmp(consoleInput, "add") == 0)
			uiAddMaterial(bakeryUI);
		if (strcmp(consoleInput, "delete") == 0)
			uiDeleteMaterial(bakeryUI);
		if (strcmp(consoleInput, "update") == 0)
			uiUpdateMaterial(bakeryUI);
		if (strcmp(consoleInput, "list") == 0)
			uiListMaterials(bakeryUI);
		else
			printf("Invalid command!\n");
	}

}

void uiAddMaterial(BakeryUI* bakeryUI)
{
	int id;
	char supplier[50], name[50];
	double quantity;
	scanf("%d ,", &id);
	scanf("%s ,", supplier);
	scanf("%s ,", name);
	scanf("%lf ,", &quantity);
	addMaterialService(bakeryUI->bakeryService, id, supplier, name, quantity);

}

void uiUpdateMaterial(BakeryUI* bakeryUI)
{
	int id;
	char supplier[50], name[50];
	double quantity;
	scanf("%d, ", &id);
	scanf("%s, ", supplier);
	scanf("%s, ", name);
	scanf("%lf, ", &quantity);
	updateMaterialService(bakeryUI->bakeryService, id, supplier, name, quantity);

}

void uiDeleteMaterial(BakeryUI* bakeryUI)
{
	int id;
	scanf("%d", &id);
	removeMaterialService(bakeryUI->bakeryService, id);
}

void uiListMaterials(BakeryUI* bakeryUI)
{	
	int length;
	char name[50] = "";
	scanf("%c", name);
	Material* listOfMaterials = returnMaterialsWithNameService(bakeryUI->bakeryService, length, name);
}
