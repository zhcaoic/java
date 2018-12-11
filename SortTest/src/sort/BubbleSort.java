package sort;

import java.util.Arrays;

public class BubbleSort {

	public static void bubbleSort(int[] numbers) {
		int temp = 0;
		int size = numbers.length;
		for (int i = 0; i < size - 1; i ++) {
			for (int j = 0; j < size - 1 - i; j ++) {
				if (numbers[j] > numbers[j + 1]) {
					temp = numbers[j];
					numbers[j] = numbers[j + 1];
					numbers[j + 1] = temp;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		int[] numbers = {12, 32, 18, 45, 3, 25, 10, 76, 33};
		bubbleSort(numbers);
		System.out.println(Arrays.toString(numbers));
	}

}
