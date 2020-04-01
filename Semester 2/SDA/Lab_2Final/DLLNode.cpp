//
// Created by Paul Colta on 01/04/2020.
//

#include "DLLNode.h"

DLLNode::DLLNode(TElem element, DLLNode *previousElement, DLLNode *nextElement) : element(element), previousElement(previousElement), nextElement(nextElement) {}

TElem DLLNode::getElement() const {
    return element;
}

void DLLNode::setElement(TElem element) {
    this->element = element;
}

DLLNode *DLLNode::getPreviousElement() const {
    return previousElement;
}

void DLLNode::setPreviousElement(DLLNode *previousElement) {
    this->previousElement = previousElement;
}

DLLNode *DLLNode::getNextElement() const {
    return nextElement;
}

void DLLNode::setNextElement(DLLNode *nextElement) {
    this->nextElement = nextElement;
}

bool DLLNode::operator==(const DLLNode &elementToCheck) const {
    return element == elementToCheck.element && previousElement == elementToCheck.previousElement && nextElement == elementToCheck.nextElement;
}

bool DLLNode::operator!=(const DLLNode &elementToCheck) const {
    return !(elementToCheck == *this);
}