package utilities;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Reference: Big Java: Early Objects, Interactive Edition (p.648)
 * https://bookshelf.vitalsource.com/reader/books/9781119141594/epubcfi/6/
 * 756[%3Bvnd.vst.idref%3Dbjeo6_ch14-5]!/4[bjeo6_ch14-5]/2/56[table013]/2/4
 */

public class MergeSort {
	public static <T extends Comparable<T>> void ascendingSort(T[] array) {
		compare(array, -1);
	}

	public static <T> void ascendingSort(T[] array, Comparator<? super T> comparator) {
		compare(array, comparator, -1);
	}
	
	public static <T extends Comparable<T>> void descendingSort(T[] array) {
		compare(array, 1);
	}

	public static <T> void descendingSort(T[] array, Comparator<? super T> comparator) {
		compare(array, comparator, 1);
	}
	
	private static <T extends Comparable<T>> void compare(T[] array, int compareValue) {
		if(array.length <= 1) {
			return;
		}
		
		T[] first = Arrays.copyOfRange(array, 0, (array.length / 2));
		T[] second = Arrays.copyOfRange(array, first.length, array.length);
		
		compare(first, compareValue);
		compare(second, compareValue);
		merge(first, second, array, compareValue);
	}
	
	private static <T> void compare(T[] array, Comparator<? super T> comparator, int compareValue) {
		if(array.length <= 1) {
			return;
		}
		
		T[] first = Arrays.copyOfRange(array, 0, (array.length / 2));
		T[] second = Arrays.copyOfRange(array, first.length, array.length);
		
		compare(first, comparator, compareValue);
		compare(second, comparator, compareValue);
		merge(first, second, array, comparator, compareValue);
	}
	
	private static <T extends Comparable<T>> void merge(
			T[] first, T[] second, T[] array, int compareValue) {
		int indexOfFirst = 0; 
		int indexOfSecond = 0; 
		int indexOfArray = 0;
		
		while(indexOfFirst < first.length && indexOfSecond < second.length) {
			if(first[indexOfFirst].compareTo(second[indexOfSecond]) == compareValue) {
				array[indexOfArray] = first[indexOfFirst];
				indexOfFirst++;
			} else {
				array[indexOfArray] = second[indexOfSecond];
				indexOfSecond++;
			}
			
			indexOfArray++;
		}
		
		while(indexOfFirst < first.length) {
			array[indexOfArray] = first[indexOfFirst];
			indexOfArray++;
			indexOfFirst++;
		}
		
		while(indexOfSecond < second.length) {
			array[indexOfArray] = second[indexOfSecond];
			indexOfArray++;
			indexOfSecond++;
		}
	}
	
	private static <T> void merge(
			T[] first, T[] second, T[] array, Comparator<? super T> comparator, int compareValue) {
		int indexOfFirst = 0; 
		int indexOfSecond = 0; 
		int indexOfArray = 0;
		
		while(indexOfFirst < first.length && indexOfSecond < second.length) {
			if(comparator.compare(first[indexOfFirst], second[indexOfSecond]) == compareValue) {
				array[indexOfArray] = first[indexOfFirst];
				indexOfFirst++;
			} else {
				array[indexOfArray] = second[indexOfSecond];
				indexOfSecond++;
			}
			
			indexOfArray++;
		}
		
		while(indexOfFirst < first.length) {
			array[indexOfArray] = first[indexOfFirst];
			indexOfArray++;
			indexOfFirst++;
		}
		
		while(indexOfSecond < second.length) {
			array[indexOfArray] = second[indexOfSecond];
			indexOfArray++;
			indexOfSecond++;
		}
	}
}