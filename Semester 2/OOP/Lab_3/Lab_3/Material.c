#include "Material.h"
#include <string.h>
#include <assert.h>
Material* createMaterial(int id, char supplier[], char name[], int quantity)
{
	Material* object = (Material*)malloc(sizeof(Material));
	object->id = id;
	strcpy(object->supplier, supplier);
	strcpy(object->name, name);
	object->quantity = quantity;
	return object;
}

void destroyMaterial(Material* materialUsed) {
	free(materialUsed->id);
	free(materialUsed->supplier);
	free(materialUsed->name);
	free(materialUsed->quantity);
	free(materialUsed);

}


int getQuantity(Material* materialUsed)
{
	return materialUsed->quantity;
}

char* getName(Material* materialUsed)
{
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

