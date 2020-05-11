#pragma once
#include<vector>
#include<utility>
#define key first
#define tvalue second
//DO NOT INCLUDE MultiMapIterator

using namespace std;

//DO NOT CHANGE THIS PART
typedef int TKey;
typedef int TValue;
typedef pair<TKey, TValue> TElem;
#define NULL_TVALUE -11111;
#define NULL_TELEM pair<int,int>(-11111, -11111);
class MultiMapIterator;

struct node {
	TElem tuple;
	struct node* next;
};

class MultiMap
{
	friend class MultiMapIterator;

private:
	//TODO - Representation
	int capacity;
	int nrPairs;
	node** hashtable;
	float maxAlpha;
	//resize the hashtable according to the limitations of alpha
	void resize();

public:
	//constructor
	MultiMap();

	//adds a key value pair to the multimap
	void add(TKey c, TValue v);

	//removes a key value pair from the multimap
	//returns true if the pair was removed (if it was in the multimap) and false otherwise
	bool remove(TKey c, TValue v);

	//returns the vector of values associated to a key. If the key is not in the MultiMap, the vector is empty
	vector<TValue> search(TKey c) const;

	//returns the number of pairs from the multimap
	int size() const;

	//checks whether the multimap is empty
	bool isEmpty() const;

	//returns an iterator for the multimap
	MultiMapIterator iterator() const;

    void freeNode(node* x);

    //hashing function
    int hash(TElem tuple, int m) const;

	//desctructor
	~MultiMap();

};

