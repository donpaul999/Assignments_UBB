#pragma once
#include "math.h"
typedef struct
{
	int id;
	char supplier[100];
	char name[100];
	int quantity;
}Material;

Material* createMaterial(int id, char supplier[], char name[], int quantity);
void destroyMaterial(Material* materialUsed);
int getQuantity(Material* materialUsed);
char* getName(Material* materialUsed);
int getID(Material* materialUsed);
char* getSupplier(Material* materialUsed);
