import java.util.List;
import java.util.ArrayList;

public class TaskManager {
    private Scheduler scheduler;

    public TaskManager(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    // Add a job with dependencies
    public void addJobWithDependencies(Job job, List<Job> dependencies) {
        // Create a copy of the dependencies list to prevent modification during iteration
        List<Job> dependenciesCopy = new ArrayList<>(dependencies);
        
        // Iterate over the copied list of dependencies
        for (Job dep : dependenciesCopy) {
            job.addDependency(dep);  // Add each dependency to the job
        }
        
        scheduler.addJob(job);  // Add the job to the scheduler after handling dependencies
    }

    // Start executing all jobs
    public void startScheduling() throws InterruptedException {
        scheduler.executeJobs();  // Execute jobs in the scheduler
    }
}
