package com.company;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class SimulationManager implements Runnable{

    public int timeLimit ; //maximum processing time
    public int maxProcessingTime ;
    public int minProcessingTime ;
    public int maxArrivalTime ;
    public int minArrivalTime ;
    public int numberOfServers;
    public int numberOfClients;
    public SelectionPolicy selectionPolicy = SelectionPolicy.SHORTEST_TIME;
    float  total=0;
    DataOutputStream dataOutput;
    File myObj1;
    private String fileName = "test";
    private String outputFileName;



    private Scheduler scheduler;
    private List<Task> generatedTasks;

    public SimulationManager(String fileName)   {
        this.fileName=fileName;
        getData(fileName);
        initialize();

        generatedTasks = new LinkedList<Task>();
        generateNRandomTasks();
        scheduler = new Scheduler(numberOfServers, numberOfClients);
        scheduler.changeStrategy(selectionPolicy);

    }



    public void initialize()
    {
        try {

            FileOutputStream file = new FileOutputStream(fileName.replace("in","out")+".txt" );
            dataOutput = new DataOutputStream(file) ;

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "SimulationManager{" +
                "timeLimit=" + timeLimit +
                ", \nmaxProcessingTime=" + maxProcessingTime +
                ", \n minProcessingTime=" + minProcessingTime +
                ", \nmaxArrivalTime=" + maxArrivalTime +
                ", \nminArrivalTime=" + minArrivalTime +
                ", \nnumberOfServers=" + numberOfServers +
                ", \nnumberOfClients=" + numberOfClients +
                '}';
    }

        private void generateNRandomTasks() {
        Random rand = new Random();
        for(int i = 0; i < numberOfClients; i++) {
            Task newTask = new Task();
            newTask.setId(i);
            newTask.setArrivalTime(rand.nextInt(maxArrivalTime) + minArrivalTime);
            newTask.setProcessingTime(rand.nextInt(maxProcessingTime) + minProcessingTime);
            generatedTasks.add(newTask);
        }




    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public List<Task> getGeneratedTasks() {
        return generatedTasks;
    }

    public void setGeneratedTasks(List<Task> generatedTasks) {
        this.generatedTasks = generatedTasks;
    }

    public void printServers( List<Server> servers ) {

        for (int i = 0; i <servers.size() ; i++)

        {   System.out.print("Server " + i + " has :");
            for (int j = 0; j < servers.get(i).getTasks().size(); j++) {
                Task[] tasksArr;
                tasksArr = servers.get(i).getArrTasks();
                System.out.printf(" ( %d , %d , %d) ", tasksArr[j].getId(),tasksArr[j].getArrivalTime() ,tasksArr[j].getProcessingTime());
            }
            System.out.println();
        }
    }

    public void printServersF( List<Server> servers ) {
        try {


            for (int i = 0; i <servers.size() ; i++) {
                dataOutput.writeBytes("Server " + i + " has :"); // System.out.print("Server " + i + " has :");
                for (int j = 0; j < servers.get(i).getTasks().size(); j++) {
                    Task[] tasksArr;
                    tasksArr = servers.get(i).getArrTasks();
                    dataOutput.writeBytes("( "+ tasksArr[j].getId()+" , "+tasksArr[j].getArrivalTime()+ " , "+tasksArr[j].getProcessingTime()+" )");

                }
                dataOutput.write('\n');
            }


        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    public void printCurrentTasks(List<Task> remianedTasks)
    {
        System.out.print("Waiting Clients :");
        for (int i = 0; i <remianedTasks.size() ; i++) {
            System.out.printf(" ( %d , %d ,%d) ",remianedTasks.get(i).getId(),remianedTasks.get(i).getArrivalTime(),remianedTasks.get(i).getProcessingTime());
        }
        System.out.println();
    }

    public void printCurrentTasksF(List<Task> remianedTasks)
    {
        try {


            dataOutput.writeBytes("Waiting Clients :");
            for (int i = 0; i <remianedTasks.size() ; i++) {
                dataOutput.writeBytes("( "+ remianedTasks.get(i).getId()+" , "+remianedTasks.get(i).getArrivalTime()+ " , "+remianedTasks.get(i).getProcessingTime()+" )");
            }
            dataOutput.writeBytes("\n");
        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    public boolean noWaitingClents(List<Server> servers)
    {
        for (int i = 0; i <servers.size() ; i++)
        {
            if(!servers.get(i).getTasks().isEmpty())
                return false;
        }
        return true;
    }



    @Override
    public void run() {
        int currentTime = 0;

        try {
            while(currentTime < timeLimit) {

                dataOutput.writeBytes("\n----------------------\n");
                dataOutput.writeBytes("Current time is " + currentTime+"\n");

                for(int i = 0; i < generatedTasks.size(); i++) {
                    if(generatedTasks.get(i).getArrivalTime() == currentTime) {

                        scheduler.dispatchTask(generatedTasks.get(i));
                        generatedTasks.remove(i);
                        i--;
                    }
                }
                //printCurrentTasks(generatedTasks);
                printCurrentTasksF(generatedTasks);

               // printServers(scheduler.getServers());
                printServersF(scheduler.getServers());

                if(generatedTasks.isEmpty() && noWaitingClents(scheduler.getServers()))
                {
                    System.out.println(" \nThe program will finish for "+ fileName);
                    dataOutput.writeBytes(" \nThe program will finish");
                    break;
                }
                currentTime++;


                try {
                    Thread.sleep(1000);
                }
                catch(InterruptedException e) {

                }


            }


            for (int i = 0; i < scheduler.getServers().size(); i++) {
                System.out.println("Server with nr " +
                        i + " had the waiting time of " + scheduler.getServers().get(i).getWaitedOverallTime());
                total += scheduler.getServers().get(i).getWaitedOverallTime();
                dataOutput.writeBytes("\nServer with nr " +
                        i + " had the waiting time of " + scheduler.getServers().get(i).getWaitedOverallTime());
                total += scheduler.getServers().get(i).getWaitedOverallTime();
            }
            System.out.println("Average waiting time : " + total / numberOfClients);
            dataOutput.writeBytes("\nAverage waiting time : " + total / numberOfClients);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void getData(String fileName)
    {

        try {
            File file = new File(fileName+".txt");
          //  File file = new File(fileName);

            Scanner scan = new Scanner(file);
            this.numberOfClients =scan.nextInt();
            this.numberOfServers = scan.nextInt();
            this.timeLimit = scan.nextInt();
            String line = scan.nextLine();
            line = scan.nextLine();
            String val[] = line.split(",");
            this.maxArrivalTime = Integer.parseInt(val[1]);
            this.minArrivalTime = Integer.parseInt(val[0]);

            line= scan.nextLine();
            val= line.split(",");

            this.maxProcessingTime = Integer.parseInt(val[1]);
            this.minProcessingTime = Integer.parseInt(val[0]);

        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred. Couldn't find the file");
            e.printStackTrace();
        }


    }



    public static void main(String[] args)  {
       SimulationManager gen1 = new SimulationManager("in-test-1");
        SimulationManager gen2 = new SimulationManager("in-test-2");
        SimulationManager gen3 = new SimulationManager("in-test-3");


        Thread t1 = new Thread(gen1);
        Thread t2 = new Thread(gen2);
        Thread t3 = new Thread(gen3);
        t1.start();
        t2.start();
        t3.start();




    }

}