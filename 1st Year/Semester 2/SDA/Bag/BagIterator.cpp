#include <exception>
#include <iostream>
#include "BagIterator.h"
#include "Bag.h"

using namespace std;


BagIterator::BagIterator(const Bag& c): bag(c)
{
	index = 0;
	frec = bag.b[0];
	poz = 0;
}

void BagIterator::first() {
	index = 0;
	frec = bag.b[0];
	poz = 0;
}

//O(1), Theta(poz)

void BagIterator::next() {
	if (!valid()) {
		throw runtime_error{ "" };
	}
	else {
		index++;
		frec--;
		if (frec <= 0) {
			poz++;
			while(bag.b[poz] == 0 && poz < bag.length)
				poz++;
			frec = bag.b[poz];
		}
	}
}

//O(1), Theta(1) 
bool BagIterator::valid() const {
	return 0 <= index && index < bag.bagsize;
}



TElem BagIterator::getCurrent() const
{
	if (!valid()) {
		throw runtime_error{ "" };
	}
	else {
		//cout << "VAL " << bag.min_num + poz << '\n';
		return bag.min_num + poz;
	}
}
