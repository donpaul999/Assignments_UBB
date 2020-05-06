#include "SetIterator.h"
#include "Set.h"
#include <exception>


SetIterator::SetIterator(const Set& m) : set(m)
{
	this->currentIndexInList = set.head;
}


void SetIterator::first() {
    this->currentIndexInList = set.head;
}


void SetIterator::next() {
    if (valid())
        this->currentIndexInList = set.next[this->currentIndexInList];
    else
        throw std::exception();

}

TElem SetIterator::getCurrent()
{
    if(valid())
        return set.elements[this->currentIndexInList];
    else
        throw std::exception();
}

bool SetIterator::valid() const {
   return this->currentIndexInList!=-1;
}



void SetIterator::jumpBackward(int k){
    if(k <= 0 || !valid())
        throw std::exception();
    int nbOfSteps = currentIndexInList - k - 1;
    this->currentIndexInList = set.head;
    for(int i = 0; i <= nbOfSteps; ++i)
        next();
}
