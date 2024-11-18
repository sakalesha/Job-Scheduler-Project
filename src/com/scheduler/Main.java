import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Create Instances
        Scheduler scheduler = new Scheduler();
        TaskManager taskManager = new TaskManager(scheduler);

        // Create jobs
        Job job1 = new Job(1, 3, 50, 30);
        Job job2 = new Job(2, 1, 20, 10);
        Job job3 = new Job(3, 2, 40, 20);
        Job job4 = new Job(4, 4, 60, 40);

        // Add dependencies (job1 depends on job2 and job3)
        job1.addDependency(job2);
        job1.addDependency(job3);

        // Add jobs to the scheduler
        taskManager.addJobWithDependencies(job1, job1.getDependencies());
        taskManager.addJobWithDependencies(job2, new ArrayList<>());
        taskManager.addJobWithDependencies(job3, new ArrayList<>());
        taskManager.addJobWithDependencies(job4, new ArrayList<>());

        // Start scheduling
        taskManager.startScheduling();
    }
}

/*
task manager --> to add job and dependencies to job
scheduler --> to schedule(according to priorty) exutution of jobs
job --> create structure of job
 */