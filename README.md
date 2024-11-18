# Job Scheduling System

This project is a job scheduling system that handles multiple tasks concurrently and optimally schedules them based on their priority, CPU usage, and memory usage. The system allows jobs to be executed in parallel while respecting job dependencies.

## Features
- **Priority-Based Scheduling**: Jobs are scheduled based on their priority, with higher priority jobs executed first.
- **Task Dependencies**: Jobs can depend on the completion of other jobs before they can start.
- **Multi-threaded Execution**: Jobs are executed using multiple threads for concurrent execution.
- **Job Details**: Each job has attributes like job ID, priority, CPU usage, and memory usage.

## Data Structures Used
- **Priority Queue/Heap**: Used for scheduling tasks based on priority.
- **Queues**: Used for handling jobs.
- **Hash Maps**: Store job details and states.
- **Linked Lists**: Handle task dependencies.

## Classes
- **Job**: Represents a job with job ID, priority, CPU usage, memory usage, and task dependencies.
- **Scheduler**: Responsible for managing the scheduling of jobs and their execution.
- **TaskManager**: Manages the addition of jobs with dependencies to the scheduler.
- **Main**: Contains the `main` method to run the job scheduling system.

## How to Run
1. Clone this repository to your local machine.
   ```bash
   git clone https://github.com/your-username/Job-Scheduler-Project.git
