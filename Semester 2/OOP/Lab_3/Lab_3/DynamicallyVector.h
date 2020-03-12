#pragma once
#include <stdlib.h>
typedef void* TElem;
typedef struct {
    int length;
    int capacity;
    TElem* elements;
}DynamicallyVector;

DynamicallyVector* createDynamicallyVector(int capacity);
void destroyDynamicallyVector(DynamicallyVector** dynamicVectorPointer);
DynamicallyVector* getCopy(DynamicallyVector* dynamicVector);
int size(DynamicallyVector* dynamicVector);
void resizeDynamicallyVector(DynamicallyVector* dynamicVector);
int almostFullCapacityUsed(DynamicallyVector* dynamicVector);
void appendDynamicallyVector(DynamicallyVector* dynamicVector, TElem element);
int deleteByPosition(DynamicallyVector* dynamicVector, int position);
int updateByPosition(DynamicallyVector* dynamicVector, int position, TElem element);
TElem getElementByPosition(DynamicallyVector* dynamicVector, int position);
void swapElements(DynamicallyVector* dynamicVector, int position1, int position2);



