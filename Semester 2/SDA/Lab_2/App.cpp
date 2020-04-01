#include <iostream>
#include "ShortTest.h"
#include "ExtendedTest.h"

int main() {
	testAll();
    std::cout << "Finished Short Tests!" << std::endl;
    testAllExtended();
	std::cout << "Finished LP Tests!" << std::endl;
	return 0;
}
