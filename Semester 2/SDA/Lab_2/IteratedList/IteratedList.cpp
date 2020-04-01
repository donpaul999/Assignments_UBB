
#include <exception>
#include "ListIterator.h"
#include "IteratedList.h"

IteratedList::IteratedList() {
	//TODO - Implementation
}

int IteratedList::size() const {
	//TODO - Implementation
	return 0;
}

bool IteratedList::isEmpty() const {
	//TODO -  Implementation
	return false;
}

ListIterator IteratedList::first() const {
	return ListIterator(*this);
}

TElem IteratedList::getElement(ListIterator pos) const {
	//TODO - Implementation
	return NULL_TELEM;
}

TElem IteratedList::remove(ListIterator& pos) {
	//TODO - Implementation
	return NULL_TELEM;
}

ListIterator IteratedList::search(TElem e) const{
	//TODO - Implementation
	return ListIterator(*this);	
}

TElem IteratedList::setElement(ListIterator pos, TElem e) {
    //TODO - Implementation
	return NULL_TELEM;
}

void IteratedList::addToPosition(ListIterator& pos, TElem e) {
    //TODO - Implementation
}

void IteratedList::addToEnd(TElem e) {
	//TODO - Implementation
}

IteratedList::~IteratedList() {
	//TODO - Implementation
}
