public class Practice2 {
    public static void main(String[] args) {
        System.out.println("Bubble sort:- ");
        bubbleSort();

        System.out.println("Selection sort:- ");
        selectionSort();

        System.out.println("Insertion sort:- ");
        insertionSort();


        System.out.println("Merge sort:- ");
        mergeSort();

        System.out.println();

        System.out.println("Quick sort:- ");
        quickSort();
    }

    public static void bubbleSort() {
        int arr[] = {5,1,4,2,3};
        int n = 5;

        for(int i=0; i<n-1; i++) {
            for(int j=0; j<n-i-1; j++) {
                if(arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }

        for(int num : arr) {
            System.out.print(num + " ");
        }

        System.out.println();
    }


    public static void selectionSort() {
        int[] nums = {5,1,4,2,3};
        int n = 5;

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

        for(int num : nums) {
            System.out.print(num + " ");
        }

        System.out.println();
    }

    public static void insertionSort() {
        int[] arr = {5,1,4,2,3};
        int n = 5;

        for(int i=1; i<n; i++) {
            int curr = arr[i];
            int prev = i-1;

            while(prev >= 0 && arr[prev] > curr) {
                arr[prev+1] = arr[prev];
                prev--;
            }

            arr[prev+1] = curr;
        }

        for(int num : arr) {
            System.out.print(num + " ");
        }

        System.out.println();
    }

    public static void mergeSort() {
        int[] nums = {5,1,4,2,3};
        int n = 5;

        solveMergeSort(nums, 0, n-1);

        for(int num : nums) {
            System.out.print(num+ " ");
        }
    }

    public static void solveMergeSort(int[] nums, int s, int e) {
        //base case
        if(s >= e) return ;

        int mid = s+(e-s)/2;

        solveMergeSort(nums, s, mid);
        solveMergeSort(nums, mid+1, e);

        merge(nums, s, mid, e);
    }

    public static void merge(int[] nums, int s, int mid, int e) {
        int[] temp = new int[e-s+1];
        int l = s, r = mid+1;
        int k = 0;

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

        while (r <= e) {
            temp[k++] =  nums[r++];
        }

        for(int i=s; i<=e; i++) {
            nums[i] = temp[i - s];
        }
    }

    public static void quickSort() {
        int[] nums = {5,1,4,2,3};
        int n = 5;

        solveQuickSort(nums, 0, n-1);

        for(int num : nums) {
            System.out.print(num + " ");
        }
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
            while(nums[i] <= pivot && i < e) i++;
            while(nums[j] > pivot && j >= s) j--;

           if(i < j){
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