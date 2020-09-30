#include <iostream>
#include "UI.h"
int main() {
    Repository repo{"test.txt"};
    Service service{repo};
    UI ui{0,service};
    ui.runApp();
    return 0;
}
