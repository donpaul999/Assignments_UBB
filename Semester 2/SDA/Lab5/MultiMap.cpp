#include "MultiMap.h"
#include "MultiMapIterator.h"
#include <exception>
#include <iostream>

using namespace std;

  int MultiMap::hash(TElem tuple, int m) const
{
	int rez = tuple.key;
	while (rez < 0) rez += m;
	return rez % m;
}


void MultiMap::freeNode(node* x)
{
	if (x->next != NULL) freeNode(x->next);
	delete x;
	return;
}

void MultiMap::resize()
{
	node* x;
	node** newhash; int m = this->capacity;
	this->capacity *= 2; this->nrPairs = 0;
	newhash = new node * [this->capacity * sizeof(node*)];
	for (int i = 0; i < this->capacity; ++i)
	{
		node* startNode;
		startNode = new node;
		newhash[i] = startNode;
		newhash[i]->next = NULL;
		newhash[i]->tuple = NULL_TELEM;
	}
	swap(newhash, this->hashtable);
	for (int i = 0; i < m; ++i)
	{
		x = newhash[i];
		while (x->next != NULL)
		{
			x = x->next;
			this->add(x->tuple.key, x->tuple.tvalue);
		}
	}
	for (int i = 0; i < m; ++i) freeNode(newhash[i]);
	delete newhash;
}

MultiMap::MultiMap() {
	this->capacity = 8;
	this->nrPairs = 0;
	this->hashtable = new node * [this->capacity * sizeof(node*)];
	for (int i = 0; i < this->capacity; ++i)
	{
		node* startNode;
		startNode = new node;
		this->hashtable[i] = startNode;
		this->hashtable[i]->next = NULL;
		this->hashtable[i]->tuple = NULL_TELEM;
	}
	this->maxAlpha = 0.7;
}


void MultiMap::add(TKey c, TValue v) {
	node* newNode;
	newNode = new node;
	TElem tup;
	tup.key = c; tup.tvalue = v;
	newNode->tuple = tup; newNode->next = NULL;

	if (this->nrPairs / this->capacity > this->maxAlpha) this->resize();

	int k = hash(newNode->tuple, this->capacity);
	node* x = this->hashtable[k];
	while (x->next != NULL) x = x->next;
	x->next = newNode;

	this->nrPairs++;
}


bool MultiMap::remove(TKey c, TValue v) {
	TElem tup;
	tup.key = c; tup.tvalue = v;
	int k = hash(tup, this->capacity);

	node* currNode = this->hashtable[k];
	node* nextNode;
	while (currNode->next != NULL)
	{
		nextNode = currNode->next;
		if (nextNode->tuple.key == c && nextNode->tuple.tvalue == v)
		{
			currNode->next = nextNode->next;
			this->nrPairs--;
			delete nextNode;
			return true;
		}
		currNode = nextNode;
	}
	return false;
}


vector<TValue> MultiMap::search(TKey c) const {
	TElem tup;
	tup.key = c;
	int k = this->hash(tup, this->capacity);
	vector<TValue> a;
	node* x = this->hashtable[k];
	while (x->next != NULL)
	{
		x = x->next;
		if (x->tuple.key == c) a.push_back(x->tuple.tvalue);
	}
	return a;
}


int MultiMap::size() const {
	return this->nrPairs;
}


bool MultiMap::isEmpty() const {
	return this->nrPairs == 0;
}

MultiMapIterator MultiMap::iterator() const {
	return MultiMapIterator(*this);
}

MultiMap::~MultiMap() {
	for (int i = 0; i < this->capacity; ++i) freeNode(this->hashtable[i]);
	delete this->hashtable;
}
