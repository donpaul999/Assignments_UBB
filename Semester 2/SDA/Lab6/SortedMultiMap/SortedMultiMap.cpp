#include "SMMIterator.h"
#include "SortedMultiMap.h"
#include <iostream>
#include <vector>
#include <exception>
using namespace std;


//Theta(n)
void SortedMultiMap::resize()
{
	TElem* newInfo = new TElem[bst.capacity * 2];
	int* newRight = new int[bst.capacity * 2];
	int* newLeft = new int[bst.capacity * 2];
	int* newParent = new int[bst.capacity * 2];
	for (int i = 0; i < bst.capacity; i++)
	{
		newInfo[i] = bst.info[i];
		newRight[i] = bst.right[i];
		newLeft[i] = bst.left[i];
		newParent[i] = bst.parent[i];
	}
	for (int i = bst.capacity; i < bst.capacity * 2; i++)
	{
		newInfo[i] = NULL_TELEM;
		newRight[i] = NULL_POS;
		newLeft[i] = i+1;
		newParent[i] = NULL_POS;
	}
	newLeft[bst.capacity * 2 - 1] = -1;
	bst.firstEmpty = bst.capacity;
	bst.capacity *= 2;
	delete[] bst.info;
	delete[] bst.right;
	delete[] bst.left;
	delete[] bst.parent;
	bst.info = newInfo;
	bst.right = newRight;
	bst.left = newLeft;
	bst.parent = newParent;
}

//Theta(n)
SortedMultiMap::SortedMultiMap(Relation r) {
	relation = r;
	mapSize = 0;
	bst.capacity = 10;
	bst.firstEmpty = 0;
	bst.root = -1;
	bst.info = new TElem[bst.capacity];
	bst.left = new int[bst.capacity];
	bst.right = new int[bst.capacity];
	bst.parent = new int[bst.capacity];
	for (int i = 0; i < bst.capacity; i++)
	{
		bst.info[i] = NULL_TELEM;
		bst.left[i] = i + 1;
		bst.right[i] = NULL_POS;
		bst.parent[i] = NULL_POS;
	}
	bst.left[bst.capacity - 1] = -1;
}


//O(n)
void SortedMultiMap::add(TKey c, TValue v) {
	if (bst.root == -1)
	{
		bst.info[bst.firstEmpty] = make_pair(c,v);
		bst.parent[bst.firstEmpty] = -1;
		bst.root = bst.firstEmpty;
		bst.firstEmpty = bst.left[bst.firstEmpty];
		bst.left[bst.root] = NULL_POS;
		mapSize++;
		return;
	}

	int current = bst.root;
	int parent = -1;

	while (current != NULL_POS)
	{
		parent = current;
		if (!relation(bst.info[current].first, c))
			current = bst.left[current];
		else
			current = bst.right[current];
	}

	if (bst.firstEmpty == -1)
		resize();

	bst.info[bst.firstEmpty] = make_pair(c, v);
	bst.parent[bst.firstEmpty] = parent;
	if (!relation(bst.info[parent].first, c))
		bst.left[parent] = bst.firstEmpty;
	else
		bst.right[parent] = bst.firstEmpty;

	int oldEmpty = bst.firstEmpty;
	bst.firstEmpty = bst.left[bst.firstEmpty];
	bst.left[oldEmpty] = NULL_POS;
	mapSize++;
}

//O(n)
vector<TValue> SortedMultiMap::search(TKey c) const {
	vector<TValue> val{};
	if (bst.root == -1)
		return val;
	int current = bst.root;
	while (current != NULL_POS)
	{
		if (relation(bst.info[current].first, c))
		{
			if (bst.info[current].first == c)
				val.push_back(bst.info[current].second);
			current = bst.right[current];
		}
		else
			current = bst.left[current];
	}
	return val;
}


//O(n)
int SortedMultiMap::getMinimum(int node)
{
	int current = node;
	while (bst.left[current] != NULL_POS)
		current = bst.left[current];
	return current;
}


//O(n)
bool SortedMultiMap::remove(TKey c, TValue v) {

	if (bst.root == -1)
		return false;
	int current = bst.root;
	int parent = -1;
	while (current != NULL_POS)
	{
		if (bst.info[current] == make_pair(c, v))
		{
			if (bst.left[current] == NULL_POS && bst.right[current] == NULL_POS)
			{
				if (current == bst.root)
				{
					bst.left[current] = bst.firstEmpty;
					bst.firstEmpty = current;
					bst.root = -1;
				}
				else
				{
					if (bst.left[parent] == current)
						bst.left[parent] = NULL_POS;
					else
						bst.right[parent] = NULL_POS;
					bst.left[current] = bst.firstEmpty;
					bst.firstEmpty = current;
				}
				mapSize--;
			}
			else if (bst.left[current] == NULL_POS && bst.right[current] != NULL_POS)
			{
				if (current == bst.root)
				{
					bst.root = bst.right[current];
					bst.left[current] = bst.firstEmpty;
					bst.right[current] = NULL_POS;
					bst.info[current] = NULL_TELEM;
					bst.firstEmpty = current;
				}
				else
				{
					if (bst.left[parent] == current)
						bst.left[parent] = bst.right[current];
					else
						bst.right[parent] = bst.right[current];
					bst.left[current] = bst.firstEmpty;
					bst.right[current] = NULL_POS;
					bst.info[current] = NULL_TELEM;
					bst.firstEmpty = current;
				}
				mapSize--;
			}
			else if (bst.left[current] != NULL_POS && bst.right[current] == NULL_POS)
			{
				if (current == bst.root)
				{
					bst.root = bst.left[current];
					bst.left[current] = bst.firstEmpty;
					bst.right[current] = NULL_POS;
					bst.info[current] = NULL_TELEM;
					bst.firstEmpty = current;
				}
				else
				{
					if (bst.left[parent] == current)
						bst.left[parent] = bst.left[current];
					else
						bst.right[parent] = bst.left[current];
					bst.left[current] = bst.firstEmpty;
					bst.right[current] = NULL_POS;
					bst.info[current] = NULL_TELEM;
					bst.firstEmpty = current;
				}
				mapSize--;
			}
			else
			{
				int minimum = getMinimum(bst.right[current]);
				remove(bst.info[minimum].first, bst.info[minimum].second);
				bst.info[current] = bst.info[minimum];
			}
			return true;
		}
		parent = current;
		if (!relation(bst.info[current].first, c))
			current = bst.left[current];
		else
			current = bst.right[current];
	}
	return false;
}

//Theta(1)
int SortedMultiMap::size() const {
	return mapSize;
}

//Theta(1)
bool SortedMultiMap::isEmpty() const {
	if (mapSize == 0)
		return true;
	return false;
}

//Theta(1)
SMMIterator SortedMultiMap::iterator() const {
	return SMMIterator(*this);
}


//Theta(1)
SortedMultiMap::~SortedMultiMap() {
	delete[] bst.info;
	delete[] bst.right;
	delete[] bst.left;
	delete[] bst.parent;
}



