package level2.야간_전술보행;

import java.util.*;

class Solution {
    public static class Node implements Comparable<Node> {
        int startSurveill;
        int endSurveill;
        int workTime;
        int restTime;

        Node(int[] time, int[] scope) {
            this.workTime = time[0];
            this.restTime = time[1];
            this.startSurveill = scope[0];
            this.endSurveill = scope[1];

        }

        @Override
        public int compareTo(Node o) {
            return this.startSurveill - o.endSurveill;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "startSurveill=" + startSurveill +
                    ", endSurveill=" + endSurveill +
                    ", workTime=" + workTime +
                    ", restTime=" + restTime +
                    '}';
        }
    }


    public static int solution(int distance, int[][] scope, int[][] times) {

        PriorityQueue<Node> nodes = new PriorityQueue<>();

        for(int i = 0; i < scope.length; i++) {
            Arrays.sort(scope[i]);
            nodes.add(new Node(times[i], scope[i]));
        }

        System.out.println(nodes);

        int curDist = 0;
        Node curNode = nodes.poll();

        for (; curDist < distance; curDist++) {

            // 화랑이의 위치가 단속 구간 전이라면, continue
            if(curDist < curNode.startSurveill)
                continue;

            // 화랑이가 단속구간을 지나갈 때, 경비원의 시간 사이클만을 가져오기 위한 나머지 연산
            int time = curDist % (curNode.workTime + curNode.restTime);

            // time이 0보다 크고 경비원의 근무 시간 안에 있다면, 발각 당한 것이므로 반복문 중단
            if(0 < time && time <= curNode.workTime)
                break;

            // 화랑이가 해당 경비원의 단속 구간을 벗어났을 경우, 다음 구간을 단속하는 경비원 가져오기
            if(curNode.endSurveill <= curDist && nodes.size() != 0)
                curNode = nodes.poll();
        }

        return curDist;
    }


    public static void main(String[] args) {
        int[][] scope = new int[][]{{3, 4}, {5, 8}};
        int[][] times = new int[][]{{2, 5}, {4, 3}};

        System.out.println(solution(10, scope, times));
    }
}