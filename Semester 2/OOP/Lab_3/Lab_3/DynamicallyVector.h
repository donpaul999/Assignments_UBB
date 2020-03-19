#pragma once
#include <stdlib.h>
typedef void* TElem;
typedef struct {
    int lengthOfTheVector;
    int capacityOfTheVector;
    TElem* elementsList;
}DynamicallyVector;

DynamicallyVector* createDynamicallyVector(int capacityOfTheVector);
void destroyDynamicallyVector(DynamicallyVector** dynamicVectorPointer);
DynamicallyVector* getCopy(DynamicallyVector* dynamicVector);
int sizeOfVector(DynamicallyVector* dynamicVector);
void resizeDynamicallyVector(DynamicallyVector* dynamicVector);
int almostFullCapacityUsed(DynamicallyVector* dynamicVector);
void appendDynamicallyVector(DynamicallyVector* dynamicVector, TElem elementToAppend);
int deleteByPosition(DynamicallyVector* dynamicVector, int positionToDelete);
int updateByPosition(DynamicallyVector* dynamicVector, int positionToUpdate, TElem elementToUpdateWith);
TElem getElementByPosition(DynamicallyVector* dynamicVector, int positionToFind);



