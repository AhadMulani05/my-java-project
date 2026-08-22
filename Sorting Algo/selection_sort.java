import java.util.*;

public class selection_sort {

    public static void selectionSort(int[] arr, int n) {
        for(int i=0; i<n-1; i++) {
            int minIdx = i;
            for(int j=i+1; j<n; j++) {
                if(arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }

            int temp = arr[i];
            arr[i] = arr[minIdx];
            arr[minIdx] = temp;
        }
    }
    public static void main(String[] arg) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] arr = new int[n];

        for(int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
        }

        selectionSort(arr, n);

        for(int num : arr) {
            System.out.print(num + " ");
        }
    }
}
