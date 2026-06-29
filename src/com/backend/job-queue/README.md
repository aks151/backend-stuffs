## Reliable job queue with Dead-Letter Recovery

- worker pull jobs from a durable queue - jobs must survive crash
- support job priorities and scheduled execution
- automatic retries with exponential backoff on failure, capped at max attemp count 
- jobs exceeding max retries move to dead-letter that can be inspected and replayed
- a job is never processed by two workers at once, even if worker dies mid-job
- expose endpoint/cli to inspect queue depth, inflight jobs, dead-letter content
- idempotency: re-running a job that partially completed doesnt double apply side effects