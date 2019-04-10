#include<stdio.h>
#include<stdlib.h>
/*
	根据维基百科上对h指数的定义：“一名科学家的h指数是指在其发表论文中，有h篇论文至少被引用h次，在其余N-h篇的引用次数均不超过h次，例如在给定一个数组中”
	c=[3,0,4,5,1]，这意味着该研究员总共有5篇论文，每篇论文分别获得了3次引用，其余两篇的引用次数都不超过3次，所以h的指数为3.
	题目：给出一个数组求出引用次数。
*/

int gethAns(int data[], int len){
	if (NULL == data || len <= 0){
		return -1;
	}
	for (int i = 0; i < len - 1; i++){
		for (int j = i + 1; j < len; j++){
			if (data[i] > data[j]){
				int temp = data[i];
				data[i] = data[j];
				data[j] = temp;
			}
		}
	}
	int count = len;
	int h=0;
	int i = 0;
	while (i<len){
		if (data[i] >= count){
			h = count;
			break;
		}
		i++;
		count--;
	}
	return h;
}
int main(void){
	int data[5] = { 3, 0, 1, 4, 5 };
	printf("%d",gethAns(data,5));
	system("pause");
	return 0;
}