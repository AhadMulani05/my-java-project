package Recursion;

import java.util.ArrayList;
import java.util.List;

public class Practice1 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 1};
        int k = 2;
        int n = 3;
        List<Integer> ans = new ArrayList<>();
        System.out.println(findCountOfK(arr, 0, 0, 0, n, k));
    }

    public static int findCountOfK(int[] arr, int count, int i, int sum, int n, int k) {
        //base case
        if(i == n) {
            if(sum == k) return 1;
            return 0;
        }

        sum += arr[i];

        int left = findCountOfK(arr, count, i+1, sum, n, k);

        sum -= arr[i];

        int right = findCountOfK(arr, count, i+1, sum, n, k);

        return left + right;
    }

    public static void findSumOfSubsequence(int[] arr, List<Integer> ans, int i, int sum, int n, int target) {
        //base case
        if(i == n) {
            if(sum == target) System.out.println(ans);
            return ;
        }

        ans.add(arr[i]);
        sum += arr[i];
        
        findSumOfSubsequence(arr, ans, i+1, sum, n, target);

        ans.remove(ans.size() - 1);
        sum -= arr[i];
        
        findSumOfSubsequence(arr, ans, i+1, sum, n, target);
    }

    public static void findSubsequence(int[] arr, List<Integer> ans, int i, int n) {
        //base case
        if(i == n) {
            System.out.println(ans);
            return ;
        }

        findSubsequence(arr, ans, i+1, n);

        ans.add(arr[i]);

        findSubsequence(arr, ans, i+1, n);

        ans.remove(arr[i]);
    }
}
