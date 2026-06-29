import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DurableJobScheduler {

    public record Job(int id, String payload, int priority, Instant runAt) {}

    private final ScheduledExecutorService workerPool = Executors.newSingleThreadScheduledExecutor();

    public void startEngine() {
        workerPool.scheduleWithFixedDelay(this::pollAndExecute, 0, 1, TimeUnit.SECONDS);
        System.out.println("Job Engine Started...");
    }

    private void pollAndExecute() {
        try {
            Job nextJob = fetchNextJobFromDatabase();

            if(nextJob != null){
                System.out.println("Executing Job[" + nextJob.id() + "]" + nextJob.payload());

                executeTaskLogic(nextJob.payload());

                updateJobStatus(nextJob.id(), "COMPLETED");

            }
        } catch (Exception e){
            System.err.println("Error processing job: " + e.getMessage());
        }
    }

    private Job fetchNextJobFromDatabase() {
        return null;
    }

    private void executeTaskLogic(String payload){
        // logic
    }

    private void updateJobStatus(int jobId, String status) {
        // TODO: Run: UPDATE job_queue SET status = ? WHERE id = ?
        System.out.println("Job " + jobId + " status updated to " + status);
    }
}