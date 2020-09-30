#include <iostream>
#include <exception>
#include "ShortTest.h"
#include "ExtendedTest.h"
#include "ListIterator.h"
#include "IteratedList.h"

void testRemoveListIterator()
{
    IteratedList list = IteratedList();
    list.addToEnd(1);
    list.addToEnd(2);
    list.addToEnd(3);
    ListIterator it = list.first();
    assert(it.remove() == 1);
    assert(list.size() == 2);
    assert(it.getCurrent() == 2);
    assert(it.remove() == 2);
    assert(list.size() == 1);
    assert(it.getCurrent() == 3);
    assert(it.remove() == 3);
    assert(list.size() == 0);
    try{
        it.remove();
        assert(false);
    }
    catch (std::exception& e){
        assert(true);
    }

}
int main() {
    testRemoveListIterator();
	testAll();
    std::cout << "Finished Short Tests!" << std::endl;
    testAllExtended();
	std::cout << "Finished LP Tests!" << std::endl;
	return 0;
}
