#pragma once
#include "Material.h"
typedef struct {
	char operationToBeDone[50];
	Material* materialUsed;
}Operation;

Operation* createOperation(char operationToBeDone[], Material* materialUsed);
void destroyOperation(Operation* operation);
char getOperation(Operation* operation);
Material* getOperationMaterial(Operation* operation);
