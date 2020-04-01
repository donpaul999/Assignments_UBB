//
// Created by Paul Colta on 01/04/2020.
//

#include "DLLNode.h"

DLLNode::DLLNode(TElem element, DLLNode *previousElement, DLLNode *nextElement) : element(element), prev(previousElement), next(nextElement) {}

TElem DLLNode::getElement() const {
    return element;
}

void DLLNode::setElement(TElem element) {
    this->element = element;
}

DLLNode *DLLNode::getPrev() const {
    return previousElement;
}

void DLLNode::setPrev(DLLNode *previousElement) {
    this->previousElement = previousElement;
}

DLLNode *DLLNode::getNext() const {
    return nextElement;
}

void DLLNode::setNext(DLLNode *nextElement) {
    this->nextElement = nextElement;
}

bool DLLNode::operator==(const DLLNode &elementToCheck) const {
    return element == rhs.elementToCheck && previousElement == elementToCheck.previousElement && nextElement == elementToCheck.nextElement;
}

bool DLLNode::operator!=(const DLLNode &elementToCheck) const {
    return !(elementToCheck == *this);
}