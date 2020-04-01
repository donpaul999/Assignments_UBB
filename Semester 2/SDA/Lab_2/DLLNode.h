//
// Created by Paul Colta on 01/04/2020.
//

#ifndef LAB_2_DLLNODE_H
#define LAB_2_DLLNODE_H


class DLLNode {
private:
    TElem element;
    DLLNode* previousElement;
    DLLNode* nextElement;

public:
    DLLNode(TElem element = 0, DLLNode *previousElement = nullptr, DLLNode *nextElement = nullptr);
    TElem getElement() const;
    void setElement(TElem element);
    DLLNode *getPrevious() const;
    void setPrevious(DLLNode *previousElement);
    DLLNode *getNext() const;
    void setNextElement(DLLNode *nextElement);
    bool operator==(const DLLNode &elementToCheck) const;
    bool operator!=(const DLLNode &elementToCheck) const;
};
};


#endif //LAB_2_DLLNODE_H
