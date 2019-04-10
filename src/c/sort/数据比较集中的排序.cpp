/*
	适合数据比较集中，相当于桶排序的衍生
*/
void sort(int data[], int len){
	if (NULL == data || len <= 0){
		return;
	}
	int table[100] = { 0 };
	for (int i = 0; i < len; i++){
		table[data[i]]++;
	}
	int index = 0;
	for (int i = 0; i < 100; i++){
		while (table[i]){
			data[index++] = i;
			table[i]--;
		}
	}
}