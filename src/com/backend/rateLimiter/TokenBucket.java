


import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;



class TokenBucket implements RateLimiter {
    
    int capacity;
    int refilRate;
    int currentTokens;
    Instant lastTime;

    public TokenBucket(int capacity, int refilRate){

        this.capacity = capacity;
        this.refilRate = refilRate;
        this.lastTime = null;
        this.currentTokens = capacity;

    }

    @Override
    public boolean israteLimited(){
        // pah: how to refil the bucket, check if a minute has passed, and then refil
        // pah: track clock
        // cron jobs and other bg timers similar always running things waste cpu cycles
        // lazy evaluation approach based on timestamp

        Instant curr = Instant.now();

        long minutesCount = ChronoUnit.MINUTES.between(curr, lastTime);
        System.out.println("test time: "+ curr);

        // int temp = minutesCount * refilRate;


        return false;
    }

    void replinishRate() {

    }
}