import java.util.List;

// Structure of our task
public class Task {
    int id;
    int duration;
    List<Integer> dependencies;
    int est;
    int eft;
    int lst;
    int lft;
    Task(int id, int duration, List<Integer> dependencies) {
        this.id = id;
        this.duration = duration;
        this.dependencies = dependencies;
        this.est = 0;
        this.eft = duration; // Earliest Finish Time is at least duration from start
        this.lst = Integer.MAX_VALUE;
        this.lft = Integer.MAX_VALUE;
    }

}
