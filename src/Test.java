import java.util.ArrayList;
import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		ArrayList<Integer> arr = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			arr.add(sc.nextInt());
		}
		quickSort(0, arr.size() - 1, arr);
		System.out.println(arr.toString());
	}

	private static void quickSort(int l, int r, ArrayList<Integer> arr) {
		if (l >= r)
			return;
		int p = partition(l, r, arr);
		quickSort(l, p - 1, arr);
		quickSort(p + 1, r, arr);
	}

	private static int partition(int l, int r, ArrayList<Integer> arr) {
		int key = arr.get(l);
		while (l < r) {
			while (l < r && arr.get(r) > key) {
				r--;
			}
			arr.set(l, arr.get(r));
			while (l < r && arr.get(l) < key) {
				l++;
			}
			arr.set(r, arr.get(l));
		}
		arr.set(l, key);
		return l;
	}
}