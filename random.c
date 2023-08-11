#include<stdio.h>

#include <stdio.h>

int main() {
    int rows = 5;
    int num = 9876543;
    int spaces = rows - 1;

    for (int i = 1; i <= rows; i++) {
        for (int j = 1; j <= spaces; j++) {
            printf(" ");
        }
        
        for (int j = 1; j <= 2 * i - 1; j++) {
            printf("%d", num % 10);
            num--;
        }
        
        printf("\n");
        spaces--;
    }

    return 0;
}
