#include "DynamicallyVector.h"

DynamicallyVector* createDynamicallyVector(int capacity)
{
    DynamicallyVector* dynamicVector = (DynamicallyVector*)malloc(sizeof(DynamicallyVector));
    dynamicVector->length = 0;
    dynamicVector->capacity = capacity;
    dynamicVector->elements = (TElem*)malloc(sizeof(TElem) * capacity);
}

void destroyDynamicallyVector(DynamicallyVector** dynamicVectorPointer)
{
    free((*dynamicVectorPointer)->elements);
    free(*dynamicVectorPointer);
}

DynamicallyVector* getCopy(DynamicallyVector* dynamicVector)
{
    DynamicallyVector* copyOfTheVector = createDynamicallyVector(dynamicVector->capacity);
    copyOfTheVector->length = dynamicVector-> length;
    for (int i = 0; i < copyOfTheVector->length; ++i)
        copyOfTheVector[i] = dynamicVector[i];
    return copyOfTheVector;
}

int size(DynamicallyVector* dynamicVector)
{
    return dynamicVector->length;
}

void resizeDynamicallyVector(DynamicallyVector* dynamicVector)
{
    TElem* resizedList = (TElem*)malloc(sizeof(TElem) * dynamicVector->capacity * 2);
    for (int i = 0; i < dynamicVector->length; ++i) 
        resizedList[i] = dynamicVector->elements[i];
    free(dynamicVector->elements);
    dynamicVector->capacity *= 2;
    dynamicVector->elements = resizedList;
}

void appendDynamicallyVector(DynamicallyVector* dynamicVector, TElem element)
{
    if (almostFullCapacityUsed(dynamicVector))
        resizeDynamicallyVector(dynamicVector);
    dynamicVector->elements[dynamicVector->length] = element;
    dynamicVector->length++;
}

int deleteByPosition(DynamicallyVector* dynamicVector, int position)
{
    if (position < 0 || position >= dynamicVector->length || dynamicVector->length == 0)
        return -1;
    for (int i = position; i < dynamicVector->length; ++i)
        dynamicVector->elements[i] = dynamicVector->elements[i + 1];
    dynamicVector->length--;
    return 1;
}

int updateByPosition(DynamicallyVector* dynamicVector, int position, TElem element)
{
    if (position < 0 || position >= dynamicVector->length || dynamicVector->length == 0)
        return -1;
    dynamicVector->elements[position] = element;
    return 1;
}

TElem getElementByPosition(DynamicallyVector* dynamicVector, int position)
{
    if (position < 0 || position >= dynamicVector->length || dynamicVector->length == 0)
        return -1;
    return dynamicVector->elements[position];
}

void swapElements(DynamicallyVector* dynamicVector, int position1, int position2)
{
    if (position1 < 0 || position1 >= dynamicVector->length || dynamicVector->length == 0)
        return -1;
    if (position2 < 0 || position2 >= dynamicVector->length)
        return -1;
    TElem transitionElement = dynamicVector->elements[position1];
    dynamicVector->elements[position1] = dynamicVector->elements[position2];
    dynamicVector->elements[position2] = transitionElement;
}

int almostFullCapacityUsed(DynamicallyVector* dynamicVector)
{
    return dynamicVector->length == dynamicVector->capacity;
}
