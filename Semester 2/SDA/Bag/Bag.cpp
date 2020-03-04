#include "Bag.h"
#include "BagIterator.h"
#include <exception>
#include <iostream>
#include <math.h>
using namespace std;


Bag::Bag() {
	capacity = 100;
	length = 0;
	this->bagsize = 0;
	b = new TElem[capacity];
	max_num = INT_MIN;
	min_num = INT_MAX;
	for (int i = 0; i < capacity; ++i)
		b[i] = 0;
}


void Bag::add(TElem elem) {
	if (this->length == this->capacity) {
		this->capacity = this->capacity + 5;
		TElem* newb = new TElem[this->capacity];
		for (int i = 0; i < this->length; ++i) {
			newb[i] = this->b[i];
		}
		delete[] this->b;
		this->b = newb;
	}

	if (this->min_num = INT_MAX && this->max_num == INT_MIN) {
		cout << "1";
		this->min_num = elem;
		this->max_num = elem;
		this->b[0] = 1;
		this->length++;
		this->bagsize++;
	}
	else if (elem > max_num) {
		cout << "2";
		int dif = elem - min_num;
		int dif2 = elem - max_num;
		this->max_num = elem;
		if (this->max_num - this->min_num + 1 >= this->capacity) {
			this->capacity = this->capacity + dif2 + 1;
			TElem* newb = new TElem[this->capacity];
			for (int i = 0; i < this->length; ++i) {
				newb[i] = this->b[i];
			}
			delete[] this->b;
			this->b = newb;
			for (int i = this->length; i < this->capacity; ++i)
				this->b = 0;
		}
		this->b[dif] = 1;
		this->bagsize++;
		this->length += dif2;
	}
	else if(elem < this->min_num){
		cout << "3";
		int dif = this->min_num - elem;
		this->min_num = elem;
		if (this->max_num - this->min_num + 1 >= this->capacity) {
			this->capacity = this->capacity + dif + 1;
			TElem* newb = new TElem[this->capacity];
			for (int i = 0; i < this->length; ++i) {
				newb[i] = this->b[i];
			}
			delete[] this->b;
			this->b = newb;
			for (int i = this->length; i < this->capacity; ++i)
				this->b = 0;
		}
		for (int i = 0; i < dif; ++i) {
			this->length++;
			for (int j = this->length; j > 0; j--)
				this->b[j] = this->b[j - 1];
		}
		this->b[0] = 1;
		this->bagsize++;
	}
	else{
		cout << "4";
		int dif = elem - this->min_num;
		this->b[dif]++;
		this->bagsize++;
	}
	cout << this->min_num << " max: " << this->max_num << '\n';
	cout << elem<<": ";
	for (int i = 0; i < this->length; ++i)
		cout << this->b[i] << " ";
	cout << '\n';
}


bool Bag::remove(TElem elem) {
	cout << 2;
	if (search(elem) == false)
		return false;		
	this->b[elem - min_num]--;
	this->bagsize--;
	if (elem == min_num && this->b[elem - min_num] == 0) {
		for (int i = 0; i < this->length; ++i)
			this->b[i] = this->b[i + 1];
		this->length--;
	}
	else
		if (elem == max_num && this->b[elem - min_num] == 0) {
			this->length--;
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

