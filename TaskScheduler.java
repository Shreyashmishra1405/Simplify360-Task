import java.util.*;

public class TaskScheduler {
    public static void main(String[] args) {
        // Define tasks and their dependencies
        Map<Integer, Task> tasks = new HashMap<>();
        tasks.put(1, new Task(1, 5, Arrays.asList()));
        tasks.put(2, new Task(2, 3, Arrays.asList(1)));
        tasks.put(3, new Task(3, 2, Arrays.asList(1)));
        tasks.put(4, new Task(4, 4, Arrays.asList(2, 3)));

        // Calculating earliest finish time for each task
        calculateEarliestFinishTimes(tasks);

        // Calculate latest start time for each task
        calculateLatestStartTimes(tasks);

        // setting values of eft and lft
        int earliestCompletion = tasks.values().stream().mapToInt(t -> t.eft).max().orElse(0);
        int latestCompletion = tasks.values().stream().mapToInt(t -> t.lft).max().orElse(0);

        System.out.println("Earliest time all tasks will be completed: " + earliestCompletion);
        System.out.println("Latest time all tasks will be completed: " + latestCompletion);
    }

    private static void calculateEarliestFinishTimes(Map<Integer, Task> tasks) {
        // Compute earliest finish times (EFT) using  topological sorting
        Queue<Task> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        for (Task task : tasks.values()) {
            if (task.dependencies.isEmpty()) {
                task.eft = task.duration;
                queue.add(task);
            }
        }

        while (!queue.isEmpty()) {
            Task task = queue.poll();
            visited.add(task.id);
            for (Task t : tasks.values()) {
                if (t.dependencies.contains(task.id)) {
                    t.est = Math.max(t.est, task.eft);
                    t.eft = t.est + t.duration;
                    if (!visited.contains(t.id)) {
                        queue.add(t);
                    }
                }
            }
        }
    }

    private static void calculateLatestStartTimes(Map<Integer, Task> tasks) {
        int projectDuration = tasks.values().stream().mapToInt(t -> t.eft).max().orElse(0);
        for (Task task : tasks.values()) {
            task.lft = projectDuration;
        }
        // Reverse topological sorting  to compute latest start times (LST)
        Queue<Task> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        for (Task task : tasks.values()) {
            if (task.dependencies.isEmpty()) {
                task.lft = task.eft;
                task.lst = task.lft - task.duration;
                queue.add(task);
            }
        }

        while (!queue.isEmpty()) {
            Task task = queue.poll();
            visited.add(task.id);
            for (Task t : tasks.values()) {
                if (t.dependencies.contains(task.id)) {
                    t.lft = Math.min(t.lft, task.lst);
                    t.lst = t.lft - t.duration;
                    if (!visited.contains(t.id)) {
                        queue.add(t);
                    }
                }
            }
        }
    }
}
