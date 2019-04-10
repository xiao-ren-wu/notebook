void arrayMerge(int data[], int low, int mid, int high){
	if (NULL == data || low < 0 || mid <= 0 || high <= 0){
		return;
	}
	int len = high - low;
	int *p = (int*)malloc(sizeof(int)*len);
	int i = low, j = mid, k = 0;
	while (i < mid&&j < high){
		if (data[i] < data[j]){
			  p[k++]=data[i++];
		}
		else{
			  p[k++]=data[j++];
		}
	}
	while (i < mid){
		  p[k++]=data[i++];
	}
	while (j < high){
		  p[k++]=data[j++];
	}
	for (i = 0,j=low; i < k; i++){
		data[j++] = p[i];
	}
	free(p);
}
void mergeSort(int data[], int low, int high){
	if (NULL == data || low < 0 || high <= 0){
		return;
	}
	if (high - low == 1){
		return;
	}
	int mid = (high + low) / 2;
	mergeSort(data, low, mid);
	mergeSort(data, mid, high);
	arrayMerge(data, low, mid, high);
}