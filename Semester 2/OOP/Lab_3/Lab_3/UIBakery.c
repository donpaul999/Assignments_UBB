#include "UIBakery.h"
#include <stdio.h>
#include <stdlib.h>

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
		if (strcmp(consoleInput, "exit") == 0) {
			destroyUI(bakeryUI);
			break;
		}
		else if (strcmp(consoleInput, "add") == 0)
			uiAddMaterial(bakeryUI);
		else if (strcmp(consoleInput, "delete") == 0)
			uiDeleteMaterial(bakeryUI);
		else if (strcmp(consoleInput, "update") == 0)
			uiUpdateMaterial(bakeryUI);
		else if (strcmp(consoleInput, "list") == 0)
			uiListMaterials(bakeryUI);
		else
			printf("Invalid command!\n");
	}

}

void uiAddMaterial(BakeryUI* bakeryUI)
{
	int id;
	char supplier[50], name[50];
	int quantity;
	//scanf("%d, %s, %s, %d", &id, supplier, name, &quantity);
	scanf("%d, ", &id);
	scanf("%s, ", supplier);
	supplier[strlen(supplier) - 1] = '\0';	//remove the ","
	scanf("%s, ", name);
	name[strlen(name) - 1] = '\0';	//remove the ","
	scanf("%d", &quantity);
	int answerOfTheFunction = addMaterialService(bakeryUI->bakeryService, id, supplier, name, quantity);
	if (answerOfTheFunction == -1)
		printf("NO!\n");

}

void uiUpdateMaterial(BakeryUI* bakeryUI)
{
	int id;
	char supplier[50], name[50];
	int quantity;
	scanf("%d, ", &id);
	scanf("%s, ", supplier);
	supplier[strlen(supplier) - 1] = '\0'; //remove the ","
	scanf("%s, ", name);
	name[strlen(name) - 1] = '\0';	//remove the ","
	scanf("%d", &quantity);
	int answerofTheFunction = updateMaterialService(bakeryUI->bakeryService, id, supplier, name, quantity);
	if (answerofTheFunction == -1)
		printf("NO!\n");
}

void uiDeleteMaterial(BakeryUI* bakeryUI)
{
	int id;
	scanf("%d", &id);
	int answerofTheFunction = removeMaterialService(bakeryUI->bakeryService, id);
	if (answerofTheFunction == -1)
		printf("NO!\n");
}

void uiListMaterials(BakeryUI* bakeryUI)
{	
	int length = 0;
	char name[50] = "", aux[50];
	gets(name);
	if (strlen(name) != 0){
		strcpy(aux, name + 1);
		strcpy(name, aux);
	}
	Material* listOfMaterials = returnMaterialsWithNameService(bakeryUI->bakeryService, &length, name);
	for (int i = 0; i < length; ++i)
		printf("ID: %d Supplier: %s Name: %s Quantity: %d\n", listOfMaterials[i].id, listOfMaterials[i].supplier, listOfMaterials[i].name, listOfMaterials[i].quantity);
}
