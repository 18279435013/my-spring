package com.lxw.event;


import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.ResolvableType;


@SpringBootApplication
public class Application {


    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Application.class);


        ResolvableType resolvableType = ResolvableType.forInstance(new Test4("123"));

        System.out.println(resolvableType.isAssignableFrom(Test3.class));
        System.out.println(resolvableType.isAssignableFrom(Test4.class));
        System.out.println(resolvableType.isAssignableFrom(Test2.class));



        DefaultSingletonBeanRegistry beanFactory = (DefaultSingletonBeanRegistry) run.getBeanFactory();




//
//        //获取所有的资源
//        Resource[] resources = run.getResources(ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX);
//        Arrays.stream(resources).forEach(r -> {
//            System.out.println(r.getDescription());
//        });
//        //获取环境变量
//        ClassLoader classLoader = run.getClassLoader();
//        System.out.println(classLoader.toString());
//
//
//        Field singletonObjects = DefaultSingletonBeanRegistry.class.getDeclaredField("singletonObjects");
//        singletonObjects.setAccessible(true);
//        Map<String, Object> o1 = (Map<String, Object>)singletonObjects.get(beanFactory);
//        o1.forEach((k,v) -> {
//            System.out.println(k +" " + v);
//        });
//
//
//        Field allBeanNamesByType = DefaultListableBeanFactory.class.getDeclaredField("allBeanNamesByType");
//        allBeanNamesByType.setAccessible(true);
//        Object o = allBeanNamesByType.get(beanFactory);
//
//        SimpleApplicationEventMulticaster bean1 = run.getBean(SimpleApplicationEventMulticaster.class);
//        bean1.setTaskExecutor(new ThreadPoolExecutor(10,10,100, TimeUnit.SECONDS,new ArrayBlockingQueue<>(10)));
//        bean1.setErrorHandler(r -> {
//
//            System.out.println("执行异常了 "+r.getMessage());
//
//            throw new RuntimeException(r.getMessage());
//
//        });
//
//        Test2 bean = run.getBean(Test2.class);
//
//        bean.test2("lxw");
    }
}
