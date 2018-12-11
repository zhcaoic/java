package sort;

import java.util.Arrays;

public class QuickSort {

	public static int getMid(int[] numbers, int i, int j) {
		int temp = numbers[i];
		while (i < j) {
			while (i < j && numbers[j] > temp) {
				j --;
			}
			numbers[i] = numbers[j];
			while (i < j && numbers[i] < temp) {
				i ++;
			}
			numbers[j] = numbers[i];
		}
		numbers[i] = temp;
		return i;
	}
	
	public static void quickSort(int[] numbers, int low, int high) {
		if (low < high) {
			int mid = getMid(numbers, low, high);
			quickSort(numbers, low, mid - 1);
			quickSort(numbers, mid + 1, high);
		}
	}
	
	public static void main(String[] args) {
		int[] numbers = {12, 32, 18, 45, 3, 25, 10, 76, 33};
		if (numbers.length > 0) {
			quickSort(numbers, 0, numbers.length - 1);
		}
		System.out.println(Arrays.toString(numbers));

	}

}
