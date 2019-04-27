package demo;

import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RateService {
     
    private BigDecimal rate = BigDecimal.ZERO;
    private ReadWriteLock lock = new ReentrantReadWriteLock();
 
    public BigDecimal getRate() {
        lock.readLock().lock();
        BigDecimal newReate = rate;
        lock.readLock().unlock();
        return newReate;
    }
 
    public void setRate(BigDecimal rate) {
        lock.writeLock().lock();
        this.rate = rate;
        lock.writeLock().unlock();
    }
     
    private static class Read implements Runnable
    {
        private RateService rateService = null;
         
        public Read(RateService rateService)
        {
            this.rateService = rateService;
        }
 
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            synchronized (this) {
            	System.out.println(rateService.getRate());
                System.out.println("rœﬂ≥Ã√˚£∫"+Thread.currentThread().getName());
			} 
        }
    }
     
    private static class Write implements Runnable
    {
        private RateService rateService = null;
 
        public Write(RateService rateService)
        {
            this.rateService = rateService;
        }
         
        @Override
        public void run() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            BigDecimal updatedRate = new BigDecimal(new Random().nextInt(100));
            String threadName = Thread.currentThread().getName();
            synchronized(this)
            {
                System.out.println(System.nanoTime() + " - " + threadName + " WRITE: " + updatedRate);
                rateService.setRate(updatedRate);
            }
        }
         
    }
     
    public static void main(String[] args)
    {
        RateService rateService = new RateService();
        Write write = new Write(rateService);
        for(int i = 0; i < 10; i++)
        {
            Thread thread = new Thread(write);
            thread.setName("W" + i);
            thread.start();
        }
         
        Read read = new Read(rateService);
        for(int i = 0; i < 10; i++)
        {
            Thread thread = new Thread(read);
            thread.setName("R" + i);
            thread.start();
        }
    }
     
 
}