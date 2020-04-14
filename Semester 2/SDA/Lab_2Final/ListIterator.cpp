#include "ListIterator.h"
#include "IteratedList.h"
#include <exception>

//Θ(1)
ListIterator::ListIterator(const IteratedList& list) : list(list) {
    if (list.isEmpty())
        currentNode = nullptr;
    else
        currentNode = list.head;
}

//Θ(1)
void ListIterator::first() {
    currentNode = list.head;
}

//Θ(1)
void ListIterator::next() {
    if (!valid()) {
        throw std::runtime_error("error");
    }
    currentNode = currentNode->getNextElement();
}

//Θ(1)
bool ListIterator::valid() const {
    return currentNode != nullptr;
}

//Θ(1)
TElem ListIterator::getCurrent() const {
    if (!valid()) {
        throw std::runtime_error("error");
    }
    return currentNode->getElement();
}

//Θ(1)
bool ListIterator::operator==(const ListIterator &elemToCheck) const {
    return &list == &elemToCheck.list && currentNode == elemToCheck.currentNode;
}

//Θ(1)
bool ListIterator::operator!=(const ListIterator &elemToCheck) const {
    return !(elemToCheck == *this);
}
