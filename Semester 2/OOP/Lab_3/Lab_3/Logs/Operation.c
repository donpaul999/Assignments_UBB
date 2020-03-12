#include "Operation.h"

Operation* createOperation(char operationToBeDone[], Material* materialUsed)
{
    Operation* operation = (Operation*)malloc(sizeof(Operation));
    strcpy(operation->operationToBeDone, operationToBeDone);
    operation->materialUsed = materialUsed;
    return operation;
}

void destroyOperation(Operation* operation)
{
    destroyProduct(operation->materialUsed);
    free(operation);
}

char getOperation(Operation* operation)
{
    return operation->operationToBeDone;
}

Material* getOperationMaterial(Operation* operation)
{
    return operation->materialUsed;
}
