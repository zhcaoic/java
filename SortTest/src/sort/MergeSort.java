package sort;

import java.util.Arrays;

public class MergeSort {
	
	public static void merge(int[] numbers, int low, int mid, int high) {
		int[] temp = new int[high - low + 1];
		int i = low;
		int j = mid + 1;
		int k = 0;
		
		while (i <= mid && j <= high) {
			if (numbers[i] <= numbers[j]) {
				temp[k ++] = numbers[i ++];
			} else {
				temp[k ++] = numbers[j ++];
			}
		}
		
		while (i <= mid) {
			temp[k ++] = numbers[i ++];
		}
		
		while (j <= high) {
			temp[k ++] = numbers[j ++];
		}
		
		for (int m = 0; m < temp.length; m ++) {
			numbers[low + m] = temp[m];
		}
	}
	
	public static void mergeSort(int[] numbers, int low, int high) {
		int mid = (low + high) / 2;
		if (low < high) {
			mergeSort(numbers, low, mid);
			mergeSort(numbers, mid + 1, high);
			merge(numbers, low, mid, high);
		}
	}

	public static void main(String[] args) {
		int[] numbers = {12, 32, 6, 18, 25, 45, 3, 25, 10, 76, 33, 75};
		int low = 0;
		int high = numbers.length - 1;
		mergeSort(numbers, low, high);
		System.out.println(Arrays.toString(numbers));

	}

}
