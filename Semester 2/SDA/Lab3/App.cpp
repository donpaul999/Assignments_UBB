#include "Set.h"
#include "SetIterator.h"
#include "ExtendedTest.h"
#include "ShortTest.h"
#include <stack>
#include <iostream>
using namespace std;




int main() {

	testAll();
	cout << "Short tests over!"<<'\n';
	testAllExtended();

	cout << "That's all!" << endl;
	return 0;
}



