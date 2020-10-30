import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

//passed all tests
public class rottenOrange2DArray {
    

    public static int orangesRotting(int[][] grid) {

        Queue<List<Integer>> queue = new ArrayDeque<>();
        int freshOranges = 0;
        int totalMin = -1;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 2){
                    List<Integer> rotten = new ArrayList<Integer>(Arrays.asList(i, j));
                    queue.offer(rotten);
                }else if(grid[i][j] == 1){
                    freshOranges++;
                }
            }
        }
        queue.add(new ArrayList<Integer>( Arrays.asList(-1, -1)));

        while(!queue.isEmpty()){
            List<Integer> temp = queue.poll();
            Integer row = temp.get(0);
            Integer col = temp.get(1);
            if(row == -1){
                totalMin++;
                if(!queue.isEmpty()){
                    queue.offer(new ArrayList<Integer>(Arrays.asList(-1, -1)));
                    continue;
                }
            }
            List<List<Integer>> listOfValidSides = getValidSides(grid, row, col);
            for(List<Integer> iter: listOfValidSides){
                grid[iter.get(0)][iter.get(1)] = 2;
                freshOranges--;
                queue.offer(iter);
            }
            

        }
    
        return freshOranges == 0 ? totalMin: -1;
    }

    public static List<List<Integer>> getValidSides(int[][] grid, int row, int col){
        List<List<Integer>> returnList = new ArrayList<>();
        if(inGrid(row + 1, col, grid) && grid[row + 1][col] == 1){
            List<Integer> downList = new ArrayList<Integer>(Arrays.asList(row + 1, col));
            returnList.add(downList);
        }
        if(inGrid(row - 1, col, grid) && grid[row - 1][col] == 1){
            List<Integer> upList = new ArrayList<Integer>(Arrays.asList(row - 1, col));
            returnList.add(upList);
        }
        if(inGrid(row, col + 1, grid) && grid[row][col + 1] == 1){
            List<Integer> rightList = new ArrayList<Integer>(Arrays.asList(row, col + 1));
            returnList.add(rightList);
        }
        if(inGrid(row, col - 1, grid) && grid[row][col - 1] == 1){
            List<Integer> leftList = new ArrayList<Integer>(Arrays.asList(row, col - 1));
            returnList.add(leftList);
        }
        return returnList;
    }

    public static boolean inGrid(int rowPos, int colPos, int[][] grid){
        return rowPos >= 0 && rowPos < grid.length && colPos >= 0 && colPos < grid[0].length; 
    }


    
}
