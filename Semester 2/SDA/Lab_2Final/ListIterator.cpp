#include "ListIterator.h"
#include "IteratedList.h"
#include <exception>

ListIterator::ListIterator(const IteratedList& list) : list(list) {
    if (list.isEmpty())
        currentNode = nullptr;
    else
        currentNode = list.head;
}

void ListIterator::first() {
    currentNode = list.head;
}

void ListIterator::next() {
    if (!valid()) {
        throw std::runtime_error("error");
    }
    currentNode = currentNode->getNextElement();
}

bool ListIterator::valid() const {
    return currentNode != nullptr;
}

TElem ListIterator::getCurrent() const {
    if (!valid()) {
        throw std::runtime_error("error");
    }
    return currentNode->getElement();
}

bool ListIterator::operator==(const ListIterator &elemToCheck) const {
    return &list == &elemToCheck.list && currentNode == elemToCheck.currentNode;
}

bool ListIterator::operator!=(const ListIterator &elemToCheck) const {
    return !(elemToCheck == *this);
}
