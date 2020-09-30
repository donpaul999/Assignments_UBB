#include "SMMIterator.h"
#include "SortedMultiMap.h"

//O(n)
SMMIterator::SMMIterator(const SortedMultiMap& d) : map(d){
    int node = map.bst.root;
    if (map.bst.root != -1)
    {
        while (!Travers.empty())
            Travers.pop();
        while (node != NULL_POS)
        {
            Travers.push(node);
            node = map.bst.left[node];
        }
        if (!Travers.empty())
            currentNode = Travers.top();
        else
            currentNode = NULL_POS;
    }
    else
        currentNode = NULL_POS;
}


//O(n)
void SMMIterator::first(){
    int node = map.bst.root;
    if (map.bst.root != -1)
    {
        while (!Travers.empty())
            Travers.pop();
        while (node != NULL_POS)
        {
            Travers.push(node);
            node = map.bst.left[node];
        }
        if (!Travers.empty())
            currentNode = Travers.top();
        else
            currentNode = NULL_POS;
    }
    else
        currentNode = NULL_POS;
}


//O(n)
void SMMIterator::next(){
    if (!Travers.empty())
    {
        int node = Travers.top();
        Travers.pop();
        if (map.bst.right[node] != NULL_POS)
        {
            node = map.bst.right[node];
            while (node != NULL_POS)
            {
                Travers.push(node);
                node = map.bst.left[node];
            }
        }
        if (!Travers.empty())
            currentNode = Travers.top();
        else
            currentNode = NULL_POS;
    }
    else
        throw std::exception();
}


//Theta(1)
bool SMMIterator::valid() const{
    return currentNode != NULL_POS;
}

//Theta(1)
TElem SMMIterator::getCurrent() const{
    if (!Travers.empty())
        return map.bst.info[currentNode];
    else
        throw std::exception();
}