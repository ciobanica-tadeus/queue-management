import java.util.ArrayList;
import java.util.List;

public class ConcreteStrategyTime implements Strategy{
    //avem lista de servere si fiecare dintre el are un anumit numar de clienti
    //in cazul acestei strategii noi vom pune task ul in functie de timpul
    @Override
    public void addTask(List<Server> servers, Task t) {
        int waitingTime = Integer.MAX_VALUE;
        for (Server server : servers){
            if( server.getWaitingPeriod() < waitingTime){
                waitingTime = server.getWaitingPeriod();
            }
        }
        for (Server server : servers){
            if(server.getWaitingPeriod() == waitingTime){
                server.addTask(t);
                break;
            }
        }

    }
}
