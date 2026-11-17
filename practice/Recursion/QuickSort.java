package Recursion;

public class QuickSort {
    public static void main(String[] args) {
        int[] nums = {5, 1, 4, 2, 3};
        int n = nums.length;

        quickSort(nums, 0, n-1);

        for(int num : nums) {
            System.out.print(num + " ");
        }
    }

    public static void quickSort(int[] nums, int s, int e) {
        if(s < e) {
            int pivotIdx = solvePartition(nums, s, e);

            quickSort(nums, s, pivotIdx-1);
            quickSort(nums, pivotIdx+1, e);
        }
    }
    
    public static int solvePartition(int[] nums, int s, int e) {
        int pivot = nums[s];

        int i = s, j = e;

        while(i < j) {
            while(i<j && nums[i] <= pivot) i++;
            while(i<j && nums[j] > pivot) j--;

            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

        int temp = nums[s];
        nums[s] = nums[j];
        nums[j] = temp;

        return j;
    }
    
}
