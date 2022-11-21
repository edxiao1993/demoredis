package org.kevin.controller.lock;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author Kevin.Zng
 * @date 2022/11/21 23:41
 */
@RestController
public class RedissonController {
    @Autowired
    private RedissonClient redissonClient;

    @GetMapping("tryLock")
    public String tryLock() throws InterruptedException {
        RLock rLock = redissonClient.getLock("MyBasicLock");

        String result = "failed~";
        boolean res = rLock.tryLock(1, 10, TimeUnit.SECONDS);
        if (res) {
            try {
                System.out.println("hhh, I get the lock, you out!");
                TimeUnit.SECONDS.sleep(5);
                return "success";
            } finally {
                rLock.unlock();
            }
        } else {
            System.out.println("=========== damn it!!!");
        }
        return result;
    }

    @GetMapping("anotherLock")
    public String anotherLock() throws InterruptedException {
        RLock rLock = redissonClient.getLock("MyBasicLock");

        boolean res = rLock.tryLock(1, 10, TimeUnit.SECONDS);
        if (res) {
            try {
                System.out.println("I got the lock~");
                TimeUnit.SECONDS.sleep(10);
            } finally {
                rLock.unlock();
            }
        } else {
            System.out.println("=== damn it");
        }

        return "done";
    }
}
