package com.company;

import java.util.List;

public class ConcreteStrategyQueue implements Strategy{

    @Override
    public void addTask(List<Server> servers, Task t) {

        int min = 10000000;
        int pos = 0;
        for(int i = 0; i < servers.size(); i++) {
            if(servers.get(i).getTasks().size() < min) {
                min = servers.get(i).getTasks().size();
                pos = i;
            }
        }
        t.setOverallTime(t.getProcessingTime() + servers.get(pos).getWaitingPeriod().get());
        servers.get(pos).addTask(t);
        if(servers.get(pos).getTasks().size() == 1) {
            Thread thread = new Thread(servers.get(pos));
            thread.start();
        }
    }

}
