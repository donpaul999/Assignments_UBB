#include "Bag.h"
#include "BagIterator.h"
#include <exception>
#include <iostream>
#include <math.h>
using namespace std;


Bag::Bag() {
	capacity = 3;
	length = 0;
	bagsize = 0;
	b = new TElem[capacity];
	max_num = INT_MIN;
	min_num = INT_MAX;
	for (int i = 0; i < capacity; ++i)
		b[i] = 0;
}


void Bag::add(TElem elem) {
	if (length == capacity) {
		capacity = capacity + 5;
		TElem* newb = new TElem[this->capacity];
		for (int i = 0; i < this->length; ++i) {
			newb[i] = this->b[i];
		}
		delete[] this->b;
		b = newb;
	}

	if (min_num == INT_MAX) {
		min_num = elem;
		max_num = elem;
		b[0] = 1;
		length++;
		bagsize++;
	}
	else if (elem > max_num) {
		int dif = elem - min_num;
		int dif2 = elem - max_num;
		max_num = elem;
		if (max_num - min_num + 1 >= capacity) {
			capacity = dif + 5;
			TElem* newb = new TElem[capacity];
			for (int i = 0; i < length; ++i) {
				newb[i] = b[i];
			}
			delete[]  b;
			b = newb;
			for (int i = length; i < capacity; ++i)
				b[i] = 0;
		}
		b[dif] = 1;
		bagsize++;
		length += dif2;
	}
	else if (elem < min_num) {
		int dif = min_num - elem;
		min_num = elem;
		if (max_num - min_num + 1 >= capacity) {
			capacity = max_num - min_num + 5;
			TElem* newb = new TElem[capacity];
			for (int i = 0; i < length; ++i) {
				newb[i] = b[i];
			}
			delete[]  b;
			b = newb;
			for (int i = length; i < capacity; ++i)
				b[i] = 0;
		}
		for (int i = 0; i < dif; ++i) {
			length++;
			for (int j = length; j > 0; j--)
				b[j] = b[j - 1];
		}
		for (int i = 1; i < dif; ++i)
			b[i] = 0;
		b[0] = 1;
		bagsize++;
	}
	else {
		int dif = elem - min_num;
		b[dif]++;
		bagsize++;
	}
	/*
	if (bagsize == 500){
		cout << "MINIM: " << min_num << " " << "MAXIM: " << max_num << '\n';
		for (int i = 0; i < length; ++i)
			cout << b[i] << " ";
		cout << '\n';
	}
	*/
	
}


bool Bag::remove(TElem elem) {
	if (search(elem) == false)
		return false;		
	this->b[elem - min_num]--;
	this->bagsize--;
	if (elem == min_num && this->b[elem - min_num] == 0) {
		for (int i = 0; i < this->length; ++i)
			this->b[i] = this->b[i + 1];
		this->length--;
		min_num++;
	}
	else
		if (elem == max_num && this->b[elem - min_num] == 0) {
			this->length--;
			max_num--;
		}
	while (b[0] == 0 && this->length > 0) {
		for (int i = 0; i < this->length; ++i)
			this->b[i] = this->b[i + 1];
		this->length--;
		min_num++;
	}
	while (b[this->length - 1] == 0 && this->length > 0) {
		this->length--;
		max_num--;
	}
	return true;
}


bool Bag::search(TElem elem) const {
	if(max_num == INT_MIN || elem < min_num || elem > max_num)
		return false; 
	int dif = elem - min_num;
	return b[dif] != 0;
}

int Bag::nrOccurrences(TElem elem) const {
	if (search(elem) == false)
		return 0;
	return this->b[elem - min_num];
}


int Bag::size() const {
	if(this->bagsize == 0)
		return 0;
	//cout << "SIZE: " << this->bagsize << '\n';
	return this->bagsize;
}


bool Bag::isEmpty() const {
	return bagsize == 0;
}

BagIterator Bag::iterator() const {
	return BagIterator(*this);
}


Bag::~Bag() {
	delete[] this->b;
}

