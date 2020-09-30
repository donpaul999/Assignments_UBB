#include "Bag.h"
#include "ShortTest.h"
#include "ExtendedTest.h"
#include <iostream>
#include <cassert>

using namespace std;

void testForDistinct() {
	Bag b;
	assert(b.distinctCount() == 0);
	b.add(5);
	assert(b.distinctCount() == 1);
	b.add(6);
	assert(b.distinctCount() == 2);
	b.add(6);
	assert(b.distinctCount() == 2);
	b.add(7);
	b.add(8);
	assert(b.distinctCount() == 4);
}


int main() {
testForDistinct();
cout << "Passed distinct test" << endl;

testAll();
cout << "Short tests over" << endl;

testAllExtended();

cout << "All test over" << endl;
}
