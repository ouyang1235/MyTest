package com.ouyang.demo.reactor.handler;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;

import java.util.concurrent.atomic.AtomicBoolean;

public abstract class AbstractHandler<T> implements ApplicationRunner {

    private final AtomicBoolean LOCK = new AtomicBoolean(false);

    @Override
    public void run(ApplicationArguments args) throws Exception {

        new Thread(()->{
            while(true){
                if (!lock()){
                    continue;
                }
                try{
                    scan().flatMap(this::process)
                            .subscribe(new BaseSubscriber<String>() {
                                @Override
                                protected void hookOnError(Throwable throwable) {
                                    super.hookOnError(throwable);
                                }

                                @Override
                                protected void hookFinally(SignalType type) {
                                    unlock();
                                }
                            });
                }catch (Throwable e){
                    sleep();
                    unlock();
                }
            }
        }).start();

    }


    protected abstract Flux<T> scan();

    protected abstract Mono<String> process(T data);

    protected boolean lock(){
        sleep();
        return LOCK.compareAndSet(false,true);
    }

    protected  void unlock(){
        LOCK.set(false);
    }

    protected void sleep(){
        try{
            Thread.sleep(500);
        } catch (InterruptedException e){
            System.out.println("sleep failed");
        }
    }
}
