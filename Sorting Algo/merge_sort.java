import java.util.Arrays;

public class merge_sort {

    private static void mergeInPlace(int arr[], int s, int mid, int e) {
        int[] temp = new int[e - s + 1];
        int i = s, j = mid+1, k = 0;

        while(i <= mid && j <= e) {
            if(arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            }
            else {
                temp[k++] = arr[j++];
            }
        }

        while(i <= mid) {
            temp[k++] = arr[i++];
        }

        while(j <= e) {
            temp[k++] = arr[j++];
        }

        for(int idx = 0; idx < temp.length; idx++) {
            arr[idx + s] = temp[idx];
        }
    }

    public static void mergeSort(int arr[], int s, int e) {
        if(s < e) {
            int mid = s+(e-s)/2;

            mergeSort(arr, s, mid);
            mergeSort(arr, mid+1, e);

            mergeInPlace(arr, s, mid, e);
        }
    }
    public static void main(String[] args) {
        int[] arr = {38, 27, 43, 3, 9, 82, 10};
        System.out.println("Original: " + Arrays.toString(arr));
        mergeSort(arr, 0, arr.length - 1);
        System.out.println("Sorted: " + Arrays.toString(arr));
    }
}
