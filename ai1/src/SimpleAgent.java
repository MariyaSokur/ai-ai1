import java.util.Random;

/**
 * Created by Mariya on 17.09.2017.
 */
public class SimpleAgent extends Agent {

    //public int count = 0;

    SimpleAgent(Environment env) {
        super(env, env.startI, env.startJ);
    }

    @Override
    public void search() {
        int[][] times = new int[][]{ {100, 0, 0}, {500, 0, 0},
                {1000, 0, 0}, {2000, 0, 0},
                {5000, 0, 0}, {10000, 0, 0}};
        for (int[] time : times) {
            env.setInit_map();
            for (int it = 0; it < time[0]; it++) {
                env.generateDirt();

                if (env.map[i][j] == 1) {
                    time[1] += 2;
                    suck();
                } else {
                    Random random = new Random();
                    int action = random.nextInt(4);

                    switch (action) {
                        case 0:
                            up();
                            time[1] += 1;
                            break;
                        case 1:
                            right();
                            time[1] += 1;
                            break;
                        case 2:
                            bottom();
                            time[1] += 1;
                            break;
                        case 3:
                            left();
                            time[1] += 1;
                    }
                }
                time[2] += env.countDirty();
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
}
