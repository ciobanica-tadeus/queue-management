import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerUI {
    private View view;

    public ControllerUI(View view) {
        this.view = view;
        view.addSimulateListener(new SimulateListener());
    }

    public int getNumberClients() {
        return Integer.parseInt(view.getTextFieldClients().getText());
    }

    public int getNumberServer() {
        return Integer.parseInt(view.getTextFieldQueues().getText());
    }

    public int getSimulationTime() {
        return Integer.parseInt(view.getTextFieldSimulationTime().getText());
    }

    public int getMinArrivalTime() {
        return Integer.parseInt(view.getTextFieldMinArrivalTime().getText());
    }

    public int getMaxArrivalTime() {
        return Integer.parseInt(view.getTextFieldMaxArrivalTime().getText());
    }

    public int getMinServiceTime() {
        return Integer.parseInt(view.getTextFieldMinServiceTime().getText());
    }

    public int getMaxServiceTime() {
        return Integer.parseInt(view.getTextFieldMaxServiceTime().getText());
    }

    public SelectionPolicy getSelectionPolicy() {
        if (view.getFirstSelect())
            return SelectionPolicy.SHORTEST_TIME;
        else
            return SelectionPolicy.SHORTEST_QUEUE;
    }

    class SimulateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.dispose();
            SimulationManager simulationManager = new SimulationManager(getSimulationTime(),getMaxServiceTime(),
                                                                        getMinServiceTime(),getMinArrivalTime(),
                                                                        getMaxArrivalTime(),getNumberClients(),
                                                                        getNumberServer(),getSelectionPolicy());
            new Thread(simulationManager).start();
        }
    }
}
