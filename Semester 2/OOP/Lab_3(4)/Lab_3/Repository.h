#pragma once
#include "Material.h"
#include "DynamicallyVector.h"

typedef struct {
	DynamicallyVector* historyList;
	DynamicallyVector* materialsList;
	int indexOfHistory;
}Repository;

Repository* createRepository(int capacity);
void destroyMaterialsList(DynamicallyVector* materialsList);
void destroyHistoryList(DynamicallyVector* commandList); 
void destroyRepository(Repository* repository);
void appendRepositoryInHistory(Repository* repository);
void removeEverythingAfterThisState(Repository* repository);
int addMaterial(Repository* repo, Material* materialUsed, int currentInUndo);
int updateMaterial(Repository* repo, Material* materialUsed, int currentInUndo);
int findMaterial(Repository* repo, int id);
int removeMaterial(Repository* repo, int id, int currentInUndo);
DynamicallyVector* returnMaterialsWithName(Repository* repo, int* length, char name[]);
DynamicallyVector* returnMaterialsWithQuantity(Repository* repo, int* length, int quantity);
int undo(Repository* repository);
int redo(Repository* repository);
DynamicallyVector* getMaterialsListCopy(Repository* repo, DynamicallyVector* materialsList);
