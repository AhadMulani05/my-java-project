public class Practice {
    public static void main(String[] args) {
        System.out.println("Bubble Sort:- ");
        bubbleSort();

        System.out.println("Selection Sort:- ");
        selectionSort();

        System.out.println("Insertion Sort:- ");
        insertionSort();

        System.out.println("Merge Sort:- ");
        mergeSort();

        System.out.println("Quick Sort:- ");
        quickSort();
    }

    public static void bubbleSort() {
        int[] nums = {4,6,2,5,7,8,1,3};
        int n = 8;

        for(int i=0; i<n-1; i++) {
            for(int j=0; j<n-i-1; j++) {
                if(nums[j] > nums[j+1]) {
                    int temp = nums[j];
                    nums[j] = nums[j+1]; 
                    nums[j+1] = temp;
                }
            }
        }

        for(int num : nums) {
            System.out.print(num + " ");
        }

        System.out.println();
    }

    public static void selectionSort() {
        int[] nums = {4,6,2,5,7,8,1,3};
        int n = 8;

        for(int i=0; i<n-1; i++) {
            int minIdx = i;

            for(int j=i; j<n; j++) {
                if(nums[j] < nums[minIdx]) {
                    minIdx = j;
                }
            }

            int temp = nums[i];
            nums[i] = nums[minIdx];
            nums[minIdx] = temp; 
        }

        for(int num : nums) {
            System.out.print(num + " ");
        }
        
        System.out.println();
    }

    public static void insertionSort() {
        int[] nums = {4,6,2,5,7,8,1,3};
        int n = 8;

        for(int i=1; i<n; i++) {
            int curr = nums[i];
            int prev = i-1;

            while(prev >= 0 && nums[prev] > curr) {
                nums[prev+1] = nums[prev];
                prev--;
            }

            nums[prev+1] = curr;
        }

        for(int num : nums) {
            System.out.print(num + " ");
        }

        System.out.println();
    }

    public static void mergeSort() {
        int[] nums = {4,6,2,5,7,8,1,3};
        int n = 8;

        solveMergeSort(nums, 0, n-1);

        for(int num : nums) {
            System.out.print(num + " ");
        }

        System.out.println();
    }

    public static void solveMergeSort(int[] nums, int s, int e) {
        if(s < e) {
            int mid = s+(e-s)/2;

            solveMergeSort(nums, s, mid);
            solveMergeSort(nums, mid+1, e);

            merge(nums, s, mid, e);
        }
    }

    public static void merge(int[] nums, int s, int mid, int e) {
        int[] temp = new int[e-s+1];
        
        int i = s, j = mid+1, k = 0;

        while(i <= mid && j <= e) {
            if(nums[i] <= nums[j]) {
                temp[k++] = nums[i++];
            }
            else {
                temp[k++] = nums[j++];
            }
        }

        while(i <= mid) {
            temp[k++] = nums[i++];
        }
        
        while(j <= e) {
            temp[k++] = nums[j++];
        }

        for(int idx=0; idx<temp.length; idx++) {
            nums[idx + s] = temp[idx];
        }
    }

    public static void quickSort() {
        int[] nums = {4,6,2,5,7,8,1,3};
        int n = 8;

        solveQuickSort(nums, 0, n-1);

        for(int num : nums) {
            System.out.print(num + " ");
        }

        System.out.println();
    }

    public static void solveQuickSort(int[] nums, int s, int e) {
        if(s < e) {
            int pIdx = solvePartition(nums, s, e);

            solveQuickSort(nums, s, pIdx-1);
            solveQuickSort(nums, pIdx+1, e);
        }
    }

    public static int solvePartition(int[] nums, int s, int e) {
        int pivot = nums[s];

        int i = s, j = e;

        while(i < j) {
            while(nums[i] <= pivot) i++;
            while(nums[j] > pivot) j--;

            if(i < j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }

        int temp = nums[s];
        nums[s] = nums[j];
        nums[j] = temp;

        return j;
    }
}