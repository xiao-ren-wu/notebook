#include<stdio.h>
#include<stdlib.h>
#include<string.h>
typedef struct node{
	double data;
	struct node *next;
	struct node *pa;
}Stack;
//入栈函数
void push(Stack **top,double data){
	if (!top){
		return;
	}
	Stack *pnew = (Stack*)malloc(sizeof(Stack));
	pnew->data = data;
	pnew->next = pnew->pa=NULL;
	if (*top == NULL){
		*top = pnew;
	}
	else{
		pnew->pa = *top;
		(*top)->next = pnew;
		*top = (*top)->next;
	}
}
//出栈函数
double pop(Stack **top){
	if (!top || !(*top)){
		return -1;
	}
	Stack *del = *top;
	double data = del->data;
	*top = (*top)->pa;
	if (*top){
		(*top)->next = NULL;
	}
	free(del);
	return data;
}
//字符串转换成数字
double strToNum(char str[], int *i){
	double temp = 0;
	while (str[*i] >= '0'&&str[*i] <= '9'){
		temp = temp * 10 + str[(*i)++] % 48;
	}
	return temp;
}
double compute(char str[]){
	int len = strlen(str);
	Stack *top1=NULL,*top2=NULL;
	int i = 0;
	while (str[i]){
		if (str[i] >= '0'&&str[i] <= '9'){
			double temp = strToNum(str, &i);
			push(&top1, temp);
		}
		else if ('+' == str[i] || '-' == str[i]){
			push(&top2,str[i++]);
		}
		else if ('*' == str[i]){
			double data1 = strToNum(str, &(++i));
			double result = data1*pop(&top1);
			push(&top1, result);
		}

		else if ('/' == str[i]){
			double data1 = strToNum(str, &(++i));	
			double result = pop(&top1)/1.0/data1;
			push(&top1, result);
		}
	}
	double ans = pop(&top1);
	while (top1){
		if (pop(&top2) == '+'){
			ans += pop(&top1);
		}
		else{
			ans = -ans + pop(&top1);
		}
	}
	return ans;
}
int main(void){
	char str[] = "1*2-3*4/5";
	printf("%0.2lf",compute(str));
	int i = 0;
	system("pause");
	return 0;
}