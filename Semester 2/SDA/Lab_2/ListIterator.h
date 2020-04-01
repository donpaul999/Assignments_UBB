#pragma once
#include "DLLNode.h"
#include "IteratedList.h"

//DO NOT CHANGE THIS PART
class IteratedList;
typedef int TElem;

class ListIterator{
	friend class IteratedList;
private:
	//TODO - Representation 

	//DO NOT CHANGE THIS PART
	const IteratedList& list;
    DLLNode* currentNode;
    ListIterator(const IteratedList& list);
public:
	void first();
	void next();
	bool valid() const;
    TElem getCurrent() const;
    bool operator==(const ListIterator &elemToCheck) const;
    bool operator!=(const ListIterator &elemToCheck) const;

};


