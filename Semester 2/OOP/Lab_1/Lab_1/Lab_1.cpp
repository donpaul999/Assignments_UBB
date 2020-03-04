
#include <stdio.h>
#include <string.h>

using namespace std;

bool lived(char person[]) {
    char* p;
    int number, i, pos = 0, total = 0;
    int day = 27, month = 2, year = 2020;
    p = strtok(person, "-");
    while (p != NULL) {
        number = 0;
        pos++;
        for (i = 0; i < strlen(p); ++i) {
            number = number * 10 + p[i] - '0';
        }
        if (pos == 1){
            total += day - number;
        }
        if (pos == 2) {
            total = total + (month - number) * 30;
        }
        if (pos == 3) {
            total = total + (year - number) * 365;
        }
        p = strtok(NULL, "-");
    }
    //printf("%d\n", total);
    if (total <= 10000)
        return 1;
    return 0;
}  


int main()
{   
    char word[101],test[101];
    char* t;
    do {
        scanf("%s", word);
        if (!strcmp(word, "exit"))
            break;
        strcpy(test, word);
        if (lived(word)) {
            //printf("%s", "123");
            printf("%s\n", test);
        }
    } while (1);
    return 0;
}

