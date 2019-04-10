/**
 *  洗牌算法：
 *	即证明每个数字在某个位置的概率相等，都为1/n：
 *
 *  解法：
 *  算法的思想是每次从未选中的数字中随机挑选一个加入排列，时间复杂度为O(n)
 *
 *	对于原排列最后一个数字：很显然他在第n个位置的概率是1/n，
 *	在倒数第二个位置概率是[(n-1)/n] * [1/(n-1)] = 1/n，
 *	在倒数第k个位置的概率是[(n-1)/n] * [(n-2)/(n-1)] *...* [(n-k+1)/(n-k+2)] *[1/(n-k+1)] = 1/n
 *	对于原排列的其他数字也可以同上求得他们在每个位置的概率都是1/n。
 */
void Shuffle_FisherYates(int data[], int len){
	if (data == NULL || len <= 0){
		return;
	}
	for (int i = len - 1; i > 0; i--){
		int a = rand() % (i + 1);
		int temp = data[a];
		data[a] = data[i];
		data[i] = temp;
	}
}
