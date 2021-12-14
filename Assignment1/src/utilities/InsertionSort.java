package utilities;

import java.util.Comparator;

/**
 * Reference: Big Java: Early Objects, Interactive Edition (p.646)
 * https://bookshelf.vitalsource.com/reader/books/9781119141594/epubcfi/6/
 * 754[%3Bvnd.vst.idref%3Dbjeo6_ch14-4]!/4[bjeo6_ch14-4]/2/138[code_cnt_4]/6/1:34[.ja%2Cva]
 */

public class InsertionSort {
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
		for(int i = 1; i < array.length; i++) {
			T next = array[i];
			int j = i;
			
			while(j > 0 && next.compareTo(array[j - 1]) == compareValue) {
				array[j] = array[j - 1];
				j--;
			}
			
			array[j] = next;
		}
	}
	
	private static <T> void compare(T[] array, Comparator<? super T> comparator, int compareValue) {
		for(int i = 1; i < array.length; i++) {
			T next = array[i];
			int j = i;
			
			while(j > 0 && comparator.compare(next, array[j - 1]) == compareValue) {
				array[j] = array[j - 1];
				j--;
			}
			
			array[j] = next;
		}
	}
}