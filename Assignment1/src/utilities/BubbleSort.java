package utilities;

import java.util.Comparator;

/**
 * Reference: Stranger's LAB (JAVA - Bubble Sort)
 * https://st-lab.tistory.com/195
 */

public class BubbleSort {
	public static <T extends Comparable<T>> void ascendingSort(T[] array) {
		compare(array, 1);
	}

	public static <T> void ascendingSort(T[] array, Comparator<? super T> comparator) {
		compare(array, comparator, 1);
	}
	
	public static <T extends Comparable<T>> void descendingSort(T[] array) {
		compare(array, -1);
	}

	public static <T> void descendingSort(T[] array, Comparator<? super T> comparator) {
		compare(array, comparator, -1);
	}
	
	private static <T extends Comparable<T>> void compare(T[] array, int compareValue) {
		for(int i = 1; i < array.length; i++) {
			for(int j = 0; j < array.length - i; j++) {
				if(array[j].compareTo(array[j + 1]) == compareValue) {
					swap(array, j, j + 1);
				}
			}
		}
	}
	
	private static <T> void compare(T[] array, Comparator<? super T> comparator, int compareValue) {
		for(int i = 1; i < array.length; i++) {
			for(int j = 0; j < array.length - i; j++) {
				if(comparator.compare(array[j], array[j + 1]) == compareValue) {
					swap(array, j, j + 1);
				}
			}
		}
	}
	
	private static <T> void swap(T[] array, int i, int j) {
		T temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}