package com.company;


        import java.lang.reflect.Array;
        import java.util.Iterator;
        import java.util.concurrent.BlockingQueue;
        import java.util.concurrent.LinkedBlockingQueue;
        import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable{

    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;
    private int waitedOverallTime;


    public void setWaitedOverallTime(int waitedOverallTime) {
        this.waitedOverallTime = waitedOverallTime;
    }

    public int getWaitedOverallTime() {
        return waitedOverallTime;
    }

    public Server() {
        tasks = new LinkedBlockingQueue<Task>();
        waitingPeriod = new AtomicInteger();

    }

    public void addTask(Task newTask) {
        tasks.add(newTask);
        waitingPeriod.addAndGet(newTask.getProcessingTime());

    }
    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public void setWaitingPeriod(AtomicInteger waitingPeriod) {
        this.waitingPeriod = waitingPeriod;
    }

    public void setTasks(BlockingQueue<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void run() {
        while(!tasks.isEmpty()) {
            try {
                    if(tasks.peek() != null) {
                        Thread.sleep(1000);
                        waitingPeriod.getAndAdd(-1);
                        tasks.peek().setProcessingTime(tasks.peek().getProcessingTime() - 1);
                        if(tasks.peek().getProcessingTime() == 0) {

                            tasks.remove();
                            Thread.currentThread().interrupt();
                            continue;

                        }
                    }
            }
            catch(InterruptedException e) {

            }

        }

    }

    public BlockingQueue<Task> getTasks() {

        return tasks;
    }

    public Task[] getArrTasks()
    {
        return tasks.toArray(Task[]::new);
    }
}