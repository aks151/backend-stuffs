


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
        this.lastTime = Instant.now();
        this.currentTokens = capacity;

    }

    @Override
    public boolean israteLimited(){

        Instant curr = Instant.now();

        // bug: we are considering a minute of duration, if two request comes at 4th second and 56th second
        // both are in the same min, so rate wont be replenished
        // 
        long minutesCount = ChronoUnit.MINUTES.between(lastTime, curr);
        System.out.println("test time: "+ curr);

        long temp = minutesCount*refilRate;
        // update tokens
        if(temp+refilRate > capacity){
            currentTokens = capacity;
        } else {
            currentTokens += temp;
        }

        // rateLimit check 
        if(currentTokens > 0){
            currentTokens--;
            return false;
        }

        

        return true;
    }

    void replinishRate() {

    }
}