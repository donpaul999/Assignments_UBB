#pragma once
#include <stdexcept>
#include "Movie.h"
typedef Movie TElem;

template <typename TElem>


class DynamicVector {

private:
    TElem* elementsList;
    int sizeOfVector;
    int capacityOfVector;

  

public:
    DynamicVector(int capacityGiven);
    DynamicVector(const  DynamicVector& vectorToSet);
    ~DynamicVector();
    void resizeElementsList();
    bool needsResize();
    int searchElementInList(const TElem& elementToSearch);
    void append(TElem elementToAppend);
    void remove(const TElem& elementToRemove);
    void update(const TElem& elementToUpdate, const TElem& updatedElement);
    TElem& operator[](int positionOfTheElement);
    int size();
    int capacity();
    DynamicVector& operator=(const  DynamicVector& vectorUsed);
};


template<typename TElem>
DynamicVector<TElem>::DynamicVector(int capacityGiven){
    this->capacityOfVector = capacityGiven;
    this->sizeOfVector = 0;
    this->elementsList = new TElem[this->capacityOfVector];
}
template<typename TElem>
DynamicVector<TElem>::DynamicVector(const  DynamicVector& vectorToSet){
    capacityOfVector = vectorToSet.capacityOfVector;
    sizeOfVector = vectorToSet.sizeOfVector;
    elementsList = new TElem[vectorToSet.capacityOfVector];
    for (int i = 0; i < sizeOfVector; ++i) {
        elementsList[i] = vectorToSet.elementsList[i];
    }
}

template<typename TElem>
DynamicVector<TElem>::~DynamicVector()
{
    delete[] elementsList;
}

template<typename TElem>
inline void DynamicVector<TElem>::resizeElementsList()
{
    TElem* newList = new TElem[capacityOfVector * 2];
    for (int i = 0; i < sizeOfVector; ++i)
        newList[i] = elementsList[i];
    capacityOfVector *= 2;
    delete[] elementsList;
    elementsList = newList;
}
template<typename TElem>
inline bool DynamicVector<TElem>::needsResize()
{
    return sizeOfVector == capacityOfVector;
}

template<typename TElem>
int DynamicVector<TElem>::searchElementInList(const TElem& elementToSearch) {
    for (int i = 0; i < sizeOfVector; ++i) {
        if (elementsList[i] == elementToSearch)
            return i;
    }
    return -1;
}

template<typename TElem>
void DynamicVector<TElem>::append(TElem elementToAppend) {
    if (needsResize())
        resizeElementsList();
    elementsList[sizeOfVector++] = elementToAppend;
}

template<typename TElem>
void DynamicVector<TElem>::remove(const TElem& elementToRemove) {
    int positionWhereFound = searchElementInList(elementToRemove);
    if (positionWhereFound != -1) {
        for (int i = positionWhereFound; i < sizeOfVector - 1; ++i) {
            elementsList[i] = elementsList[i + 1];
        }
        --sizeOfVector;
    }
}

template<typename TElem>
void DynamicVector<TElem>::update(const TElem& elementToUpdate, const TElem& updatedElement) {
    int positionWhereFound = searchElementInList(elementToUpdate);
    if (positionWhereFound != -1) {
        elementsList[positionWhereFound] = updatedElement;
    }
}

template<typename TElem>
TElem& DynamicVector<TElem>::operator[](int positionOfTheElement) {
    if (positionOfTheElement < 0 || positionOfTheElement >= sizeOfVector)
        throw std::runtime_error("Invalid position");
    return elementsList[positionOfTheElement];
}

template<typename TElem>
int DynamicVector<TElem>::size() {
    return sizeOfVector;
}

template<typename TElem>
int DynamicVector<TElem>::capacity()
{
    return capacityOfVector;
}

template<typename TElem>
DynamicVector<TElem>& DynamicVector<TElem>::operator=(const  DynamicVector& vectorUsed){
    if (this != &vectorUsed) {
        if (capacityOfVector < vectorUsed.sizeOfVector) {
            delete[] elementsList;
            elementsList = new TElem[vectorUsed.capacityOfVector];
        }
        sizeOfVector = vectorUsed.sizeOfVector;
        capacityOfVector = vectorUsed.capacityOfVector;
        for (int i = 0; i < sizeOfVector; ++i)
            elementsList[i] = vectorUsed.elementsList[i];
    }
    return *this;
}
