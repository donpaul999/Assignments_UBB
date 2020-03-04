#include <exception>
#include <iostream>
#include "BagIterator.h"
#include "Bag.h"

using namespace std;


BagIterator::BagIterator(const Bag& c): bag(c)
{
	cout << 1;
	index = 0;
}

void BagIterator::first() {
	cout << 1;
	index = 0;
}


void BagIterator::next() {
	cout << 1;

	if (valid()) {
		index++;
	}
}


bool BagIterator::valid() const {
	cout << 1;

	//TODO - Implementation
	return false;
}



TElem BagIterator::getCurrent() const
{
	cout << 1;

	return 0 <= index && index < bag.length;
}
