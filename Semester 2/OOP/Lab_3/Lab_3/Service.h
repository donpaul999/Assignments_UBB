#pragma once
#include "Repository.h"

typedef struct {
	Repository* repo;
}Service;


Service* createService(Repository* repo);
void destroyService(Service* service);
int removeMaterialService(Service* service, int id);
int findMaterialService(Service* service, int id);
int updateMaterialService(Service* service, int id, char supplier[], char name[], double quantity);
int addMaterialService(Service* service, int id, char supplier[], char name[], double quantity);
Material* returnMaterialsWithNameService(Service* service, int* length, char name[]);
