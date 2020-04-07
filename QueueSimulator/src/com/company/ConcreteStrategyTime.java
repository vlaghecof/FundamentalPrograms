package com.company;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcreteStrategyTime implements Strategy{

    @Override
    public void addTask(List<Server> servers, Task t) {
        AtomicInteger min = new AtomicInteger(10000000);
        int pos = 0;
        for(int i = 0; i < servers.size(); i++) {
            if(servers.get(i).getWaitingPeriod().get() < min.get()) {
                min.set(servers.get(i).getWaitingPeriod().get());
                pos = i;
            }
        }

        t.setOverallTime(  servers.get(pos).getWaitingPeriod().get() + t.getProcessingTime());
        servers.get(pos).setWaitedOverallTime(servers.get(pos).getWaitedOverallTime()+ t.getOverallTime() )  ;

        servers.get(pos).addTask(t);
        if(servers.get(pos).getTasks().size() == 1) {
            Thread thread = new Thread(servers.get(pos));
            thread.start();
        }

    }
}

