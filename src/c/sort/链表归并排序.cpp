//找到链表的中间节点
ElemSN* findMidNode(ElemSN *begin){
	if (NULL == begin){
		return NULL;
	}
	ElemSN *fast = NULL, *slow = NULL;
	slow = begin;
	fast = begin->next;
	while (fast!=NULL){
		fast = fast->next;
		if (fast != NULL){
			fast = fast->next;
			slow = slow->next;
		}
	}
	return slow;
}
//合并两个升序链表
ElemSN* mergeRiseLinkList(ElemSN *begin1, ElemSN *begin2){
	if (begin1 == NULL){
		return begin2;
	}
	if (begin2 == NULL){
		return begin1;
	}
	if (begin1->data < begin2->data){
		begin1->next = mergeRiseLinkList(begin1->next, begin2);
		return begin1;
	}
	else{
		begin2->next = mergeRiseLinkList(begin1, begin2->next);
		return begin2;
	}
}
ElemSN* mergeSort(ElemSN *begin){
	if (begin ==NULL||begin->next==NULL){
		return begin;
	}
	ElemSN *mid = findMidNode(begin);
	ElemSN *left = begin, *right = mid->next;
	mid->next = NULL;
	left = mergeSort(left);
	right = mergeSort(right);
	return mergeRiseLinkList(left,right);
}