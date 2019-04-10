/**
 *给定一个字符串，里面包含0-9，在不改变原顺序的情况下，
 *添加‘+’或‘-’运算符，实现结果等于目标值。输出所有的结果
 *
 *	eg:[11111]3
 *		1-1+1+1+1
 *		1+1-1+1+1
 *		1+1+1-1+1
 *	 	1+1+1+1-1
 *
 *	eg:[22]4
 *		2+2
 */
#define _CRT_SECURE_NO_WARNINGS
#include<stdlib.h>
#include<stdio.h>
#include<string.h>
void getAns(int data[], int begin, int end, int flag[], int top, int current, int tag){
	if (begin >= end){
		if (current == tag){
			int i, j=0;
			for (i = 0; i < end; i++){
				printf("%d",data[i]);
				if (i + 1 == end){
					printf("\n");
					return;
				}
				if (flag[j++] == 1){
					printf("+");
				}
				else{
					printf("-");
				}
			}
		}
		return;
	}
	for (int i = 0; i < 2; i++){
		if (i == 0){
			current -= data[begin];
		}
		else{
			current += data[begin];
			flag[top] = 1;
		}
		getAns(data, begin + 1, end, flag, top + 1, current, tag);
		if (i == 0){
			current += data[begin];
		}
		else{
			current -= data[begin];
			flag[top] = 0;
		}
	}
}
int main(void){
	char str[1000] = { 0 };
	scanf("%s",str);
	int data[1000], flag[1000] = { 0 },tag,i,j=0;
	for (i = 0; i < strlen(str)&&str[i]!=']'; i++){
		if (str[i] >='0'&&str[i]<='9'){
			data[j++] = str[i] % 48;
		}
	}
	for (i; i < strlen(str); i++){
		if (str[i] >= '0'&&str[i] <= '9'){
			tag = str[i] % 48;
		}
	}
	getAns(data, 1, j, flag, 0, data[0], tag);
	system("pause");
	return 0;
}