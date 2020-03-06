#pragma once
#include "math.h"
typedef struct
{
	int id;
	char supplier[100];
	char name[100];
	int quantity;
}Material;

Material createMaterial(int id, char supplier[], char name[], double quantity);
double getQuantity(Material p);
char* getName(Material* p);
int getID(Material p);
char* getSupplier(Material* p);
void testMaterial();