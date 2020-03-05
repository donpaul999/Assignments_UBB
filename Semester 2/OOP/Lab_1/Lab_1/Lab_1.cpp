
#include <stdio.h>
#include <string.h>

using namespace std;

bool lived_Less_Than_TenThousand_Days(char person_birthdate[]) {
    char* partOfDate;
    int i, totalDays = 0;
    int dateSection = 0; // The 1st section contains the day, the 2nd contains the month, the 3rd contains the year
    int dateValueInTheSection; //Compute the number from the section given
    int day = 27, month = 2, year = 2020;
    partOfDate = strtok(person_birthdate, "-");
    while (partOfDate != NULL) {
        dateValueInTheSection = 0;
        dateSection++;
        for (i = 0; i < strlen(partOfDate); ++i) {
            dateValueInTheSection = dateValueInTheSection * 10 + partOfDate[i] - '0';
        }
        if (dateSection == 1){
            totalDays += day - dateValueInTheSection;
        }
        if (dateSection == 2) {
            totalDays = totalDays + (month - dateValueInTheSection) * 30;
        }
        if (dateSection == 3) {
            totalDays = totalDays + (year - dateValueInTheSection) * 365;
        }
        partOfDate = strtok(NULL, "-");
    }
    if (totalDays <= 10000)
        return 1;
    return 0;
}  


int main()
{   
    char consoleInput[101],copyOfInput[101];
    do {
        scanf("%s", consoleInput);
        if (!strcmp(consoleInput, "exit"))
            break;
        strcpy(copyOfInput, consoleInput);
        if (lived_Less_Than_TenThousand_Days(consoleInput)) {
            printf("%s\n", copyOfInput);
        }
    } while (1);
    return 0;
}

