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


