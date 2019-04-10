/**
 *	给定n对括号，编写一个函数用来生成良好定义的括号的所有组合
 *	例如：给定n=3,
 *	输出如下：
 *	( ( ( ) ) )
 *	( ( ) ( ) )
 *	( ( ) ) ( )
 *	( ) ( ( ) )
 *	( ) ( ) ( )
 */
#include<stdio.h>
#include<stdlib.h>

typedef struct stack {
	char data;
	struct stack *next;
	struct stack *pa;
}Stack;

//入栈函数
void push(Stack **top,char str){
	if (top == NULL) {
		return;
	}
	//为入栈节点分配单元
	Stack *pnew = (Stack*)malloc(sizeof(Stack));
	//初始化入栈节点
	pnew->data = str;
	pnew->next = pnew->pa = NULL;
	if (*top == NULL) {
		*top = pnew;
	}
	else {
		(*top)->next = pnew;
		pnew->pa = *top;
		*top = (*top)->next;
	}
}
//出栈函数
char pop(Stack **top) {
	if (top == NULL||(*top)==NULL) {
		return NULL;
	}
	//获取出栈节点
	Stack *pdel = *top;
	//获取出栈元素
	char str = pdel->data;
	(*top) = (*top)->pa;
	if (*top) {
		(*top)->next = NULL;
	}
	free(pdel);
	return str;
}
//打印栈
void print(Stack **top) {
	if (*top) {
		print(&((*top)->pa));
		printf("%c ",(*top)->data);
	}
}
void compute(int leftNum, int rightNum,int n,Stack **top) {
	if (rightNum == n) {
		print(top);
		printf("\n");
	}
	if (leftNum < n) {
		push(top, '(');
		compute(leftNum + 1, rightNum, n, top);
		pop(top);
	}
	if (rightNum < leftNum) {
		push(top, ')');
		compute(leftNum, rightNum + 1, n, top);
		pop(top);
	}
}
int main(void) {
	Stack *top = NULL;
	compute(0, 0, 3, &top);
	system("pause");
	return 0;
}
