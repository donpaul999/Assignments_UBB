#pragma once
#include "Service.h"
#include <stdlib.h>

Service* createService(Repository* repo)
{
	Service* service = (Service*)malloc(sizeof(Service));
	service->repo = repo;
	return service;
}

void destroyService(Service* service)
{
	destroyRepository(service->repo);
	free(service);
}

int removeMaterialService(Service* service, int id, int currentInUndo)
{
	return removeMaterial(service->repo, id, currentInUndo);
}

int findMaterialService(Service* service, int id)
{
	return findMaterial(service->repo, id);
}

int addMaterialService(Service* service, int id, char supplier[], char name[], int quantity, int currentInUndo){
	Material* materialUsed = createMaterial(id, supplier, name, quantity);
	return addMaterial(service->repo, materialUsed, currentInUndo);
}

DynamicallyVector* returnMaterialsWithNameService(Service* service, int* length,char name[])
{
	return returnMaterialsWithName(service->repo, length, name);
}

DynamicallyVector* returnMaterialsWithQuantityService(Service* service, int* length, int quantity)
{
	return returnMaterialsWithQuantity(service->repo, length, quantity);
}

int undoService(Service* service)
{
	return undo(service->repo);
}

int redoService(Service* service)
{
	return redo(service->repo);
}

int updateMaterialService(Service* service, int id, char supplier[], char name[], int quantity, int currentInUndo)
{
	Material* materialUsed = createMaterial(id, supplier, name, quantity);
	return updateMaterial(service->repo, materialUsed, currentInUndo);
}
