package sort;

import java.util.Arrays;

public class SelectSort {
	
	public static void selectSort(int[] numbers) {
		int size = numbers.length;
		int temp = 0;
		
		for (int i = 0; i < size - 1; i ++) {
			int tempMin = i;
			for (int j = size - 1; j > i; j --) {
				if (numbers[j] < numbers[tempMin]) {
					tempMin = j;
				}
			}
			temp = numbers[tempMin];
			numbers[tempMin] = numbers[i];
			numbers[i] = temp;
		}
	}

	public static void main(String[] args) {
		int[] numbers = {12, 32, 18, 25, 45, 3, 25, 10, 76, 33, 75};
		if (numbers.length > 0) {
			selectSort(numbers);
		}
		System.out.println(Arrays.toString(numbers));
	}
}
