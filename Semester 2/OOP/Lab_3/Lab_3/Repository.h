#pragma once
#include "Material.h"
#define MAX_DIM 1001 

typedef struct {
	Material list_of_materials[MAX_DIM];
	int materials_counter;
}Repository;

Repository* createRepository();
void destroyRepository(Repository* repo);
int updateMaterial(Repository* repo, Material m);
int addMaterial(Repository* repo, Material m);
int findMaterial(Repository* repo, int id);
int removeMaterial(Repository* repo, int id);
Material* returnMaterialsWithName(Repository* repo, int*length, char name[]);


