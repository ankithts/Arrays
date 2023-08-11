#include<stdio.h>

int main()
{
    int arr[] = {9,8,7,6,5,4,3,2,1};
    for(int i=1;i<=5;i++)
    {
        for(int j=1;j<=9;j+2)
        {
            for(int k=1;k<=j;k++)
            {
                printf("%d",arr[k]);
            }
        }
    }
    return 0;
}