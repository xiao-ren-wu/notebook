#include<stdio.h>
#include<stdlib.h>
/*
	字典序算法：
		给定一个正整数，实现一个方法，通过交换这个数
		任意位的位置，是的得到的数比这个数次大。

		eg:
		   12345====>12354
		   12354====>12435
	算法分析：
		为了和原数接近，我们需要尽量保持高位不变，低位在最小的范围内变换次序。
		所以从低位开始寻找如果找到第一个不是升序的元素，
		与之前比他次大的元素进行交换，之后将序排列后面的元素即可
		例如：32587654===>找到元素找到元素5，交换5和6的位置，得到32687554，
		交换的元素后面的数字升序排列得到32645578
*/
void swap(int *a, int *b){
	if (!a || !b){
		return;
	}
	int temp = *a;
	*a = *b;
	*b = temp;
}
void getLittleBigNum(int data[], int len){
	if (data == NULL || len < 2){
		return;
	}
	int i,j;
	for (i = len - 1, j = i - 1; j >= 0 && data[j] >= data[i]; j--, i--);
	if (0 == j&&data[j] >= data[i]){
		return;
	}
	else{
		for (i = len - 1; i>j&&data[i] <= data[j]; i--);
		swap(&data[i], &data[j]);
		j++;
		for (i = len - 1; i >j; i--, j++){
			swap(&data[i], &data[j]);
		}
	}
}

int main(void){
	int data[8] = { 3,2,5,8,7,6,5,4 };
	getLittleBigNum(data, 8);
	for (int i = 0; i < 8; i++){
		printf("%d",data[i]);
	}
	system("pause");
	return 0;
}