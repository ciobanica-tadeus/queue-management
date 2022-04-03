import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Scheduler {
    private List<Server> servers;
    private final int maxNoServers;
    private final int maxTasksPerServer;
    private Strategy strategy;

    public Scheduler(int maxNoServers, int maxTasksPerServer) {
        this.maxNoServers = maxNoServers;
        this.maxTasksPerServer = maxTasksPerServer;
//      servers = Collections.synchronizedList(new ArrayList<>());
        servers = new ArrayList<>();
        for (int i = 1; i <= maxNoServers; i++) {
            Server server = new Server(maxTasksPerServer);
            servers.add(server);
            server.setID(i);
            new Thread(server).start();
        }
    }

    public void changeStrategy(SelectionPolicy policy) {
        if (policy == SelectionPolicy.SHORTEST_QUEUE) {
            strategy = new ConcreteStrategyQueue();
        }
        if (policy == SelectionPolicy.SHORTEST_TIME) {
            strategy = new ConcreteStrategyTime();
        }
    }

    //expediaza task-ul conform strategiei alese
    public void dispatchTask(Task task) {
        strategy.addTask(servers, task);
    }

    public List<Server> getServers() {
        return servers;
    }

    public int getNmbClients(){
        int sum = 0;
        for( Server server: servers){
            sum += server.getCapacity();
        }
        return sum;
    }


}
