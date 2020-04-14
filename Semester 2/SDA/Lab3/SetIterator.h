#pragma once
#include "Set.h"

class SetIterator
{
	//DO NOT CHANGE THIS PART
	friend class Set;
private:
    int currentIndexInList;
    //DO NOT CHANGE THIS PART
	const Set& set;
	SetIterator(const Set& s);

	//TODO - Representation

public:
	void first();
	void next();
	TElem getCurrent();
	bool valid() const;
};


