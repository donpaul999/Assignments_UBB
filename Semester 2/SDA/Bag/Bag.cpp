#include "Bag.h"
#include "BagIterator.h"
#include <exception>
#include <iostream>
#include <math.h>
using namespace std;


Bag::Bag() {
	capacity = 100;
	length = 0;
	b = new TElem[capacity];
	max_num = INT_MIN;
	min_num = INT_MAX;
	for (int i = 0; i < capacity; ++i)
		b[i] = 0;
}


void Bag::add(TElem elem) {
	if (this->length == this->capacity) {
		this->capacity = this->capacity * 2;
		TElem* newb = new TElem[this->capacity];
		for (int i = 0; i < this->length; ++i) {
			newb[i] = this->b[i];
		}
		delete[] this->b;
		this->b = newb;
	}

	if (min_num = INT_MAX && max_num == INT_MIN) {
		min_num = elem;
		max_num = elem;
		this->b[0] = 1;
		length++;
	}
	else if (elem > max_num) {

		int dif = elem - max_num;
		max_num = elem;
		this->capacity = this->capacity + dif + 1;
		TElem* newb = new TElem[this->capacity];
		for (int i = 0; i < this->length; ++i) {
			newb[i] = this->b[i];
		}
		delete[] this->b;
		this->b = newb;
		this->b[dif] = elem;
	}

	

}


bool Bag::remove(TElem elem) {
	//TODO - Implementation
	return false; 
}


bool Bag::search(TElem elem) const {
	//TODO - Implementation
	return false; 
}

int Bag::nrOccurrences(TElem elem) const {
	//TODO - Implementation
	return 0; 
}


int Bag::size() const {
	//TODO - Implementation
	return 0;
}


bool Bag::isEmpty() const {
	//TODO - Implementation
	return 0;
}

BagIterator Bag::iterator() const {
	return BagIterator(*this);
}


Bag::~Bag() {
	delete[] this->b;
}

