package sort;

import java.util.Arrays;

public class InsertSort {
	
	public static void insertSort(int[] numbers) {
		int size = numbers.length;
		int temp = 0;
		int j = 0;
		
		for (int i = 0; i < size; i ++) {
			temp = numbers[i];
			for (j = i; j > 0 && temp < numbers[j - 1]; j --) {
				numbers[j] = numbers[j - 1];
			}
			numbers[j] = temp;
		}
	}
	

	public static void main(String[] args) {
		int[] numbers = {12, 32, 18, 25, 45, 3, 25, 10, 76, 33, 75};
		if (numbers.length > 0) {
			insertSort(numbers);
		}
		System.out.println(Arrays.toString(numbers));
	}
}
