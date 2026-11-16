package Recursion;

import java.util.ArrayList;
import java.util.List;

public class Practice1 {
    public static void main(String[] args) {
        int[] arr = {3, 2, 1};
        int n = 3;
        List<Integer> ans = new ArrayList<>();
        findSubsequence(arr, ans, 0, n);
    }

    public static void findSubsequence(int[] arr, List<Integer> ans, int i, int n) {
        //base case
        if(i == n) {
            System.out.println(ans);
            return;
        }

        findSubsequence(arr, ans, i+1, n);

        ans.add(arr[i]);

        findSubsequence(arr, ans, i+1, n);

        ans.remove(arr[i]);
    }
}
