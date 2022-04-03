import javax.swing.*;
import java.awt.*;

public class SimulationView extends JFrame {
    private JPanel panel = new JPanel();
    private TextArea textArea = new TextArea();
    private JTextField peakText = new JTextField();
    private JTextField serviceText = new JTextField();

    public SimulationView(){
        JScrollPane scrollPane = new JScrollPane( textArea);
        textArea.setEditable(false);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        scrollPane.setBounds(50,10,1100,600);
        peakText.setBounds(50,640,400,30);
        serviceText.setBounds(50,680,400,30);

        peakText.setEditable(false);
        serviceText.setEditable(false);

        panel.add(peakText);
        panel.add(serviceText);
        panel.add(scrollPane);
        panel.setLayout(null);
        this.setContentPane(panel);
        this.setSize(1200,800);
        this.setTitle("Simulation");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void setTextArea(String string) {
        this.textArea.setText(string);
    }

    public void setPeakText(String string) {
        this.peakText.setText(string);
    }

    public void setServiceText(String string) {
        this.serviceText.setText(string);
    }

    public static void main(String[] args) {
        SimulationView simulationView = new SimulationView();
        simulationView.setVisible(true);
    }
}
