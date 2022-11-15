package level2.숫자_카드_나누기;

import java.util.*;

public class Solution {
    public static int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;

        int minA = Arrays.stream(arrayA).min().getAsInt();
        int minB = Arrays.stream(arrayB).min().getAsInt();

        Set<Integer> set = new HashSet<>();

        for(int i = 2; i <= minA; i++) {
            if(minA % i == 0) set.add(i);
        }

        for(int i = 2; i <= minB; i++) {
            if(minB % i == 0) set.add(i);
        }

        List<Integer> list = new ArrayList<>(set);

        System.out.println(list);

        boolean isPossible;
        for (Integer integer : list) {
            isPossible = true;
            for (int j = 0; j < arrayA.length; j++) {
                if (arrayA[j] % integer != 0 || arrayB[j] % integer == 0) {
                    isPossible = false;
                    break;
                }
            }

            if (isPossible) answer = Math.max(answer, integer);

            isPossible = true;

            for (int j = 0; j < arrayB.length; j++) {
                if (arrayB[j] % integer != 0 || arrayA[j] % integer == 0) {
                    isPossible = false;
                    break;
                }
            }

            if (isPossible) answer = Math.max(answer, integer);
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{10, 20}, new int[]{5, 17}));
    }
}
