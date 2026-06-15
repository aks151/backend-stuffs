
public class Main {
    public static void main(String[] args) {
        TokenBucket tb = new TokenBucket(50,6);
        tb.israteLimited();
    }
}

