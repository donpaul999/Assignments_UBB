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
		else if (strcmp(consoleInput, "undo") == 0)
			uiUndo(bakeryUI);
		else if (strcmp(consoleInput, "redo") == 0)
			uiRedo(bakeryUI);
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
	int isTheFunctionSuccesful = addMaterialService(bakeryUI->bakeryService, id, supplier, name, quantity, 1);
	if (isTheFunctionSuccesful == -1)
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
	int isTheFunctionSuccesful = updateMaterialService(bakeryUI->bakeryService, id, supplier, name, quantity, 1);
	if (isTheFunctionSuccesful == -1)
		printf("NO!\n");
}

void uiDeleteMaterial(BakeryUI* bakeryUI)
{
	int id;
	scanf("%d", &id);
	int isTheFunctionSuccesful = removeMaterialService(bakeryUI->bakeryService, id, 1);
	if (isTheFunctionSuccesful == -1)
		printf("NO!\n");
}

void uiListMaterials(BakeryUI* bakeryUI)
{	
	int length = 0;
	DynamicallyVector* listOfMaterials;
	Material* materialToPrint;
	char nameOrQuantity[50] = "", transitionString[50];

	gets(nameOrQuantity);
	if (strlen(nameOrQuantity) != 0){
		strcpy(transitionString, nameOrQuantity + 1);
		strcpy(nameOrQuantity, transitionString);
	}
	//Verify if what we got from console is a quantity or a name
	int lengthOfString = strlen(nameOrQuantity);
	int quantity = 0, isQuantity = 1;
	if(lengthOfString == 0)
	{
		isQuantity = 0;
	}
	for (int i = 0; i < lengthOfString && isQuantity == 1; ++i)
		if (nameOrQuantity[i] < '0' || nameOrQuantity[i] > '9')
			isQuantity = 0;
		else
			quantity = quantity * 10 + nameOrQuantity[i] - '0';
	if(isQuantity == 1)
		listOfMaterials = returnMaterialsWithQuantityService(bakeryUI->bakeryService, &length, quantity);
	else
		listOfMaterials = returnMaterialsWithNameService(bakeryUI->bakeryService, &length, nameOrQuantity);
	for (int i = 0; i < length; ++i) {
		materialToPrint = getElementByPosition(listOfMaterials, i);
		printf("ID: %d Supplier: %s Name: %s Quantity: %d\n", materialToPrint->id, materialToPrint->supplier, materialToPrint->name, materialToPrint->quantity);
	}
}

void uiUndo(BakeryUI* bakeryUI)
{
	int isSuccesful = undoService(bakeryUI->bakeryService);
	if (isSuccesful == -1)
		printf("No more undo!\n");
}

void uiRedo(BakeryUI* bakeryUI)
{
	int isSuccesful = redoService(bakeryUI->bakeryService);
	if (isSuccesful == -1)
		printf("No more redo!\n");
}
