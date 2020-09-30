#include "Set.h"
#include "SetIterator.h"
#include <iostream>
Set::Set() {
    this->capacity = INIT_SIZE;
    this->elements = new TElem[this->capacity];
    this->head = -1;
    this->sizeOfArray = 0;
    this->next = new int[this->capacity];
    this->firstEmpty = 0;
    for(int i = 0; i < this->capacity - 1; i++)
        this->next[i] = i + 1;
    this->next[this->capacity-1] = -1;
}

//O(n)
bool Set::add(TElem elem) {
    int currentPosition, previousPosition;

    this->findElemInSet(elem, currentPosition, previousPosition);

    if(currentPosition!=-1)
        return false;

    if(this->firstEmpty == -1)
        this->resize();

    if(previousPosition == -1){
        int newPosition = this->firstEmpty;
        this->elements[newPosition] = elem;
        this->firstEmpty = this->next[this->firstEmpty];
        this->next[newPosition] = this->head;
        this->head = newPosition;
    } else{
        int newPosition = this->firstEmpty;
        this->firstEmpty = this->next[this->firstEmpty];
        this->elements[newPosition] = elem;
        this->next[newPosition] = this->next[previousPosition];
        this->next[previousPosition] = newPosition;
    }

    this->sizeOfArray++;
    return true;
}

//O(n)
bool Set::remove(TElem elem) {
    int currentPosition, previousPosition;
    this->findElemInSet(elem, currentPosition, previousPosition);

    if(currentPosition == -1)
        return false;

    if(currentPosition == this->head){
        this->head = this->next[this->head];
    } else{
        this->next[previousPosition] = this->next[currentPosition];
    }

    this->next[currentPosition] = this->firstEmpty;
    this->firstEmpty = currentPosition;
    this->sizeOfArray--;
    return true;
}

//O(n)
bool Set::search(TElem elem) const {
    int current = this->head;
    while(current != -1)
        if(this->elements[current] == elem)
            return true;
        else
            current = this->next[current];
	return false;
}


void Set::resize() {
    auto* newElements = new TElem[this->capacity * 2];
    auto* newNext = new int[this->capacity * 2];

    for(int i = 0; i < this->sizeOfArray; i++){
        newElements[i] = this->elements[i];
        newNext[i] = this->next[i];
    }

    for(int i = this->capacity; i < this->capacity * 2 - 1 ;i++)
        newNext[i] = i + 1;
    newNext[this->capacity * 2 - 1] = -1;

    delete [] this->elements;
    delete [] this->next;

    this->elements = newElements;
    this->next = newNext;
    this->firstEmpty = this->capacity;
    this->capacity *= 2;
}

//O(n)
void Set::findElemInSet(TElem elem, int &currentPos, int &previousPos) {
    int auxiliar = this->head;
    int auxiliarPrevious = -1;
    bool found = 0;


    while(auxiliar != -1 && found == 0){
        if(this->elements[auxiliar] == elem)
            found = 1;
        else{
            auxiliarPrevious = auxiliar;
            auxiliar = this->next[auxiliar];
        }
    }

    if(found){
        currentPos = auxiliar;
        previousPos = auxiliarPrevious;
    } else{
        currentPos = -1;
        previousPos = auxiliarPrevious;
    }
}


int Set::size() const {
	return this->sizeOfArray;
}


bool Set::isEmpty() const {
	return this->sizeOfArray == 0;
}

SetIterator Set::iterator() const {
	return SetIterator(*this);
}


Set::~Set() {

    //delete[] elements;
    //delete[] next;
}
