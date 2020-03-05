#pragma once
#include "Repository.h"

typedef struct {
	Repository* repo;
}Service;


Service* createService(Repository* repo);
void destroyService(Service* service);
void removeMaterialService(Service* service, int id);
void findMaterialService(Service* service, int id);
void updateMaterialService(Service* service, int id, char name[], char supplier, double quantity);
void addMaterialService(Service* service, int id, char name[], char supplier, double quantity);