#include <iostream>
#include <assert.h>
#include "MultiMap.h"
#include "ExtendedTest.h"
#include "ShortTest.h"
#include "MultiMapIterator.h"

using namespace std;

bool condition(int a){
    return a % 2 == 1;
}


void testFilter(){
    MultiMap m;
    m.add(1, 100);
    m.add(2, 200);
    m.add(3, 300);
    m.filter(*condition);
    assert(m.size() == 2);

    MultiMapIterator im = m.iterator();
}

int main() {


	testAll();
	testFilter();
	testAllExtended();
	cout << "End" << endl;

}
