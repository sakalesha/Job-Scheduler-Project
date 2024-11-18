import java.util.*;
import java.util.concurrent.*;

public class Scheduler {
    private PriorityQueue<Job> jobQueue; // queue of jobs to be executed based on priority
    private Map<Integer, Job> jobMap;

    // Constructor
    public Scheduler() {
        jobQueue = new PriorityQueue<>(Comparator.comparingInt(Job::getPriority).reversed()); // Max priority queue
        // Comparator in Java is an interface that defines a custom sorting order for objects
        // Comparator.comparingInt(Job::getPriority).reversed() is a concise way to create a custom comparator for comparing Job objects based on their priority in descending order.
        // Comparator.comparingInt --> comparator based on an integer 
        // Job::getPriority is a method reference pointing to the getPriority() method of the Job class.
        jobMap = new HashMap<>();
    }

    // Add job to the queue
    public void addJob(Job job) {
        jobQueue.offer(job); // Add job to the priority queue
        // offer method is a part of the Java PriorityQueue class and is used to add an element to the queue. 
        jobMap.put(job.getJobId(), job); // Store job in the map
    }

    // Multi-threading
    // Execute jobs
    public void executeJobs() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5); // Fixed thread pool of 5 threads. multiple jobs can be processed simultaneously (up to 5 at a time).
        while (!jobQueue.isEmpty()) {
            Job job = jobQueue.poll(); // Get the highest-priority job
            // If the job has no dependencies (i.e., the job can run immediately), the code submits the job to the thread pool for execution.
            if (job.getDependencies().isEmpty()) {
                executorService.submit(() -> runJob(job)); // Submit task to thread pool
            } else {
                // Handle dependencies before executing
                handleDependencies(job);
            }
        }
        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS); // Wait for all tasks to finish
        // before shutting down the ExecutorService, the executeJobs() method waits for all the threads (tasks) to finish their execution.
    }
    // A thread is the smallest unit of execution within a process in a computer system. perform multiple tasks concurrently (in parallel or pseudo-parallel
    // A pool of threads, or thread pool, is a collection of pre-instantiated and reusable threads maintained by a thread management system (like Java's ExecutorService) to execute tasks. 
    // ExecutorService is an interface in Java's java.util.concurrent package that provides a framework for managing and controlling a pool of threads. It simplifies the process of managing multiple threads, such as creating, starting, and terminating threads.
    // The choice of 5 threads is a reasonable starting point for general tasks, balancing efficiency and resource usage.


    // Running a Job
    private void runJob(Job job) {
        System.out.println("Executing " + job); // toString() method of the Job class is automatically called when the job object is printed
        // Executing JobID: 1, Priority: 10
        try {
            Thread.sleep(1000); // Simulate task execution time
            // thread which is currently executing this code will stop running for the specified time. Other threads in the program (if there are any) will continue to run
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Handle thread interruption
            // This exception is thrown if the thread is interrupted while it's sleeping.
        }
        System.out.println("Completed " + job); // Completed JobID: 1, Priority: 10
    }

    // Handle job dependencies 
    // manage job dependencies before a job can be executed.
    private void handleDependencies(Job job) {
        for (Job dependency : job.getDependencies()) {
            if (jobMap.containsKey(dependency.getJobId())) {
                addJob(dependency); // Add dependent jobs to the jobQueue
            }
        }
    }
}

/*
Jobs are runned according to priorty(scheduling) 

Add Jobs: Jobs are added to the scheduler with addJob, specifying their priority, CPU usage, memory usage, and dependencies.
Execute Jobs:
The scheduler uses a fixed thread pool to execute jobs concurrently.
Higher-priority jobs are executed first (due to the priority queue).
Jobs with unresolved dependencies are deferred until dependencies are resolved.
Dependency Resolution:
Before executing a job, the scheduler ensures all its dependencies are added to the queue.
Dependencies are resolved iteratively.
 */