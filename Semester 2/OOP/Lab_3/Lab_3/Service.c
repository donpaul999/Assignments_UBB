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

int removeMaterialService(Service* service, int id)
{
	return removeMaterial(service->repo, id);
}

int findMaterialService(Service* service, int id)
{
	return findMaterial(service->repo, id);
}

int addMaterialService(Service* service, int id, char supplier[], char name[], double quantity)
{
	Material m = createMaterial(id, supplier, name, quantity);
	return addMaterial(service->repo, m);
}

Material* returnMaterialsWithNameService(Service* service, int* length,char name[])
{
	return returnMaterialsWithName(service->repo, length, name);
}

int updateMaterialService(Service* service, int id, char supplier[], char name[], double quantity)
{
	Material m = createMaterial(id, supplier, name, quantity);
	return updateMaterial(service->repo, m);
}

