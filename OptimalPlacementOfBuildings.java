// Time Complexity : O(HW * HW C n)
//HW C n is the number of total combinations given H*W matrix with n buildings
//H*W is for each combination find the maximum distance of parking lot
// Space Complexity : O(N/k)
// Did this code successfully run on Leetcode : Yes

import java.util.LinkedList;
import java.util.Queue;

public class OptimalPlacementOfBuildings {
    // "static void main" must be defined in a public class.
        public static void main(String[] args) {
            BuildingPlacement buildingPlacement = new BuildingPlacement();
            System.out.println(buildingPlacement.findMinDistance(7,7,3));
        }

        public static class BuildingPlacement{

            int H,W;
            int result;

            public int findMinDistance(int h, int w, int n){
                this.H = h;
                this.W= w;
                result = Integer.MAX_VALUE;
                int[][] grid = new int[h][w];
                for(int i=0; i<h; i++){
                    for(int j=0; j<w; j++){
                        grid[i][j] = -1;
                    }
                }
                // place the buildings
                helper(grid, 0, n);
                return result;
            }

            private void helper(int[][] grid, int pivot, int n){
                if(n == 0){
                    // calculate the distance of the empty parking lot from the building
                    bfs(grid);
                    return;
                }
                for(int i=pivot; i<H*W; i++){

                    int r = i/W;
                    int c = i%W;

                    grid[r][c] = 0;  //action
                    helper(grid,i+1, n-1); // recurse
                    grid[r][c] = -1; //backtrack;
                }
            }

            private void bfs(int[][] grid){
                int m = grid.length;
                int n = grid[0].length;
                int[][] dirs = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
                boolean[][] visited = new boolean[H][W];
                Queue<int[]> q = new LinkedList<>();
                for(int i=0; i<m; i++){
                    for(int j=0; j<n; j++){
                        if(grid[i][j] == 0){
                            q.add(new int[]{i,j});
                            visited[i][j] = true;
                        }
                    }
                }
                int dist = 0;
                while(!q.isEmpty()){
                    int size = q.size();
                    for(int i=0; i<size; i++){
                        int[] curr = q.poll();
                        for(int[] dir: dirs){
                            int r = dir[0] + curr[0];
                            int c = dir[1] + curr[1];

                            if(r>=0 && c>=0 && r<H && c<W && grid[r][c]==-1 && !visited[r][c]){
                                q.add(new int[]{r,c});
                                visited[r][c] = true;
                            }
                        }
                    }
                    dist++;
                }
                result = Math.min(result,dist-1);
            }
        }
    }

