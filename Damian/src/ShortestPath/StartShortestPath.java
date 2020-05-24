package ShortestPath;


public class StartShortestPath {
    public static void main(String[] args) {
        String pathMaze;
        ShortestPath maze;
        pathMaze = "./Damian/resources/ShortestPath/TC1.txt";
        maze = new ShortestPath(pathMaze);
        System.out.println(maze.checkPath());
    }
}
