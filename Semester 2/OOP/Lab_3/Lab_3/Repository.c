#include "Repository.h"
#include "DynamicallyVector.h"
#include <string.h>
#pragma once

Repository* createRepository(int capacity)
{
	Repository* repo = (Repository*)malloc(sizeof(Repository));
	repo->materialsList = createDynamicallyVector(capacity);
	repo->historyList = createDynamicallyVector(2);
	repo->indexOfHistory = -1;
	return repo;
}

void destroyMaterialsList(DynamicallyVector* materialsList)
{
	for (int i = 0; i < sizeOfVector(materialsList); ++i) {
		destroyMaterial(materialsList->elements[i]);
	}
}

void destroyHistoryList(DynamicallyVector* historyList)
{
	DynamicallyVector* listOfMaterials;
	for (int i = 0; i < sizeOfVector(historyList); ++i) {
		listOfMaterials = (DynamicallyVector*)getElementByPosition(historyList, i);
		destroyMaterialsList(listOfMaterials);
		destroyDynamicallyVector(&listOfMaterials);
	}
}

void destroyRepository(Repository* repo)
{
	destroyMaterialsList(repo->materialsList);
	destroyDynamicallyVector(&(repo->materialsList));
	destroyHistoryList(repo->historyList);
	destroyDynamicallyVector(&(repo->historyList));
	free(repo);
}

void appendRepositoryInHistory(Repository* repository)
{
	DynamicallyVector* copyListOfMaterials = getMaterialsListCopy(repository, repository->materialsList);
	appendDynamicallyVector(repository->historyList, copyListOfMaterials);
	repository->indexOfHistory++;
}

void removeEverythingAfterThisState(Repository* repository)
{
	if (repository->indexOfHistory < sizeOfVector(repository->historyList) - 1) {
		DynamicallyVector* transitionListOfStates;
		while (repository->indexOfHistory < sizeOfVector(repository->historyList) - 1) {
			transitionListOfStates = (DynamicallyVector*)getElementByPosition(repository->historyList, sizeOfVector(repository->historyList) - 1);
			destroyMaterialsList(transitionListOfStates);
			destroyDynamicallyVector(&transitionListOfStates);
			deleteByPosition(repository->historyList, sizeOfVector(repository->historyList) - 1);
		}
	}
}


int updateMaterial(Repository* repo, Material* materialUsed, int currentInUndo)
{
	int position_where_material_was_find = findMaterial(repo, materialUsed->id);
	if (position_where_material_was_find == -1) {
		destroyMaterial(materialUsed);
		return -1;
	}
	if (currentInUndo == 1)
		removeEverythingAfterThisState(repo);
	updateByPosition(repo->materialsList, position_where_material_was_find, materialUsed);
	appendRepositoryInHistory(repo);
	return 1;
}

int addMaterial(Repository* repo, Material* materialUsed, int currentInUndo)
{
	if (findMaterial(repo, materialUsed->id) != -1) {
		destroyMaterial(materialUsed);
		return -1;
	}
	if (currentInUndo == 1)
		removeEverythingAfterThisState(repo);
	int positionOfTheMaterial = findMaterial(repo, materialUsed->id);
	appendDynamicallyVector(repo->materialsList, materialUsed);
	appendRepositoryInHistory(repo);
	return 1;
}

int findMaterial(Repository* repo, int id)
{
	int position = -1;
	Material* materialToCheck;
	for (int i = 0; i < sizeOfVector(repo->materialsList) && position == -1; ++i) {
		materialToCheck = getElementByPosition(repo->materialsList, i);
		if (materialToCheck->id == id) {
			position = i;
			break;
		}
	}
	return position;
}

int removeMaterial(Repository* repo, int id, int currentInUndo)
{
	int position_where_material_was_found = findMaterial(repo, id);
	if (position_where_material_was_found == -1)
		return -1;
	if (currentInUndo == 1)
		removeEverythingAfterThisState(repo);
	Material* materialToBeDestroyed = getElementByPosition(repo->materialsList, position_where_material_was_found);
	destroyMaterial(materialToBeDestroyed);
	deleteByPosition(repo->materialsList, position_where_material_was_found);
	appendRepositoryInHistory(repo);
	return 1;
}

