#include<stdio.h>
#include<stdlib.h>
/**
 *有n个信封，里面各有一封信，现从信封中取出信，
 *放到其他信封中，不能放回到原来的信封中，
 *有多少种放法？
 */
long cnt = 0;
void swap(int *a, int *b){
	int temp = *a;
	*a = *b;
	*b = temp;
}
void count(int data[], int begin, int end){
	if (begin > end){
		for (int i = 1; i <= end;i++){
			printf("%d",data[i]);
		}
		printf("\n");
		cnt++;
		return;
	}
	for (int i = begin; i <= end; i++){
		if (data[begin] == i){
			continue;
		}
		swap(&data[i], &data[begin]);
		count(data,begin+1,end);
		swap(&data[i], &data[begin]);
	}
}
int main(void){
	int n=4;
	int data[1000];
	for (int i = 1; i <= n; i++){
		data[i] = i;
	}
	count(data, 1, n);
	printf("%ld",cnt);
	system("pause");
	return 0;
}