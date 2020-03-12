#pragma once
#include "Repository.h"

typedef struct {
	Repository* repo;
}Service;


Service* createService(Repository* repo);
void destroyService(Service* service);
int removeMaterialService(Service* service, int id, int currentInUndo);
int findMaterialService(Service* service, int id);
int updateMaterialService(Service* service, int id, char supplier[], char name[], int quantity, int currentInUndo);
int addMaterialService(Service* service, int id, char supplier[], char name[], int quantity, int currentInUndo);
DynamicallyVector* returnMaterialsWithNameService(Service* service, int* length, char name[]);
DynamicallyVector* returnMaterialsWithQuantityService(Service* service, int* length, int quantity);
int undoService(Service* service);
int redoService(Service* service);

