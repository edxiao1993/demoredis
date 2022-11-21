package org.kevin.controller.lock;

import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author Kevin.Zng
 * @date 2022/11/21 23:52
 */
@RestController
public class SemaphoreLock {
    @Autowired
    private RedissonClient redissonClient;

    @GetMapping("trySemp")
    public String trySemp() throws InterruptedException {
        RSemaphore smLock = redissonClient.getSemaphore("mySemaphore");
        smLock.trySetPermits(1);

        boolean res = smLock.tryAcquire(1, TimeUnit.SECONDS);
        if (res) {
            try {
                System.out.println("I got the Semaphore distributed lock~");
                TimeUnit.SECONDS.sleep(5);
                return "success";
            } finally {
                smLock.release();
            }
        }

        return "failed";
    }

    @GetMapping("anoSemp")
    public String anoSemp() throws InterruptedException {
        RSemaphore smLock = redissonClient.getSemaphore("mySemaphore");
        smLock.trySetPermits(1);

        boolean res = smLock.tryAcquire(1, TimeUnit.SECONDS);
        if (res) {
            try {
                System.out.println("I got the Semaphore distributed lock at another");
                TimeUnit.SECONDS.sleep(5);
                return "success";
            } finally {
                smLock.release();
            }
        } else {
            System.out.println("emm...");
        }

        return "failed";
    }
}
