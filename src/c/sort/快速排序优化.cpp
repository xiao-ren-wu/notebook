/*
1、快速排序的基本思想：
快速排序使用分治的思想，通过一趟排序将待排序列分割成两部分，其中一部分记录的关键字均比另一部分记录的关键字小。
之后分别对这两部分记录继续进行排序，以达到整个序列有序的目的。
2、快速排序的三个步骤：
(1)选择基准：在待排序列中，按照某种方式挑出一个元素，作为 “基准”（pivot）
(2)分割操作：以该基准在序列中的实际位置，把序列分成两个子序列。此时，在基准左边的元素都比该基准小，在基准右边的元素都比基准大
(3)递归地对两个序列进行快速排序，直到序列为空或者只有一个元素。
3、选择基准的方式：
对于分治算法，当每次划分时，算法若都能分成两个等长的子序列时，那么分治算法效率会达到最大。
也就是说，基准的选择是很重要的。选择基准的方式决定了两个分割后两个子序列的长度，进而对整个算法的效率产生决定性影响。
最理想的方法是，选择的基准恰好能把待排序序列分成两个等长的子序列
我们介绍三种选择基准的方法：(3种)
方法(1)：固定位置
思想：取序列的第一个或最后一个元素作为基准
但是，这是一直很不好的处理方法，如果输入序列是随机的，处理时间可以接受的。如果数组已经有序时，此时的分割就是一个非常不好的分割。
因为每次划分只能使待排序序列减一，此时为最坏情况，快速排序沦为起泡排序，时间复杂度为Θ(n^2)。
而且，输入的数据是有序或部分有序的情况是相当常见的。因此，使用第一个元素作为枢纽元是非常糟糕的，为了避免这个情况，就引入了下面两个获取基准的方法。
方法(2)：随机选取基准
引入的原因：在待排序列是部分有序时，固定选取枢轴使快排效率底下，要缓解这种情况，就引入了随机选取枢轴
思想：取待排序列中任意一个元素作为基准。
这是一种相对安全的策略。由于枢轴的位置是随机的，那么产生的分割也不会总是会出现劣质的分割。
在整个数组数字全相等时，仍然是最坏情况，时间复杂度是O(n^2）。实际上，随机化快速排序得到理论最坏情况的可能性仅为1/(2^n）。
所以随机化快速排序可以对于绝大多数输入数据达到O(nlogn）的期望时间复杂度。
一位前辈做出了一个精辟的总结：“随机化快速排序可以满足一个人一辈子的人品需求。”
方法(3)：三数取中（median-of-three）
引入的原因：虽然随机选取枢轴时，减少出现不好分割的几率，但是还是最坏情况下还是O(n^2），要缓解这种情况，就引入了三数取中选取枢轴
分析：最佳的划分是将待排序的序列分成等长的子序列，最佳的状态我们可以使用序列的中间的值，也就是第N/2个数。
可是，这很难算出来，并且会明显减慢快速排序的速度。这样的中值的估计可以通过随机选取三个元素并用它们的中值作为枢纽元而得到。

优化1：当待排序序列的长度分割到一定大小后，使用插入排序
原因：对于很小和部分有序的数组，快排不如插排好。当待排序序列的长度分割到一定大小后，继续分割的效率比插入排序要差，此时可以使用插排而不是快排。
但是三数取中选择枢轴+插排还是不能处理重复数字。
优化2：在一次分割结束后，可以把与Key相等的元素聚在一起，继续下次分割时，不用再对与key相等元素分割
在一次划分后，把与key相等的元素聚在一起，能减少迭代次数，效率会提高不少
具体过程：在处理过程中，会有两个步骤
第一步，在划分过程中，把与key相等元素放入数组的两端
第二步，划分结束后，把与key相等的元素移到枢轴周围
在数组中，如果有相等的元素，那么就可以减少不少冗余的划分。这点在重复数组中体现特别明显啊。
*/

void swap(int *a, int *b){
	int temp = *a;
	*a = *b;
	*b = temp;
}
//三数取中：先找到一个最大值放到data[len-1]的位置，
//之后mid值通过data[low]和data[mid]确定
int midAndSwap(int data[],int low,int high){
	if (NULL == data || low < 0 || high <= 0){
		return 0;
	}
	int mid = (high - low) / 2 + low;
	if (data[low]>data[high - 1]){
		swap(&data[low], &data[high - 1]);
	}
	if (data[mid] > data[high - 1]){
		swap(&data[mid], &data[high - 1]);
	}
	if (data[low] < data[mid]){
		swap(&data[low], &data[mid]);
	}
	return data[low];
}
void insertSort(int data[], int len){
	for (int i = 0; i < len - 1; i++){
		int temp = data[i + 1];
		int j = i;
		while (j >= 0 && data[j]>temp){
			data[j + 1] = data[j];
			j--;
		}
		data[j + 1] = temp;
	}
}

void quickSort(int data[], int low, int high){
	//转换成插入排序数组的长度为（5<len<15）
	if ((high - low) < 2){
		insertSort(data + low, high-low);
		return;
	}

	int left = low;
	int right = high - 1;

	int leftCopy = left;
	int rightCopy = right;

	int key = midAndSwap(data, low, high);
	while (left < right){
		for (; left < high&&data[left] <= key; left++){
			if (key == data[left]){
				swap(&data[left], &data[leftCopy]);
				leftCopy++;
			}
		}
		for (; right>=left&&data[right] >= key; right--){
			if (key == data[right]){
				swap(&data[right], &data[rightCopy]);
				rightCopy--;
			}
		}
		if (left < right){
			int temp = data[left];
			data[left] = data[right];
			data[right] = temp;
		}
	}
	//将枢纽元素移动到中间位置
	left = right;
	while (leftCopy-low){
		swap(&data[--leftCopy], &data[left--]);
	}
	if (right - left){
		right++;
	}
	while (high-1 - rightCopy){
		swap(&data[++rightCopy], &data[right++]);
	}
	quickSort(data, low, left+1);
	quickSort(data, right, high);
}
