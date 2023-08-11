#include<iostream>
using namespace std;

int main()
{
    enum up{
        u, 
        p=-2,
        d,
    };
    call(d);
    return 0;
}


int call(int p)
{
    if(p>0)
     printf("%d",call(p-1));
    else
     printf("%d",call(p)-1);

}