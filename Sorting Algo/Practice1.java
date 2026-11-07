public class Practice1 {
    public static void main(String[] args) {
        
        int[] nums = {5, 3, 8, 4, 6};
        int n = 5;

        insertionSort(nums, n);

        for(int i=0; i<n; i++) {
            System.out.print(nums[i] + " ");
        }

    }

    public static void bubbleSort(int[] nums, int n) {

        for(int i=0; i<n-1; i++) {
            for(int j=i; j<n-i-1; j++) {
                if(nums[j] > nums[j+1]) {
                    int temp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = temp;
                }
            }
        }
    }

    public static void selectionSort(int[] nums, int n) {

        for(int i=0; i<n-1; i++) {
            int minIdx = i;

            for(int j=i+1; j<n; j++) {
                if(nums[j] < nums[minIdx]) {
                    minIdx = j;
                }
            }

            int temp = nums[i];
            nums[i] = nums[minIdx];
            nums[minIdx] = temp;
        }

    }

    public static void insertionSort(int[] nums, int n) {

        for(int i=1; i<n; i++) {
            int curr = nums[i];
            int prev = i-1;

            while(prev >=0 && nums[prev] > curr) {
                nums[prev+1] = nums[prev];
                prev--;
            }

            nums[prev+1] = curr;
        }

    }

}
