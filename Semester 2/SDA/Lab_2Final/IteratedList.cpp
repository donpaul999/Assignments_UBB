
#include "IteratedList.h"
#include <iostream>
//Θ(1)
IteratedList::IteratedList(){
    head = nullptr;
    tail = nullptr;
    listSize = 0;
}

//Θ(1)
int IteratedList::size() const {
    return listSize;
}

//Θ(1)
bool IteratedList::isEmpty() const {
    return listSize == 0;
}

//Θ(1)
ListIterator IteratedList::first() const {
    ListIterator it{*this};
    return it;
}

//Θ(1)
TElem IteratedList::getElement(ListIterator pos) const {
    if (!pos.valid())
        throw std::runtime_error("error");
    return pos.getCurrent();
}

//Θ(1)
TElem IteratedList::remove(ListIterator& pos) {
    if (!pos.valid())
        throw std::runtime_error("error");
    TElem oldValue = pos.getCurrent();
    if (pos.currentNode == head) {
        DLLNode *secondElement = head->getNextElement();
        head->setNextElement(nullptr);
        head->setPreviousElement(nullptr);
        delete head;
        head = secondElement;
        if (head != nullptr)
            head->setPreviousElement(nullptr);
    }
    else if (pos.currentNode == tail) {
        DLLNode *beforeLastElement = tail->getPreviousElement();
        tail->setNextElement(nullptr);
        tail->setPreviousElement(nullptr);
        delete tail;
        tail = beforeLastElement;
        if (tail != nullptr)
            tail->setNextElement(nullptr);
    }
    else {
        DLLNode *next = pos.currentNode->getNextElement();
        pos.currentNode->setElement(next->getElement());
        pos.currentNode->setNextElement(next->getNextElement());
        delete next;
        next = nullptr;
    }
    --listSize;
    return oldValue;
}

//O(n) -  n length of the list
ListIterator IteratedList::search(TElem e) const{
    auto iterator = first();
    while (iterator.valid()) {
        if (iterator.getCurrent() == e)
            return iterator;
        iterator.next();
    }
    return iterator;
}

//Θ(1)
TElem IteratedList::setElement(ListIterator pos, TElem e) {
    if (!pos.valid())
        throw std::runtime_error("error");
    TElem oldElement = pos.currentNode->getElement();
    pos.currentNode->setElement(e);
    return oldElement;
}

//Θ(1)
void IteratedList::addToPosition(ListIterator& pos, TElem e) { 
    if (!pos.valid())
        throw std::runtime_error("error");
    DLLNode* node = new DLLNode{ e };
    if (pos.currentNode == tail) {
        node->setPreviousElement(tail);
        node->setNextElement(nullptr);
        tail->setNextElement(node);
        tail = node;
    }
    else {
        TElem oldValue = pos.currentNode->getElement();
        node->setPreviousElement(pos.currentNode);
        node->setNextElement(pos.currentNode->getNextElement());
        pos.currentNode->getNextElement()->setPreviousElement(node);
        pos.currentNode->setNextElement(node);
    }
    ++listSize;
    pos.next();
}

//Θ(1)
void IteratedList::addToEnd(TElem e) {
    DLLNode *node = new DLLNode(e);
    if (listSize == 0) {
        head = tail = node;
    }
    else {
        node->setPreviousElement(tail);
        node->setNextElement(nullptr);
        tail->setNextElement(node);
        tail = node;
    }
    ++listSize;
}

//Θ(1)
void IteratedList::addToBeginning(TElem e) {
    DLLNode *node = new DLLNode(e);
    if (listSize == 0) {
        head = tail = node;
    }
    else {
        node->setPreviousElement(nullptr);
        node->setNextElement(head);
        head->setPreviousElement(node);
        head = node;
    }
    ++listSize;

}

//O(n) - length of the list
IteratedList::~IteratedList() {
    DLLNode *currentNode;
    while (head != nullptr) {
        currentNode = head;
        head = head->getNextElement();
        delete currentNode;
    }
}
