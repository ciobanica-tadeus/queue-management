import java.util.List;

public class ConcreteStrategyQueue implements Strategy {

    @Override
    public void addTask(List<Server> servers, Task t) {
        int numberTasks = Integer.MAX_VALUE;
        for (Server iterate : servers) {
            if (iterate.getCapacity() < numberTasks) {
                numberTasks = iterate.getCapacity();
            }
        }

        for(Server iterate : servers){
            if( iterate.getCapacity() == numberTasks){
                iterate.addTask(t);
                break;
            }
        }
    }
}
