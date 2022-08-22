// Time Complexity : O(mn c k) | O(Exponential)
// Space Complexity : O(MN)

import java.util.LinkedList;
import java.util.Queue;

public class OptimizationOfGridPlacement {
    public static class BuildingPlacement{
        int minDistance = Integer.MAX_VALUE;

        public int getMinDistance() {
            return minDistance;
        }

        public BuildingPlacement(int m, int n, int k) {
            int[][] grid = new int[m][n];
            // placing the building in all combinations
            dfs(grid, 0, 0, k, m, n);
        }
        private void dfs(int[][] grid, int r, int c, int count, int m, int n) {
            if(count == 0) {
                bfs(grid, m, n);
                return;
            }
            if(c == n) {
                r++;
                c=0;
            }
            if(r == m) return;
            for(int i=r;i<m;i++) {
                for(int j=c;j<n;j++) {
                    grid[i][j] = -1;
                    dfs(grid, i, j+1, count-1, m, n);
                    grid[i][j] = 0;
                }
            }
        }
        private void bfs(int[][] grid, int m, int n) {
            boolean[][] visited = new boolean[m][n];
            Queue<int[]> q = new LinkedList<>();
            for(int i=0;i<m;i++){
                for(int j=0;j<n;j++){
                    if(grid[i][j] == -1) {
                        visited[i][j] = true;
                        q.add(new int[]{i, j});
                    }
                }
            }
            int[][] dirs = {{-1,0},{0,-1},{0,1},{1,0}};
            int level = 0;
            while(!q.isEmpty()) {
                int size = q.size();
                for(int i=0;i<size;i++) {
                    int[] curr = q.poll();
                    for(int[] dir: dirs) {
                        int nr = dir[0]+curr[0];
                        int nc = dir[1]+curr[1];
                        if(nr>=0 && nr<m && nc>=0 && nc<n && !visited[nr][nc]){
                            visited[nr][nc] = true;
                            q.add(new int[]{nr, nc});
                        }
                    }
                }
                level++;
            }
            minDistance = Math.min(minDistance, level-1);
        }
    }

    public static void main(String[] args) {
        BuildingPlacement bp = new BuildingPlacement(4,4,3);
        System.out.println(bp.getMinDistance()); // 2
        BuildingPlacement bp1 = new BuildingPlacement(4,4,2);
        System.out.println(bp1.getMinDistance()); // 3
        BuildingPlacement bp2 = new BuildingPlacement(3,2,1);
        System.out.println(bp2.getMinDistance()); // 2

    }
}