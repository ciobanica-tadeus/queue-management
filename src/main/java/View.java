import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame {
    private JPanel panel = new JPanel();
    private JLabel labelClients = new JLabel("Introduce number of clients");
    private JTextField textFieldClients = new JTextField();
    private JLabel labelQueues = new JLabel("Introduce number of queues");
    private JTextField textFieldQueues = new JTextField();
    private JLabel labelSimulationTime = new JLabel("Introduce simulation time");
    private JTextField textFieldSimulationTime = new JTextField();
    private JLabel labelMinArrivalTime = new JLabel("Minimum arrival time");
    private JTextField textFieldMinArrivalTime = new JTextField();
    private JLabel labelMaxArrivalTime = new JLabel("Maximum arrival time");
    private JTextField textFieldMaxArrivalTime = new JTextField();
    private JLabel labelMinServiceTime = new JLabel("Minimum service time");
    private JTextField textFieldMinServiceTime = new JTextField();
    private JLabel labelMaxServiceTime = new JLabel("Maximum service time");
    private JTextField textFieldMaxServiceTime = new JTextField();
    private JRadioButton firstSelect = new JRadioButton("SHORTEST TIME");
    private JRadioButton secondSelect = new JRadioButton("SHORTEST QUEUE");
    private ButtonGroup group = new ButtonGroup();
    private JButton btnStart = new JButton("START");

    public JTextField getTextFieldClients() {
        return textFieldClients;
    }

    public JTextField getTextFieldQueues() {
        return textFieldQueues;
    }

    public JTextField getTextFieldSimulationTime() {
        return textFieldSimulationTime;
    }

    public JTextField getTextFieldMinArrivalTime() {
        return textFieldMinArrivalTime;
    }

    public JTextField getTextFieldMaxArrivalTime() {
        return textFieldMaxArrivalTime;
    }

    public JTextField getTextFieldMinServiceTime() {
        return textFieldMinServiceTime;
    }

    public JTextField getTextFieldMaxServiceTime() {
        return textFieldMaxServiceTime;
    }

    public boolean getFirstSelect() {
        if (firstSelect.isSelected())
            return true;
        return false;
    }

    public View() {
        labelClients.setBounds(0, 10, 200, 20);
        textFieldClients.setBounds(10, 40, 200, 30);
        labelQueues.setBounds(10, 80, 200, 20);
        textFieldQueues.setBounds(10, 110, 200, 30);
        labelSimulationTime.setBounds(10, 150, 200, 20);
        textFieldSimulationTime.setBounds(10, 180, 200, 30);
        labelMinArrivalTime.setBounds(10, 220, 200, 20);
        textFieldMinArrivalTime.setBounds(10, 250, 200, 30);
        labelMaxArrivalTime.setBounds(10, 290, 200, 20);
        textFieldMaxArrivalTime.setBounds(10, 320, 200, 30);
        labelMinServiceTime.setBounds(10, 360, 200, 20);
        textFieldMinServiceTime.setBounds(10, 390, 200, 30);
        labelMaxServiceTime.setBounds(10, 430, 200, 20);
        textFieldMaxServiceTime.setBounds(10, 460, 200, 30);
        firstSelect.setBounds(10, 500, 150, 30);
        secondSelect.setBounds(160, 500, 150, 30);
        btnStart.setBounds(100, 550, 150, 30);
        group.add(firstSelect);
        group.add(secondSelect);

        panel.add(firstSelect);
        panel.add(secondSelect);
        panel.add(labelClients);
        panel.add(textFieldClients);
        panel.add(labelQueues);
        panel.add(textFieldQueues);
        panel.add(labelSimulationTime);
        panel.add(textFieldSimulationTime);
        panel.add(labelMinArrivalTime);
        panel.add(textFieldMinArrivalTime);
        panel.add(labelMaxArrivalTime);
        panel.add(textFieldMaxArrivalTime);
        panel.add(labelMinServiceTime);
        panel.add(textFieldMinServiceTime);
        panel.add(labelMaxServiceTime);
        panel.add(textFieldMaxServiceTime);
        panel.add(btnStart);

        panel.setLayout(null);
        this.setTitle("Simulation manager");
        this.setSize(500, 650);
        this.setContentPane(panel);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void addSimulateListener(ActionListener actionListener) {
        btnStart.addActionListener(actionListener);
    }
}
