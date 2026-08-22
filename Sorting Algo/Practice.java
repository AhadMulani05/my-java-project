import java.util.Arrays;

public class Practice {
    // public static void bubblesort(int[] arr, int n) {
    //     for(int i=0;i<n-1;i++) {
    //         for(int j=0;j<n-i-1;j++) {
    //             if(arr[j] > arr[j+1]) {
    //                 int temp = arr[j];
    //                 arr[j] = arr[j+1];
    //                 arr[j+1] = temp;
    //             }
    //         }
    //     }
    // }

    // public static void selectionSort(int[] arr, int n) {
    //     for(int i=0; i<n-1; i++) {
    //         int minIdx = i;
    //         for(int j=i+1;j<n;j++) {
    //             if(arr[j] < arr[minIdx]) {
    //                 minIdx = j;
    //             }
    //         }

    //         int temp = arr[i];
    //         arr[i] = arr[minIdx];
    //         arr[minIdx] = temp;
    //     }
    // }

    // public static void insertionSort(int[] arr, int n) {
    //     for(int i=1; i<n; i++) {
    //         int curr = arr[i];
    //         int prev = i-1;

    //         while(prev >= 0 && arr[prev] > curr) {
    //             arr[prev+1] = arr[prev];
    //             prev--;
    //         }

    //         arr[prev+1] = curr;
    //     }
    // }

    // public static void solve(int[] arr, int s, int mid, int e) {
    //     int[] temp = new int[e-s+1];
    //     int i = s, j = mid+1, k = 0;

    //     while(i <= mid && j <= e) {
    //         if(arr[i] <= arr[j]) {
    //             temp[k++] = arr[i++];
    //         }
    //         else temp[k++] = arr[j++];
    //     }

    //     while(i <= mid) {
    //         temp[k++] = arr[i++];
    //     }

    //     while(j <= e) {
    //         temp[k++] = arr[j++];
    //     }

    //     for(int idx=0; idx<temp.length; idx++) {
    //         arr[idx+s] = temp[idx];
    //     }
    // }

    // public static void mergeSort(int[] arr, int s, int e) {
    //     if(s < e) {
    //         int mid = s+(e-s)/2;

    //         mergeSort(arr, s, mid);
    //         mergeSort(arr, mid+1, e);

    //         solve(arr, s, mid, e);
    //     }
    // }

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
        //quick sort
        int[] arr = {5,1,4,3,2};
        quickSort(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));



        // //merge sort
        // int[] arr = {5,1,4,2,3};
        // mergeSort(arr, 0, arr.length-1);
        // System.out.println(Arrays.toString(arr));


        // //insertion sort
        // int[] arr = {5,1,4,3,2};
        // int n = 5;

        // insertionSort(arr, n);
        // System.out.println(Arrays.toString(arr));


        //selection sort
        // int[] arr = {5,1,4,3,2};
        // int n = 5;

        // selectionSort(arr, n);
        // System.out.println(Arrays.toString(arr));
        
        // //bubble sort
        // int[] arr = {5,1,4,2,3};
        // int n = arr.length;
        // bubblesort(arr, n);
        // System.out.println(Arrays.toString(arr));
    }
}