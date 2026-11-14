package Recursion;

public class Practice {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 2, 1};
        System.out.println(checkPalindromeOrNot(nums, 0, nums.length-1));
    }

    public static boolean checkPalindromeOrNot(int[] nums, int l, int r) {
        //base case
        if(l >= r) return true;

        if(nums[l] != nums[r]) return false;

        return checkPalindromeOrNot(nums, l+1, r-1);
    }
   

    public static boolean checkOnlyDigit(String s, int i) {
        //base case
        if(i >= s.length()) return true;

        if(!Character.isDigit(s.charAt(i))) return false;

        return checkOnlyDigit(s, i+1);
    }

    public static int findLastIndexOfTarget(int[] nums, int target, int i) {
        //base case
        if(i < 0) return -1;

        if(nums[i] == target) return i;

        return findLastIndexOfTarget(nums, target, i-1);
    }

    public static int findFirstIndexOfTarget(int[] nums, int target, int i) {
        //base case
        if(i >= nums.length) return -1;

        if(nums[i] == target) return i;

        return findFirstIndexOfTarget(nums, target, i+1);
    }

    public static boolean checkPresentOrNot(int[] nums, int target, int i) {
        //base case
        if(i >= nums.length) return false;

        if(nums[i] == target) return true;

        return checkPresentOrNot(nums, target, i+1);
    }

    public static int findOccurance(int[] nums, int target, int i, int count) {
        //base case
        if(i >= nums.length) return count;

        if(nums[i] == target) count++;

        return findOccurance(nums, target, i+1, count);
    }

    public static int findSumOfArray(int[] nums, int i, int sum) {
        //base case
        if(i >= nums.length) return sum;

        sum += nums[i];

        return findSumOfArray(nums, i+1, sum);
    }

    public static int findMaxi(int[] nums, int i, int ans) {
        //base case
        if(i >= nums.length) return ans;

        ans = Math.max(ans, nums[i]);

        return findMaxi(nums, i+1, ans);
    }

    public static boolean checkSortOrNot(int[] nums, int i, int j) {
        //base case
        if(i >= nums.length || j >= nums.length) {
            return true;
        }

        if(nums[i] > nums[j]) return false;

        return checkSortOrNot(nums, i+1, j+1);
    }

    public static int solveFibonacci(int n) {
        //base case
        if(n == 0 || n == 1) return n;

        return solveFibonacci(n-1) + solveFibonacci(n-2);
    }

    public static int findPower(int base, int exponent) {
        //base case
        if(exponent == 0) return 1;

        return base * findPower(base, exponent-1);
    }

    public static int findCountOfDigit(int n, int count) {
        //base case
        if(n <= 0) return count;

        count++;

        return findCountOfDigit(n/10, count);
    }

    public static boolean checkPalindrome(String s, int l, int r) {
        //base case
        if(l >= r) return true;

        if(s.charAt(l) != s.charAt(r)) return false;

        return checkPalindrome(s, l+1, r-1);
    }

    public static void reverseArray(int[] nums, int l, int r) {
        //base case
        if(l >= r) {
            return;
        }

        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;

        reverseArray(nums, l+1, r-1);
    }

    public static int solveFactorial(int n) {
        //base case
        if(n <= 1) return 1;

        return n * solveFactorial(n-1);
    }

    public static int sumOfNnumber(int n) {
        //base case
        if(n <= 0) return 0;

        return n + sumOfNnumber(n-1);
    }

    public static void solve1(int n) {
        //base case
        if(n <= 0) return;

        System.out.print(n + " ");

        solve1(n-1);
    }

    public static void solve(int i, int n) {
        //base case
        if(i > n) {
            return;
        }

        System.out.print(i + " ");
        
        solve(i+1, n);
    }
}
