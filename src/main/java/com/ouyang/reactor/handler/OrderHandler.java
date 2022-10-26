package com.ouyang.reactor.handler;

import com.ouyang.reactor.Order;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.ByteBufFlux;
import reactor.netty.http.client.HttpClient;

import java.util.ArrayList;
import java.util.List;

@Profile("!dev")
@Component
public class OrderHandler extends AbstractHandler<Order>{

    private final HttpClient httpClient = HttpClient.create();

    @Override
    protected Flux<Order> scan() {
        return getOrders();
    }

    @Override
    protected Mono<String> process(Order data) {
        System.out.println("get order:no-"+data.getOrderNo());
        return Mono.just(data.getOrderNo());
    }

    private Flux<Order> getOrders(){
        ArrayList<Order> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Order order = new Order(String.valueOf(i));
            list.add(order);
        }
        return Flux.fromIterable(list);
    }

    protected <T> Mono<T> httpExecute(T t){
        return httpClient
                .headers(hb->{
            hb.add("abc","def");
        })
        .post()
        .uri("https://www.com")
        .send(ByteBufFlux.fromString(Mono.just("{asdasdad}")))
        .responseContent()
        .aggregate()
        .asString()
        .map(str->getResponse(t,str));
    }

    protected <T> T getResponse(T t,String str){
        return t;
    }

}
