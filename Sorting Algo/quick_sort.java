import java.util.Arrays;

public class quick_sort {

    public static int solve(int[] arr, int s, int e) {
        int pivot = arr[e];
        int i = s-1;

        for(int j=s; j<e; j++) {
            if(arr[j] < pivot) {
                i++;

                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i+1];
        arr[i+1] = arr[e];
        arr[e] = temp;

        return i+1;
    }

    public static void quickSort(int[] arr, int s, int e) {
        if(s < e) {
            int pivotIdx = solve(arr, s, e);

            quickSort(arr, s, pivotIdx-1);
            quickSort(arr, pivotIdx+1, e);
        }
    }
    public static void main(String[] args) {
        int[] arr = {12,31,35,8,32,17};

        quickSort(arr, 0, arr.length-1);

        System.out.println(Arrays.toString(arr));
    } 
}
