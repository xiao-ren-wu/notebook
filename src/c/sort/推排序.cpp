void swap(int *a, int *b){
	if (NULL == a || NULL == b){
		return;
	}
	int temp = *a;
	*a = *b;
	*b = temp;
}
void makeAHeap(int data[], int len){
	int pa, tag = 1, end = len;
	while (tag){
		pa = end / 2;
		tag = 0;
		while (pa){
			if (data[pa] < data[2 * pa]){
				swap(&data[pa], &data[2 * pa]);
				tag = 1;
			}
			if (2 * pa + 1 <= end&&data[pa] < data[2 * pa + 1]){
				swap(&data[pa], &data[2 * pa + 1]);
				tag = 1;
			}
			pa--;
		}
	}
}
void heapSort(int data[], int len){
	int end = len;
	while (end-1){
		makeAHeap(data, end);	
		swap(&data[1], &data[end--]);
	}
}