import java.util.ArrayList;
import java.util.List;

public class Job {
    private int jobId;
    private int priority;
    private int cpuUsage;
    private int memoryUsage;
    private List<Job> dependencies; // for task dependencies

    // Constructor
    public Job(int jobId, int priority, int cpuUsage, int memoryUsage) {
        this.jobId = jobId;
        this.priority = priority;
        this.cpuUsage = cpuUsage;
        this.memoryUsage = memoryUsage;
        this.dependencies = new ArrayList<>();
    }

    // Getters and setters --> Provides access to the private fields of the class
    public int getJobId() { return jobId; }
    public int getPriority() { return priority; }
    public int getCpuUsage() { return cpuUsage; }
    public int getMemoryUsage() { return memoryUsage; }
    public List<Job> getDependencies() { return dependencies; }

    // Add a dependency
    public void addDependency(Job job) {
        this.dependencies.add(job);
    }

    // Provides a human-readable string representation of a Job object
    @Override
    public String toString() {
        return "JobID: " + jobId + ", Priority: " + priority; // JobID: 1, Priority: 5
    }
}
