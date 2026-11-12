public class MergeSort {

    public static void merge(int[] nums, int s, int mid, int e) {
        int[] temp = new int[e-s+1];
        int l = s, r = mid+1, k = 0;

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
            nums[i+s] = temp[i];
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
    
    public static void main(String[] args) {
        int[] nums = {3, 1, 2, 4, 1, 5, 2, 6, 4};
        int n = 9;

        mergeSort(nums, 0, n-1);

        for(int num : nums) {
            System.out.print(num + " ");
        }
    }
}
