#include "Repository.h"
#pragma once

Repository* createRepository()
{
	Repository* repo = (Repository*)malloc(sizeof(Repository));
	if (repo == NULL)
		return NULL;
	repo->materials_counter = 0;
	return repo;
}

void destroyRepository(Repository* repo)
{
	free(repo);
}

int updateMaterial(Repository* repo, Material m)
{
	int position_where_material_was_find = findMaterial(repo, m.id);
	if(position_where_material_was_find == -1)
		return -1;
	strcpy(repo->list_of_materials[position_where_material_was_find].name, m.name);
	strcpy(repo->list_of_materials[position_where_material_was_find].supplier, m.supplier);
	repo->list_of_materials[position_where_material_was_find].quantity = m.quantity;
	return 1;
}

int addMaterial(Repository* repo, Material m)
{
	if (findMaterial(repo, m.id) != -1)
		return -1;
	repo->list_of_materials[repo->materials_counter++] = m;
	return 1;
}

int findMaterial(Repository* repo, int id)
{
	for (int i = 0; i < repo->materials_counter; ++i)
		if (id == getID(repo->list_of_materials[i]))
			return i;
	return -1;
}

int removeMaterial(Repository* repo, int id)
{
	int position_where_material_was_found = findMaterial(repo, id);
	if (position_where_material_was_found == -1)
		return -1;
	for (int i = position_where_material_was_found; i < repo->materials_counter - 1; ++i)
		repo->list_of_materials[i] = repo->list_of_materials[i + 1];
	repo->materials_counter--;
	return 1;
}

Material* returnMaterialsWithName(Repository* repo, int* length, char name[]) {
	int i = 0;
	int lengthOfTheList = 0;
	Material* list_of_materials = (Material*)malloc(sizeof(Material) * 200);
	if (strlen(name) == 0) {
		*length = repo->materials_counter;
		return repo->list_of_materials;
	}
	for (i = 0; i < repo->materials_counter; ++i)
		if (strcmp(repo->list_of_materials[i].name, name) == 0) {
			list_of_materials[lengthOfTheList].id = repo->list_of_materials[i].id;
			strcpy(list_of_materials[lengthOfTheList].name, repo->list_of_materials[i].name);
			strcpy(list_of_materials[lengthOfTheList].supplier, repo->list_of_materials[i].supplier);
			list_of_materials[lengthOfTheList++].quantity = repo->list_of_materials[i].quantity;
		}
	*length = lengthOfTheList;
	return list_of_materials;
}