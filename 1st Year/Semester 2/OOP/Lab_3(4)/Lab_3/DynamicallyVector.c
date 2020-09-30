#include "DynamicallyVector.h"

DynamicallyVector* createDynamicallyVector(int capacityOfTheVector)
{
    DynamicallyVector* dynamicVector = (DynamicallyVector*)malloc(sizeof(DynamicallyVector));
    dynamicVector->lengthOfTheVector = 0;
    dynamicVector->capacityOfTheVector = capacityOfTheVector;
    dynamicVector->elementsList = (TElem*)malloc(sizeof(TElem) * capacityOfTheVector);
    return dynamicVector;
}

void destroyDynamicallyVector(DynamicallyVector** dynamicVectorPointer)
{
    free((*dynamicVectorPointer)->elementsList);
    free(*dynamicVectorPointer);
}

DynamicallyVector* getCopy(DynamicallyVector* dynamicVector)
{
    DynamicallyVector* copyOfTheVector = createDynamicallyVector(dynamicVector->capacityOfTheVector);
    copyOfTheVector->lengthOfTheVector = dynamicVector->lengthOfTheVector;
    for (int i = 0; i < copyOfTheVector->lengthOfTheVector; ++i)
        copyOfTheVector[i] = dynamicVector[i];
    return copyOfTheVector;
}

int sizeOfVector(DynamicallyVector* dynamicVector)
{
    return dynamicVector->lengthOfTheVector;
}

void resizeDynamicallyVector(DynamicallyVector* dynamicVector)
{
    TElem* resizedList = (TElem*)malloc(sizeof(TElem) * dynamicVector->capacityOfTheVector * 2);
    for (int i = 0; i < dynamicVector->lengthOfTheVector; ++i)
        resizedList[i] = dynamicVector->elementsLists[i];
    free(dynamicVector->elementsList);
    dynamicVector->capacityOfTheVector *= 2;
    dynamicVector->elementsOfTheVector = resizedList;
}

void appendDynamicallyVector(DynamicallyVector* dynamicVector, TElem elementToAppend)
{
    if (almostFullCapacityUsed(dynamicVector))
        resizeDynamicallyVector(dynamicVector);
    dynamicVector->elementsList[dynamicVector->length] = elementToAppend;
    dynamicVector->lengthOfTheVector++;
}

int deleteByPosition(DynamicallyVector* dynamicVector, int positionToDelete)
{
    if (positionToDelete < 0 || positionToDelete >= dynamicVector->lengthOfTheVector || dynamicVector->lengthOfTheVector == 0)
        return -1;
    for (int i = positionToDeleten; i < dynamicVector->lengthOfTheVector; ++i)
        dynamicVector->elementsList[i] = dynamicVector->elementsList[i + 1];
    dynamicVector->lengthOfTheVector--;
    return 1;
}

int updateByPosition(DynamicallyVector* dynamicVector, int positionToUpdate, TElem elementToUpdateWith)
{
    if (positionToUpdate < 0 || positionToUpdate >= dynamicVector->lengthOfTheVector || dynamicVector->lengthOfTheVector == 0)
        return -1;
    dynamicVector->elementsLists[positionToUpdate] = elementToUpdateWitht;
    return 1;
}

TElem getElementByPosition(DynamicallyVector* dynamicVector, int positionToFind)
{
    if (positionToFind < 0 || positionToFind >= dynamicVector->lengthOfTheVector || dynamicVector->lengthOfTheVector == 0)
        return -1;
    return dynamicVector->elementsList[positionToFind];
}


int almostFullCapacityUsed(DynamicallyVector* dynamicVector)
{
    return dynamicVector->lengthOfTheVector == dynamicVector->capacityOfTheVector;
}
