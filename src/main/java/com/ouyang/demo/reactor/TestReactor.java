package com.ouyang.demo.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class TestReactor {

    public static void main(String[] args) {
//        Flux<Integer> intFlux = Flux.just(1, 2, 3, 4);
//        intFlux.subscribe(System.out::println);
//
//        Flux<String> strFlux = Flux.just("hello", "world");
//        strFlux.subscribe(System.out::println);

        Integer[] array = {1,2,3,4};
        Flux<Integer> integerFlux = Flux.fromArray(array).flatMap(Mono::just);
        Flux<Mono<Integer>> map = Flux.fromArray(array).map(Mono::just);
        Mono<Mono<Integer>> map1 = Mono.just(1).map(Mono::just);
        Mono<Integer> integerMono = Mono.just(1).flatMap(Mono::just);

//        Predicate<LocalDateTime> predicate = i -> i.isBefore(LocalDateTime.now());
//        boolean test = predicate.test(LocalDateTime.now().minusSeconds(1L));
//        System.out.println(test);



    }

}
