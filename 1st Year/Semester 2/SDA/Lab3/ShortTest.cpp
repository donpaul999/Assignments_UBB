#include "ShortTest.h"
#include <assert.h>
#include "Set.h"
#include "SetIterator.h"
#include <iostream>

void testAll() {
    int i = 0;
    Set s;
    //std::cout << i++<<'\n';
    assert(s.isEmpty() == true);
    //std::cout << i++<<'\n';

    assert(s.size() == 0);
    //std::cout << i++<<'\n';

    assert(s.add(5)==true);
    //std::cout << i++<<'\n';

	assert(s.add(1)==true);
   //std::cout << i++<<'\n';

	assert(s.add(10)==true);
    //std::cout << i++<<'\n';

	assert(s.add(7)==true);
    //std::cout << i++<<'\n';

    assert(s.add(1)==false);
    //std::cout << i++<<'\n';

	assert(s.add(10)==false);
    //std::cout << i++<<'\n';

	assert(s.add(-3)==true);
    //std::cout << i++<<'\n';

	assert(s.size() == 5);
    //std::cout << i++<<'\n';

	assert(s.search(10) == true);
    //std::cout << i++<<'\n';

	assert(s.search(16) == false);
    //std::cout << i++<<'\n';

	assert(s.remove(1) == true);
    //std::cout << i++<<'\n';

	assert(s.remove(6) == false);
    //std::cout << i++<<'\n';

	assert(s.size() == 4);
    //std::cout << i++<<'\n';


	SetIterator it = s.iterator();
	it.first();
	int sum = 0;
	while (it.valid()) {
		TElem e = it.getCurrent();
		sum += e;
		it.next();
	}
	assert(sum == 19);

}

