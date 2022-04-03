import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {
    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;
    private Task currentTask;
    private final int capacity;
    private int ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getWaitingPeriod() {
        return waitingPeriod.intValue();
    }

    public Server(int capacity) {
        this.capacity = capacity;
        tasks = new ArrayBlockingQueue<>(this.capacity);
        waitingPeriod = new AtomicInteger();
    }

    public void addTask(Task task) {
        try {
            tasks.put(task);
            waitingPeriod.addAndGet(task.getServiceTime());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Task getCurrentTask(){
        return currentTask;
    }

    public BlockingQueue<Task > getTasks(){
        return tasks;
    }

    public int getCapacity(){
        return tasks.size();
    }
    @Override
    public void run() {
        while (true) {
            try {
                Task nextTask = tasks.take();
                currentTask = nextTask;
                int serviceTime = nextTask.getServiceTime();
                while (serviceTime > 0) {
                    Thread.sleep(1000 );
                    serviceTime --;
                    waitingPeriod.decrementAndGet();
                }
                currentTask = null;
            } catch (InterruptedException e) {
                System.out.println("Interrupted!!");
                e.printStackTrace();
            }
        }
    }
}
