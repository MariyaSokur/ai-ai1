import java.io.*;
import java.net.URL;

/**
 * Created by Mariya on 17.09.2017.
 */
public class Main {

    public static void main(String[] args){
        Environment env = new Environment();
        env.loadMap();
        SimpleAgent sa = new SimpleAgent(env);
        sa.search();
        AgentWithModel am = new AgentWithModel(env);
        am.search();
    }
}
