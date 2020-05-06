#include "Set.h"
#include "SetIterator.h"
#include "ExtendedTest.h"
#include "ShortTest.h"
#include <stack>
#include <iostream>
using namespace std;

void TestJump(){
    Set s;
    s.add(5);
    s.add(1);
    s.add(11);
    s.add(3);
    s.add(7);
    SetIterator it = s.iterator();
    it.next();
    it.next();
    it.next();
    it.next();
    assert(it.getCurrent() == 7);
    try{
        it.jumpBackward(5);
        assert(false);
    }
    catch (exception& e)
    {
        assert(true);
    }
    it.jumpBackward(2);
    assert(it.getCurrent() == 11);
    it.jumpBackward(1);
    assert(it.getCurrent() == 1);
    it.jumpBackward(1);
    assert(it.getCurrent() == 5);


}



int main() {
    TestJump();
	testAll();
	cout << "Short tests over!"<<'\n';
	testAllExtended();

	cout << "That's all!" << endl;
	return 0;
}



