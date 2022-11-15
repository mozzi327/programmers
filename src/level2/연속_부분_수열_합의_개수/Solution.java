package level2.연속_부분_수열_합의_개수;

import java.util.*;

class Solution {
    public static int solution(int[] elements) {
        HashSet<Integer> resultSet = new HashSet<>();
        int N = elements.length;
        int[] powElements = new int[N * 2];
        for(int i = 0; i < N; i++) {
            powElements[i] = powElements[i + N] = elements[i];
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                resultSet.add(Arrays.stream(powElements, j, j+i).sum());
            }
        }


        return resultSet.size();
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{7,9,1,1,4})); // 18
    }
}
