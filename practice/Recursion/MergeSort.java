package Recursion;

public class MergeSort {
    public static void main(String[] args) {
        int[] nums = {5, 1, 4, 2, 3};
        int n = nums.length;
        mergeSort(nums, 0, n-1);

        for(int num : nums) {
            System.out.print(num + " ");
        }
    }

    public static void mergeSort(int[] nums, int s, int e) {
        if(s < e) {
            int mid = s+(e-s)/2;

            mergeSort(nums, s, mid);
            mergeSort(nums, mid+1, e);

            merge(nums, s, mid, e);
        }
    }

    public static void merge(int[] nums, int s, int mid, int e) {
       
        int l = s;
        int r = mid+1;
        int k = 0;

        int[] temp = new int[e-s+1];

        while(l <= mid && r <= e) {
            if(nums[l] <= nums[r]) {
                temp[k++] = nums[l++];
            }
            else {
                temp[k++] = nums[r++];
            }
        }

        while(l <= mid) {
            temp[k++] = nums[l++];
        }
        
        while(r <= e) {
            temp[k++] = nums[r++];
        }

        for(int i=0; i<temp.length; i++) {
            nums[i + s] = temp[i];
        }
    }
}
