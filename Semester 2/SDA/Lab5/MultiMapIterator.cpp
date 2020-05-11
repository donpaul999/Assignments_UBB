#include "MultiMapIterator.h"
#include "MultiMap.h"


MultiMapIterator::MultiMapIterator(const MultiMap& c): col(c) {
	this->first();
}

TElem MultiMapIterator::getCurrent() const{
	if (!this->valid()) throw exception();
	return this->current->tuple;
}

bool MultiMapIterator::valid() const {
	return this->current != NULL;
}

void MultiMapIterator::next() {
	if (!this->valid()) throw exception();
	this->current = this->current->next;
	while(this->current == NULL && this->arrayIndex + 1 < this->col.capacity)
	{
		this->arrayIndex++;
		this->current = this->col.hashtable[this->arrayIndex]->next;
	}
}

void MultiMapIterator::first() {
	this->arrayIndex = 0;
	this->current = this->col.hashtable[this->arrayIndex]->next;
	while (this->current == NULL && this->arrayIndex + 1 < this->col.capacity)
	{
		this->arrayIndex++;
		this->current = this->col.hashtable[this->arrayIndex]->next;
	}
}

