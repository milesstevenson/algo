public class Solution {
    /**
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number 
     *          and the index of the last number
     */
    public ArrayList<Integer> subarraySum(int[] nums) {
        if (nums.length == 0 || (nums.length == 1 && nums[0] != 0))
            return new ArrayList<>();
            
        for (int i = 0; i < nums.length; i++) {
            int sum = nums[i];
            if (sum == 0) {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i); list.add(i);
                return list;
            }
            
            for (int j = i+1; j < nums.length; j++) {
                sum += nums[j];
                if (sum == 0) {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(i); list.add(j);
                    return list;
                }
            }
        }
        
        return new ArrayList<>();
    }
}
