#include <iostream>

#include "ShortTest.h"
#include "ExtendedTest.h"
#include <assert.h>

#include "SortedMultiMap.h"
#include "SMMIterator.h"
#include <exception>
#include <vector>

bool relation2(int cheie1, int cheie2) {
    if (cheie1 <= cheie2) {
        return true;
    }
    else {
        return false;
    }
}

void testFunction(){
    SortedMultiMap smm = SortedMultiMap(relation2);
    assert(smm.size() == 0);
    assert(smm.getValueRange() == -1);
    smm.add(1,2);
    smm.add(1,3);
    smm.add(2,5);
    smm.add(2,6);
    assert(smm.getValueRange() == 4);
}



int main(){
    testFunction();

    testAll();
	testAllExtended();

    std::cout<<"Finished SMM Tests!"<<std::endl;
}
