import java.util.*;

public class insertion_sort {

    public static void insertionSort(int[] arr, int n) {
        for(int i=1; i<n; i++) {
            int curr = arr[i];
            int prev = i-1;

            while(prev >= 0 && arr[prev] > curr) {
                arr[prev+1] = arr[prev];
                prev--;
            }

            arr[prev+1] =  curr;
        }
    }
    public static void main(String[] arg) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] arr = new int[n];

        for(int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
        }

        insertionSort(arr, n);

        for(int num : arr) {
            System.out.print(num + " ");
        }
    }
}
