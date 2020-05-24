package ShortestPath;

public class ShortestPath {
    Maze maze;
    XYpoint[] previousSteps = new XYpoint[1000];
    XYpoint[] nextSteps = new XYpoint[1000];
    int stepsDone=0;

    ShortestPath(String mazePath){
        maze = new Maze(mazePath);
    }

    public int checkPath(){
        previousSteps[0] = maze.start;
        while (previousSteps[0] != null){
            stepsDone++;
            if(getNextSteps() == 1){
                return stepsDone;
            }
        }
        return -1;
    }

    private int getNextSteps(){
        int i =0;
        int nextStepCount = 0;
        while(previousSteps[i] != null){
            if (previousSteps[i].x - 1 >=0){
                if(maze.mazeTable[previousSteps[i].x - 1][previousSteps[i].y] == 0){
                    maze.mazeTable[previousSteps[i].x - 1][previousSteps[i].y]--;
                    nextSteps[nextStepCount++] = new XYpoint(previousSteps[i].x - 1, previousSteps[i].y);
                }
                else if(maze.mazeTable[previousSteps[i].x - 1][previousSteps[i].y] == 3){
                    stepsDone++;
                    return 1;
                }
            }

            if (previousSteps[i].x + 1 < maze.widthSize){
                if(maze.mazeTable[previousSteps[i].x + 1][previousSteps[i].y] == 0){
                    maze.mazeTable[previousSteps[i].x + 1][previousSteps[i].y]--;
                    nextSteps[nextStepCount++] = new XYpoint(previousSteps[i].x + 1, previousSteps[i].y);
                }
                else if(maze.mazeTable[previousSteps[i].x + 1][previousSteps[i].y] == 3){
                    stepsDone++;
                    return 1;
                }
            }
            if (previousSteps[i].y - 1 >=0){
                if(maze.mazeTable[previousSteps[i].x][previousSteps[i].y - 1] == 0){
                    maze.mazeTable[previousSteps[i].x][previousSteps[i].y - 1]--;
                    nextSteps[nextStepCount++] = new XYpoint(previousSteps[i].x, previousSteps[i].y - 1);
                }
                else if(maze.mazeTable[previousSteps[i].x][previousSteps[i].y - 1] == 3){
                    stepsDone++;
                    return 1;
                }
            }
            if (previousSteps[i].y + 1 <maze.heightSize){
                if(maze.mazeTable[previousSteps[i].x][previousSteps[i].y + 1] == 0){
                    maze.mazeTable[previousSteps[i].x][previousSteps[i].y + 1]--;
                    nextSteps[nextStepCount++] = new XYpoint(previousSteps[i].x, previousSteps[i].y + 1);
                }
                else if(maze.mazeTable[previousSteps[i].x][previousSteps[i].y +1 ] == 3){
                    stepsDone++;
                    return 1;
                }
            }
            i++;
        }
        previousSteps = nextSteps;
        nextSteps = new XYpoint[1000];
        return 0;
    }


}
