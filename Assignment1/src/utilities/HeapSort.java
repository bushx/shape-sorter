package utilities;

import java.util.Comparator;

/**
 * Reference: Stranger's LAB (JAVA - Heap Sort: Bottom-Up Type)
 * https://st-lab.tistory.com/225
 */

public class HeapSort {
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
		if(array.length < 2) {
			return;
		}
		
		int parentIndex = (array.length - 2) / 2;
		
		for(int i = parentIndex; i >= 0; i--) {
			heapify(array, i, array.length - 1, compareValue);
		}
		
		for(int i = array.length - 1; i > 0; i--) {
			swap(array, 0, i);
			heapify(array, 0, i - 1, compareValue);
		}
	}

	private static <T> void compare(T[] array, Comparator<? super T> comparator, int compareValue) {
		if(array.length < 2) {
			return;
		}
		
		int parentIndex = (array.length - 2) / 2;
		
		for(int i = parentIndex; i >= 0; i--) {
			heapify(array, i, array.length - 1, comparator, compareValue);
		}
		
		for(int i = array.length - 1; i > 0; i--) {
			swap(array, 0, i);
			heapify(array, 0, i - 1, comparator, compareValue);
		}
	}
	
	private static <T extends Comparable<T>> void heapify(
			T[] array, int parentIndex, int lastIndex, int compareValue) {
		int leftChildIndex;
		int rightChildIndex;
		int targetIndex;
		
		while((parentIndex * 2) + 1 <= lastIndex) {
			leftChildIndex = (parentIndex * 2) + 1;
			rightChildIndex = (parentIndex * 2) + 2;
			targetIndex = parentIndex;
			
			if(array[leftChildIndex].compareTo(array[targetIndex]) == compareValue) {
				targetIndex = leftChildIndex;
			}
			
			if((rightChildIndex <= lastIndex) &&
					(array[rightChildIndex].compareTo(array[targetIndex]) == compareValue)) {
				targetIndex = rightChildIndex;
			}
			
			if(targetIndex != parentIndex) {
				swap(array, parentIndex, targetIndex);
				parentIndex = targetIndex;
			} else {
				return;
			}
		}
	}
	
	private static <T> void heapify(
			T[] array, int parentIndex, int lastIndex, Comparator<? super T> comparator, int compareValue) {
		int leftChildIndex;
		int rightChildIndex;
		int targetIndex;
		
		while((parentIndex * 2) + 1 <= lastIndex) {
			leftChildIndex = (parentIndex * 2) + 1;
			rightChildIndex = (parentIndex * 2) + 2;
			targetIndex = parentIndex;
			
			if(comparator.compare(array[leftChildIndex], array[targetIndex]) == compareValue) {
				targetIndex = leftChildIndex;
			}
			
			if((rightChildIndex <= lastIndex) &&
					(comparator.compare(array[rightChildIndex], array[targetIndex]) == compareValue)) {
				targetIndex = rightChildIndex;
			}
			
			if(targetIndex != parentIndex) {
				swap(array, parentIndex, targetIndex);
				parentIndex = targetIndex;
			} else {
				return;
			}
		}
	}

	private static <T> void swap(T[] array, int i, int j) {
		T temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}