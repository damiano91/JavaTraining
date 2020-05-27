package ShortestPath;

public class ShortestPath {
    Maze maze;
    XYpoint[] previousSteps = new XYpoint[2];
    XYpoint[] nextSteps = new XYpoint[5];
    int stepsDone=0;

    ShortestPath(String mazePath){
        maze = new Maze(mazePath);
    }

    public int checkPath(){
        previousSteps[0] = maze.start;
        while (previousSteps[0] != null){
            stepsDone++;
            if(getNextSteps() == 1){
                return ++stepsDone;
            }
        }
        return -1;
    }

    private int getNextSteps(){
        int i =0;
        int nextStepCount = 0;
        while(previousSteps[i] != null){
            for(int dir = -2; dir<=2;dir++) {
                int x = dir % 2;
                int y = dir / 2;
                if (x == 0 && y == 0)continue;
                x += previousSteps[i].x;
                y += previousSteps[i].y;
                if ((y >= maze.heightSize) || (y < 0) ||(x >= maze.widthSize) ||(x < 0)) continue;
                if (maze.mazeTable[x][y] == 0) {
                    maze.mazeTable[x][y]--;
                    nextSteps[nextStepCount++] = new XYpoint(x, y);
                } else if (maze.mazeTable[x][y] == 3) {
                    return 1;

                }
            }
            i++;
        }
        previousSteps = nextSteps;
        nextSteps = new XYpoint[nextStepCount * 4];
        return 0;
    }

}