DynamicallyVector* returnMaterialsWithName(Repository* repo, int* length, char name[]){
	int i = 0;
	int lengthOfTheList = 0;
	Material* materialToCheck;
	Material* materialToBeAppend;
	if (strlen(name) == 0) {
		*length = sizeOfVector(repo->materialsList);
		return repo->materialsList;
	}

	for (int i = 0; i < sizeOfVector(repo->materialsList); ++i) {
		materialToCheck = getElementByPosition(repo->materialsList, i);
		if (strcmp(materialToCheck->name, name) == 0)
			lengthOfTheList++;
	}

	DynamicallyVector* listOfMaterials = createDynamicallyVector(lengthOfTheList);
	for (int i = 0; i < sizeOfVector(repo->materialsList); ++i) {
		materialToCheck = getElementByPosition(repo->materialsList, i);
		if (strcmp(materialToCheck->name, name) == 0) {
			materialToBeAppend = createMaterial(materialToCheck->id, materialToCheck->supplier, materialToCheck->name, materialToCheck->quantity);
			appendDynamicallyVector(listOfMaterials, materialToBeAppend);
		}
	}
	*length = lengthOfTheList;
	return listOfMaterials;
}

DynamicallyVector* returnMaterialsWithQuantity(Repository* repo, int* length, int quantity)
{
	int i = 0;
	int lengthOfTheList = 0;
	Material* materialToCheck;
	Material* materialToBeAppend;
	for (int i = 0; i < sizeOfVector(repo->materialsList); ++i) {
		materialToCheck = getElementByPosition(repo->materialsList, i);
		if (materialToCheck->quantity < quantity)
			lengthOfTheList++;
	}

	DynamicallyVector* listOfMaterials = createDynamicallyVector(lengthOfTheList);
	for (int i = 0; i < sizeOfVector(repo->materialsList); ++i) {
		materialToCheck = getElementByPosition(repo->materialsList, i);
		if (materialToCheck->quantity < quantity) {
			materialToBeAppend = createMaterial(materialToCheck->id, materialToCheck->supplier, materialToCheck->name, materialToCheck->quantity);
			appendDynamicallyVector(listOfMaterials, materialToBeAppend);
		}
	}

	*length = lengthOfTheList;
	return listOfMaterials;
}

int undo(Repository* repository)
{
	if (repository->indexOfHistory == -1)
		return -1;

	destroyMaterialsList(repository->materialsList);
	destroyDynamicallyVector(&repository->materialsList);
	if (repository->indexOfHistory == 0)
		repository->materialsList = createDynamicallyVector(2);
	else 
		repository->materialsList = getMaterialsListCopy(repository, (DynamicallyVector*)getElementByPosition(repository->historyList, repository->indexOfHistory - 1));
	repository->indexOfHistory--;
	return 1;
}

int redo(Repository* repository)
{
	if (repository->indexOfHistory == sizeOfVector(repository->historyList) - 1)
		return -1;
	destroyMaterialsList(repository->materialsList);
	destroyDynamicallyVector(&repository->materialsList);
	repository->materialsList = getMaterialsListCopy(repository, (DynamicallyVector*)getElementByPosition(repository->historyList, repository->indexOfHistory + 1));
	repository->indexOfHistory++;
	return 1;

}

DynamicallyVector* getMaterialsListCopy(Repository* repo, DynamicallyVector* materialsList)
{
	DynamicallyVector* transitionListOfMaterials = createDynamicallyVector(sizeOfVector(materialsList));
	Material* materialToBeAppend;
	Material* materialToCheck;
	for (int i = 0; i < sizeOfVector(materialsList); ++i) {
		materialToCheck = getElementByPosition(materialsList, i);
		materialToBeAppend = createMaterial(materialToCheck->id, materialToCheck->supplier, materialToCheck->name, materialToCheck->quantity);
		appendDynamicallyVector(transitionListOfMaterials, materialToBeAppend);
	}
	return transitionListOfMaterials;
}
