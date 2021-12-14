package utilities;

import java.util.Comparator;

/**
 * Reference: Big Java: Early Objects, Interactive Edition (p.637)
 * https://bookshelf.vitalsource.com/reader/books/9781119141594/epubcfi/6/
 * 748[%3Bvnd.vst.idref%3Dbjeo6_ch14-1]!/4[bjeo6_ch14-1]/2/44/3:18[.ja%2Cva]
 */

public class SelectionSort {
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
		for(int i = 0; i < array.length - 1; i++) {
			int position = i;
			
			for(int j = i + 1; j < array.length; j++) {
				if(array[j].compareTo(array[position]) == compareValue) {
					position = j;
				}
			}
			
			swap(array, position, i);
		}
	}
	
	private static <T> void compare(T[] array, Comparator<? super T> comparator, int compareValue) {
		for(int i = 0; i < array.length - 1; i++) {
			int position = i;
			
			for(int j = i + 1; j < array.length; j++) {
				if(comparator.compare(array[j], array[position]) == compareValue) {
					position = j;
				}
			}
			
			swap(array, position, i);
		}
	}
	
	private static <T> void swap(T[] array, int i, int j) {
		T temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}