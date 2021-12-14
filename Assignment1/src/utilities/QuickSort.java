package utilities;

import java.util.Comparator;

/**
 * Reference: Big Java: Early Objects, Interactive Edition (p.653)
 * https://bookshelf.vitalsource.com/reader/books/9781119141594/epubcfi/6/
 * 760[%3Bvnd.vst.idref%3Dbjeo6_ch14-7]!/4[bjeo6_ch14-7]/2/96[code_cnt_6]/8[table022]/2/4
 */

public class QuickSort {	
	public static <T extends Comparable<T>> void ascendingSort(T[] array) {
		compare(array, 0, array.length - 1, -1);
	}

	public static <T> void ascendingSort(T[] array, Comparator<? super T> comparator) {
		compare(array, 0, array.length - 1, comparator, -1);
	}
	
	public static <T extends Comparable<T>> void descendingSort(T[] array) {
		compare(array, 0, array.length - 1, 1);
	}

	public static <T> void descendingSort(T[] array, Comparator<? super T> comparator) {
		compare(array, 0, array.length - 1, comparator, 1);
	}
	
	private static <T extends Comparable<T>> void compare(
			T[] array, int from, int to, int compareValue) {
		if(from >= to) {
			return;
		}
		
		int pivot = partition(array, from, to, compareValue);
		
		compare(array, from, pivot, compareValue);
		compare(array, pivot + 1, to, compareValue);
	}
	
	private static <T> void compare(
			T[] array, int from, int to, Comparator<? super T> comparator, int compareValue) {
		if(from >= to) {
			return;
		}
		
		int pivot = partition(array, from, to, comparator, compareValue);
		
		compare(array, from, pivot, comparator, compareValue);
		compare(array, pivot + 1, to, comparator, compareValue);
	}
	
	private static <T extends Comparable<T>> int partition(
			T[] array, int from, int to, int compareValue) {
		T pivot = array[from];
		int left = from - 1;
		int right = to + 1;
		
		while(left < right) {
			left++;
			
			while(array[left].compareTo(pivot) == compareValue) {
				left++;
			}
			
			right--;
			
			while(array[right].compareTo(pivot) == compareValue * -1) {
				right--;
			}
			
			if(left < right) {
				swap(array, left, right);
			}
		}
		
		return right;
	}
	
	private static <T> int partition(
			T[] array, int from, int to, Comparator<? super T> comparator, int compareValue) {
		T pivot = array[from];
		int left = from - 1;
		int right = to + 1;
		
		while(left < right) {
			left++;
			
			while(comparator.compare(array[left], pivot) == compareValue) {
				left++;
			}
			
			right--;
			
			while(comparator.compare(array[right], pivot) == (compareValue * -1)) {
				right--;
			}
			
			if(left < right) {
				swap(array, left, right);
			}
		}
		
		return right;
	}
	
	private static <T> void swap(T[] array, int i, int j) {
		T temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}