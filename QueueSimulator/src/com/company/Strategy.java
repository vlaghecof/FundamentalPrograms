package com.company;

import java.util.List;

public interface Strategy {
    void addTask(List<Server> servers, Task t);
}
