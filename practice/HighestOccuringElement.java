import java.util.HashMap;
import java.util.Map;

public class HighestOccuringElement {
    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 3, 3, 3, 2, 2};

        System.out.println(highestOccurance(nums));
    }

    public static int highestOccurance(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int num : nums) {
            map.put(num, map.getOrDefault(num, 0)+1);
        }

        int maxFreq = 0;
        int ans = 0;

        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(entry.getValue() > maxFreq) {
                maxFreq = entry.getValue();
                ans = entry.getKey();
            }
        }

        return ans;
    }
}