#include "MultiMap.h"
#include "MultiMapIterator.h"
#include <exception>
#include <iostream>

using namespace std;


MultiMap::MultiMap() {
	//TODO - Implementation
}

int MultiMap::hash(TElem elem){
    if (elem.second < 0)
        elem.second *= -1;
    return elem.second % hashTableCapacity;
}

void MultiMap::add(TKey c, TValue v) {
	//TODO - Implementation
}


bool MultiMap::remove(TKey c, TValue v) {
	//TODO - Implementation
	return  false;
}


vector<TValue> MultiMap::search(TKey c) const {
	//TODO - Implementation
	return vector<TValue>();
}


int MultiMap::size() const {
	//TODO - Implementation
	return 0;
}


bool MultiMap::isEmpty() const {
	//TODO - Implementation
	return false;
}

MultiMapIterator MultiMap::iterator() const {
	return MultiMapIterator(*this);
}


MultiMap::~MultiMap() {
	//TODO - Implementation
}

