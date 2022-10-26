package com.ouyang;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;

@Rule(name = "hello World Rule")
public class HelloWorldRule {

    @Condition
    public boolean when(@Fact("haha") String haha) {
        return ("boo!".equals(haha) || "poo!".equals(haha));
    }

    @Action(order = 1)
    public void then(Facts facts) {
        System.out.println("今天就是死，也要打伞！");
        facts.remove("haha");
//        facts.put("haha", "poo!");
    }

    @Action(order = 2)
    public void then2(Facts facts) {
        System.out.println("今天就是死，也要再打伞！");
        facts.remove("haha");
    }


}
