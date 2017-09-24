import java.util.Random;

/**
 * Created by Mariya on 22.09.2017.
 */
public class AgentWithModel extends Agent {
    int[][] map  = new int[10][10];
    int lastAction;

    AgentWithModel(Environment env) {
        super(env, env.startI, env.startJ);
    }

    public void search() {
        int[][] times = new int[][]{ {100, 0, 0}, {500, 0, 0},
                {1000, 0, 0}, {2000, 0, 0},
                {5000, 0, 0}, {10000, 0, 0}};
        //time[1] - energy
        //time[2] - dirt
        //time[0] - count of iterations
        for (int[] time : times) {
            env.setInit_map();
            map[i][j] = 1;
            for (int it = 0; it < time[0]; it++) {
                env.generateDirt();

                if (env.map[i][j] == 1) {
                    time[1] += 2;
                    suck();
                    map[i][j] = 1;
                } else {
                    boolean flagAction = true;
                    while(flagAction) {
                        int action = chooseAction();
                        switch (action) {
                            case 0:
                                if(up()) {
                                    flagAction = false;
                                    lastAction = 2;
                                    map[i][j] = 1;
                                }else
                                    map[i-1][j] = 2;
                                time[1] += 1;
                                break;
                            case 1:
                                if(right()) {
                                    flagAction = false;
                                    lastAction = 3;
                                    map[i][j] = 1;
                                }else
                                    map[i][j+1] = 2;
                                time[1] += 1;
                                break;
                            case 2:
                                if(bottom()) {
                                    flagAction = false;
                                    lastAction = 0;
                                    map[i][j] = 1;
                                }else
                                    map[i+1][j] = 2;
                                time[1] += 1;
                                break;
                            case 3:
                                if(left()) {
                                    flagAction = false;
                                    lastAction = 1;
                                    map[i][j] = 1;
                                }else
                                    map[i][j-1] = 2;
                                time[1] += 1;
                        }
                    }
                }
                time[2] += env.countDirty();
               // print();
            }
        }

        for(int i=0; i< times.length; i++) {
            for (int j = 0; j < times[0].length; j++)
                if(j == 0)
                    System.out.print(times[i][j] + " ");
                else
                    System.out.print((double) times[i][j]/(double)times[i][0] + " ");
            System.out.print('\n');
        }

    }

    private int chooseAction(){
        int[] successors = new int[]{map[i-1][j], map[i][j+1], map[i+1][j], map[i][j-1]};
        for(int s=0; s< successors.length; s++) {
            if(successors[s] == 0)
                return s;
        }
        for(int s=0; s< successors.length; s++) {
            if(successors[s] == 1 && s!=lastAction)
                return s;
        }
        return  lastAction;
    }

    private void print(){
        for(int i=0; i< map.length; i++) {
            for (int j = 0; j < map[0].length; j++)
                System.out.print(map[i][j]);
            System.out.print('\n');
        }
        System.out.println("-----------------------------");
    }
}
