


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

        Instant curr = Instant.now();

        long minutesCount = ChronoUnit.MINUTES.between(curr, lastTime);
        System.out.println("test time: "+ curr);

        // rate limit check 

        long temp = minutesCount*refilRate;
        // update tokens
        if(temp+refilRate > capacity){
            refilRate = capacity;
        } else {
            refilRate += temp;
        }

        if(currentTokens > 0){
            currentTokens--;
            return true;
        }

        

        return false;
    }

    void replinishRate() {

    }
}