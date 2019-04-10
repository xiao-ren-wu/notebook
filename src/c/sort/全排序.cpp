/**
 *给定一个元素为1-9整数的不重复数组，且该数组按照升序排序，
 *输出该数组元素的所有排列，输出 顺序为排列构成的整数的升序。
 *输入描述：
 *	第一行整数n(1<=n<=9),代表数组的长度
 *	接下来n行，每行一个整数，代表数组中的一个元素。
 *输出描述：
 *	按照排列构成的整数的升序输出每一种排列，每行一个。
 *实例：
 *	输入：
 *	3
 *	输出：
 *	159
 *	195
 *	519
 *	591
 *	915
 *	951
 * */
#define _CRT_SECURE_NO_WARNINGS
#include<stdio.h>
#include<stdlib.h>
//定义链表的节点
typedef struct node{
	int data;
	struct node *next;
}ElemSN;
ElemSN *h = NULL;
void swap(int *a, int *b){
	int temp = *a;
	*a = *b;
	*b = temp;
}
//升序链表
ElemSN* addNodeInlinkList(ElemSN *h, int data){
	ElemSN *p = (ElemSN*)malloc(sizeof(ElemSN));
	p->next = NULL;
	p->data = data;
	if (!h){
		h = p;
	}
	else{
		ElemSN *pa = NULL;
		ElemSN *c = h;
		while (c && c->data < p->data){
			pa = c;
			c = c->next;
		}
		p->next = c;
		if (!pa){
			h = p;
		}
		else{
			pa->next = p;
		}
	}
	return h;
}
//打印并释放链表
void printLinkList(ElemSN *h){
	ElemSN *p =NULL;
	while (h){
		p = h;
		printf("%d\n", h->data);
		h = h->next;
		free(p);
	}
}

//全排列
void fullSort(int data[], int begin,int end){
	if (begin >= end){
		//说明已经有一组已经拍好了，转换成数字存入链表中。
		int t = 0;
		for (int i = 0; i < end; i++){
			t = t * 10 + data[i];
		}
		h = addNodeInlinkList(h,t);
	}
	else{
		for (int i = begin; i < end; i++){
			swap(&data[i], &data[begin]);
			fullSort(data, begin + 1, end);
			swap(&data[i], &data[begin]);
		}
	}
}
int main(void){
	int data[9], n;
	scanf("%d", &n);
	for (int i = 0; i < n; i++){
		scanf("%d", &data[i]);
	}
	fullSort(data, 0, n);
	printLinkList(h);
	system("pause");
	return 0;
}
