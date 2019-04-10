void swap(int *a, int *b){
	int temp = *a;
	*a = *b;
	*b = temp;
}
//链表快排实现
void quickSort(ElemSN *begin, ElemSN *end){
	//非法输入
	if (NULL == begin){
		return;
	}
	if (begin == end || begin->next == end){
		return;
	}
	ElemSN *key, *i, *j;
	key = i = begin;
	j = i->next;
	while (j!=end){
		if (j->data < key->data){
			i = i->next;
			swap(&(i->data),&(j->data));
		}
		j = j->next;
	}
	swap(&(i->data),&(key->data));
	quickSort(begin, i);
	quickSort(i->next, j);
}