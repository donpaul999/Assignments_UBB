#include "DLLNode.h"
#pragma once
//DO NOT CHANGE THIS PART
class IteratedList;
typedef int TElem;

class ListIterator{
	friend class IteratedList;
private:
	//TODO - Representation 

	//DO NOT CHANGE THIS PART
    IteratedList &list;
    DLLNode* currentNode;
    ListIterator(IteratedList& list);
public:
	void first();
	void next();
	bool valid() const;
    TElem getCurrent() const;
    TElem remove();
    bool operator==(const ListIterator &elemToCheck) const;
    bool operator!=(const ListIterator &elemToCheck) const;

};


