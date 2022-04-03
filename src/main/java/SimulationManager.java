import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.lang.Math;

public class SimulationManager implements Runnable {
    //data read from UI
    public int timeLimit;//60
    public int maxServiceTime;//= 4;
    public int minServiceTime;//= 2;
    public int minArrivalTime;//= 2;
    public int maxArrivalTime;//= 10;
    public int numberOfService;//= 4;
    public int numberOfServers;//= 2;
    public SelectionPolicy selectionPolicy;

    private Scheduler scheduler;
    private SimulationView simulationView;
    private List<Task> generatedTasks;

    public SimulationManager(int timeLimit, int maxServiceTime, int minServiceTime, int minArrivalTime, int maxArrivalTime, int numberOfService, int numberOfServers, SelectionPolicy selectionPolicy) {
        this.timeLimit = timeLimit;
        this.maxServiceTime = maxServiceTime;
        this.minServiceTime = minServiceTime;
        this.minArrivalTime = minArrivalTime;
        this.maxArrivalTime = maxArrivalTime;
        this.numberOfService = numberOfService;
        this.numberOfServers = numberOfServers;
        this.selectionPolicy = selectionPolicy;
        generatedTasks = new ArrayList<>();
        scheduler = new Scheduler(numberOfServers, numberOfService);
        scheduler.changeStrategy(selectionPolicy);

        simulationView = new SimulationView();
        simulationView.setVisible(true);
        generateNRandomTasks();
    }

    public void generateNRandomTasks() {
        for (int i = 0; i < numberOfService; i++) {
            Task generateTask = new Task(i, (int) (Math.random() * maxArrivalTime - minArrivalTime + 1) + minArrivalTime,
                    (int) (Math.random() * maxServiceTime - minServiceTime + 1) + minServiceTime);
            System.out.println(generateTask.getArrivalTime() + " si " + generateTask.getServiceTime());
            generatedTasks.add(generateTask);
        }
        Collections.sort(generatedTasks);
    }

    @Override
    public void run() {
        int currentTime = 0;
        int totalNmbClients = 0, maxNmbClients = 0, peakTime = 0;
        double avgServiceTime = 0;
        boolean finished = false;
        PrintWriter write = null;
        try {
            write = new PrintWriter("E:\\TP\\PT2022_30221_Ciobanica_Tadeus_Assignment_2\\output.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (currentTime < timeLimit) {
            List<Task> toRemove = new ArrayList<>();
            for (Task iterate : generatedTasks) {
                if (iterate.getArrivalTime() == currentTime) {
                    totalNmbClients++;
                    avgServiceTime += iterate.getServiceTime();
                    //calculate peak time
                    if (scheduler.getNmbClients() > maxNmbClients) {
                        maxNmbClients = scheduler.getNmbClients();
                        peakTime = currentTime;
                    }
                    scheduler.dispatchTask(iterate);
                    toRemove.add(iterate);
                }
            }
            generatedTasks.removeAll(toRemove);
            //AFISARE
            if (finished) {
                break;
            }
            String display = "Time:" + currentTime + "\nWaiting clients:\n";
            write.write("Time:" + currentTime + "\nWaiting clients:\n");
            System.out.println("Time:" + currentTime);
            System.out.println("Waiting clients: ");
            for (Task task : generatedTasks) {
                System.out.println("( " + task.getId() + "," + task.getArrivalTime() + "," + task.getServiceTime() + " )");
                display += "( " + task.getId() + "," + task.getArrivalTime() + "," + task.getServiceTime() + " )\n";
                write.write("( " + task.getId() + "," + task.getArrivalTime() + "," + task.getServiceTime() + " )\n");
            }
            for (Server server : scheduler.getServers()) {
                System.out.print("Queue" + server.getID() + ": ");
                display += "Queue" + server.getID() + ": ";
                write.write("Queue" + server.getID() + ": ");
                Task currentTask = server.getCurrentTask();
                if (currentTask != null && currentTask.getServiceTime() > 0) {
                    System.out.print("( " + currentTask.getId()
                            + "," + currentTask.getArrivalTime()
                            + "," + currentTask.getServiceTime() + " )");
                    display += "( " + currentTask.getId()
                            + "," + currentTask.getArrivalTime()
                            + "," + currentTask.getServiceTime() + " )";
                    write.write("( " + currentTask.getId()
                            + "," + currentTask.getArrivalTime()
                            + "," + currentTask.getServiceTime() + " )");
                    currentTask.setServiceTime(currentTask.getServiceTime() - 1);
                } else {
                    if (server.getWaitingPeriod() == 0) {
                        display += "closed;";
                        write.write("closed;");
                        System.out.print("closed;");
                    }
                }
                for (Task t : server.getTasks()) {
                    System.out.print("( " + t.getId()
                            + "," + t.getArrivalTime()
                            + "," + t.getServiceTime() + " ) ");
                    display += "( " + t.getId()
                            + "," + t.getArrivalTime()
                            + "," + t.getServiceTime() + " )";
                    write.write("( " + t.getId()
                            + "," + t.getArrivalTime()
                            + "," + t.getServiceTime() + " )");
                }
                System.out.println();
                display += "\n";
                write.write("\n");
            }
            int ok = 1;//pp ca sunt goale serverele
            for (Server server : scheduler.getServers()) {
                if (server.getWaitingPeriod() != 0) {
                    ok = 0;
                }
            }
            if (generatedTasks.isEmpty() && ok == 1) {
                finished = true;
            }
            //Update UI
            simulationView.setTextArea(display);
            write.write("\n");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            currentTime++;
        }
        avgServiceTime /= totalNmbClients;
        System.out.println("Peak Time: " + peakTime);
        simulationView.setPeakText("Peak Time: " + peakTime);
        System.out.println("Average service Time: " + avgServiceTime);
        simulationView.setServiceText("Average service Time: " + avgServiceTime);
        write.write("Peak Time: " + peakTime + "\n");
        write.write("Average service time: " + avgServiceTime + "\n");
        write.close();

    }
}

