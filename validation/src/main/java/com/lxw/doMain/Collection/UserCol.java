package com.lxw.doMain.Collection;

import com.lxw.doMain.User;
import com.lxw.doMain.ValidationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@RestController
public class UserCol {

    @PostMapping("/insert")
    public void insert(@RequestBody @Validated(value = ValidationImpl.insert.class) User u){
        System.out.println(u.getUserName());
    }

    @PostMapping("/update")
    public void update(@RequestBody @Validated(value = ValidationImpl.update.class) User u){
        System.out.println(u.getUserName());
    }


    @GetMapping("/findById")
    public void findById(@Validated(value = ValidationImpl.findById.class) User u){
        System.out.println(u.getId());
    }


    @Autowired
    private ImplTest implTest;

    @GetMapping("/testFuture")
    public void testFuture() throws ExecutionException, InterruptedException, TimeoutException {
        long l = System.currentTimeMillis();
        Future<String> stringFuture = implTest.test_1();
        System.out.println("1：" + (System.currentTimeMillis() - l));
        Future<String> stringFuture1 = implTest.test_2();
        System.out.println("2：" + (System.currentTimeMillis() - l));
        String s = stringFuture.get(2, TimeUnit.SECONDS);
        System.out.println("3：" + (System.currentTimeMillis() - l));
        String s1 = stringFuture1.get(2, TimeUnit.SECONDS);
        System.out.println("4：" + (System.currentTimeMillis() - l));
        System.out.println(s);
        System.out.println(s1);

    }
}
