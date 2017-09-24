/**
 * Created by Mariya on 17.09.2017.
 */
public abstract class Agent {
    public int i = 0;
    public int j = 0;
    Environment env;

    Agent(){}

    public Agent(Environment env, int i, int j){
        this.env = env;
        this.i = i;
        this.j = j;
    }

    //search
    public void search(){};

    //actions
    public boolean right(){
        if(env.map[i][j+1] != 2) {
            j += 1;
            return true;
        }
        return false;
    }

    public boolean left(){
        if(env.map[i][j-1] != 2) {
            j -= 1;
            return true;
        }
        return false;
    }

    public boolean up(){
        if(env.map[i-1][j] != 2) {
            i -= 1;
            return true;
        }
        return false;
    }

    public boolean bottom(){
        if(env.map[i+1][j] != 2) {
            i += 1;
            return true;
        }
        return false;
    }

    public void suck(){
        env.map[i][j] = 0;
    }

    public boolean isDirty(){
        if(env.map[i][j] == 1)
            return true;
        return false;
    }
}

