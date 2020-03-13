#include "Material.h"
#include <string.h>
#include <assert.h>
Material* createMaterial(int id, char supplier[], char name[], int quantity)
{
	Material* object = (Material*)malloc(sizeof(Material));
	object->id = id;
	object->name = (char*)malloc(sizeof(char) * (strlen(name) + 1));
	object->supplier = (char*)malloc(sizeof(char) * (strlen(supplier) + 1));
	object->name = NULL;
	object->supplier = NULL;
	strcpy(object->supplier, supplier);
	strcpy(object->name, name);
	object->quantity = quantity;
	return object;
}

void destroyMaterial(Material* materialUsed) {
	free(materialUsed->name);
	free(materialUsed->supplier);
	free(materialUsed);
}


int getQuantity(Material* materialUsed){
	return materialUsed->quantity;
}

char* getName(Material* materialUsed){
	return materialUsed->name;
}

int getID(Material* materialUsed)
{
	return materialUsed->id;
}

char* getSupplier(Material* materialUsed)
{
	return materialUsed->supplier;
}

