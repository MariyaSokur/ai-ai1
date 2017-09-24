import java.io.*;
import java.net.URL;
import java.util.Random;

/**
 * Created by Mariya on 17.09.2017.
 */
public class Environment {

    int[][] map = new int[10][10];
    int[][] init_map = new int[10][10];
    int startI;
    int startJ;
    int probability;

    public void loadMap(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        File f = new File("maps/map1.txt");
        BufferedReader fin = null;
        try {
            fin = new BufferedReader(new FileReader(f));
            String line = null;
            int i = 0;
            while ((line = fin.readLine()) != null) {
                if(i==0){
                    String[] startPosition = line.split(" ");
                    startI = Integer.parseInt(startPosition[0]);
                    startJ = Integer.parseInt(startPosition[1]);
                    probability = Integer.parseInt(startPosition[2]);
                }
                else {
                    //System.out.println(line);
                    String[] maps = line.split(" ");
                    for (int j = 0; j < maps.length; j++) {
                        map[i-1][j] = Integer.parseInt(maps[j]);
                        init_map[i-1][j] = Integer.parseInt(maps[j]);
                    }
                }
                i++;
            }
            //print();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setInit_map(){
        for(int i=0; i< map.length; i++)
            for (int j = 0; j < map[0].length; j++)
                map[i][j] = init_map[i][j];
    }

    public void print(){
        for(int i=0; i< map.length; i++) {
            for (int j = 0; j < map[0].length; j++)
                System.out.print(map[i][j]);
            System.out.print('\n');
        }
    }

    public int countDirty(){
        int sum = 0;
        for(int i=0; i<map.length;i++)
            for(int j=0; j<map[0].length;j++)
                if(map[i][j]==1)
                    sum+=1;
        return sum;
    }

    public void generateDirt(){
        for(int i=0; i< map.length; i++) {
            for (int j = 0; j < map[0].length; j++)
                if(map[i][j] == 0){
                    Random random = new Random();
                    int action = random.nextInt(probability);
                    if(action == 1) map[i][j] = 1;
                }
        }
    }
}
