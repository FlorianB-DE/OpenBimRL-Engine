#include <iostream>
#include "include/json.hpp"
#include "lib.h"

int sum(int n1, int n2)
{
    std::cout << "calculating: " << n1 << " + " << n2 << std::endl;
    return n1 + n2;
}
