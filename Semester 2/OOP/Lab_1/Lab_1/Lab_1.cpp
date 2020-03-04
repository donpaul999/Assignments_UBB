
#include <stdio.h>
#include <string.h>

using namespace std;

bool lived_Less_Than_TenThousand_Days(char person[]) {
    char* partOfDate;
    int number, i, section = 0, totalDays = 0;
    int day = 27, month = 2, year = 2020;
    partOfDate = strtok(person, "-");
    while (partOfDate != NULL) {
        number = 0;
        section++;
        for (i = 0; i < strlen(partOfDate); ++i) {
            number = number * 10 + partOfDate[i] - '0';
        }
        if (section == 1){
            totalDays += day - number;
        }
        if (section == 2) {
            totalDays = totalDays + (month - number) * 30;
        }
        if (section == 3) {
            totalDays = totalDays + (year - number) * 365;
        }
        partOfDate = strtok(NULL, "-");
    }
    if (totalDays <= 10000)
        return 1;
    return 0;
}  


int main()
{   
    char input[101],copyOfInput[101];
    char* t;
    do {
        scanf("%s", input);
        if (!strcmp(input, "exit"))
            break;
        strcpy(copyOfInput, input);
        if (lived_Less_Than_TenThousand_Days(input)) {
            printf("%s\n", copyOfInput);
        }
    } while (1);
    return 0;
}

