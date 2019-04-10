#include<stdio.h>
#include<stdlib.h>
/*
	青蛙跳台阶问题
		青蛙一次可以跳1级也可以跳2级，20级台阶有多少种跳法？
		回溯
*/
int count = 0;
void go(int data){
	if (0 == data){
		count++;
	}
	else{
		for (int i = 1; i <= 2; i++){
			if (data >= i){
				data -= i;
				go(data);
				data += i;
			}
		}
	}
}
int main(void){
	int data = 30;
	go(data);
	printf("%d ",count);
	system("pause");
	return 0;
}