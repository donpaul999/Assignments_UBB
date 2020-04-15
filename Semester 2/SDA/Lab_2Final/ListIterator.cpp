#include "IteratedList.h"
#include <exception>
#include <iostream>


//Θ(1)
ListIterator::ListIterator(IteratedList& list) : list(list) {
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
    return currentNode->getElement();}

//Θ(1)
TElem ListIterator::remove(){
    if (!valid() || list.size() == 0)
        throw std::runtime_error("error");
    TElem oldValue = getCurrent();
    if(currentNode == list.head && list.head == list.tail) {
        list.head = list.tail = nullptr;
        --list.listSize;
        return oldValue;
    }
    if (currentNode == list.head) {
        DLLNode *secondElement = list.head->getNextElement();
        list.head->setNextElement(nullptr);
        list.head->setPreviousElement(nullptr);
        delete list.head;
        list.head = secondElement;
        list.head->setPreviousElement(nullptr);
        if (list.head == nullptr)
            list.tail = list.head;
        currentNode = list.head;
    }
    else if (currentNode == list.tail) {
        DLLNode *beforeLastElement = list.tail->getPreviousElement();
        list.tail->setNextElement(nullptr);
        list.tail->setPreviousElement(nullptr);
        delete list.tail;
        list.tail = beforeLastElement;
        if (list.tail != nullptr)
            list.tail->setNextElement(nullptr);
        currentNode = list.tail;
    }
    else {
        DLLNode *next = currentNode->getNextElement();
        DLLNode *previous = currentNode->getPreviousElement();
        currentNode->setElement(next->getElement());
        currentNode->setNextElement(next->getNextElement());
        currentNode->setPreviousElement(previous);
        delete previous;
        delete next;
    }
    --list.listSize;
    return oldValue;
}

//Θ(1)
bool ListIterator::operator==(const ListIterator &elemToCheck) const {
    return &list == &elemToCheck.list && currentNode == elemToCheck.currentNode;
}

//Θ(1)
bool ListIterator::operator!=(const ListIterator &elemToCheck) const {
    return !(elemToCheck == *this);
}
