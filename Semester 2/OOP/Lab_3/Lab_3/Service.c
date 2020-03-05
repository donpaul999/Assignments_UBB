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

void removeMaterialService(Service* service, int id)
{
	return removeMaterial(service->repo, id);
}

void findMaterialService(Service* service, int id)
{
	return findMaterial(service->repo, id);
}

void addMaterialService(Service* service, int id, char name[], char supplier, double quantity)
{
	Material m = createMaterial(id, supplier, name, quantity);
	return addMaterial(service->repo, m);
}

void updateMaterialService(Service* service, int id, char name[], char supplier, double quantity)
{
	Material m = createMaterial(id, supplier, name, quantity);
	return updateMaterial(service->repo, m);
}
