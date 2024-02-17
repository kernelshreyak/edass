import com.edass.Analysis.AnalysisEngine;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] args) {
        try {
            AnalysisEngine engine = new AnalysisEngine();
            engine.initSession();

            JFrame frame = new JFrame("CSV Reader");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            JButton openButton = new JButton("Open CSV File");
            openButton.setBounds(130,100,100, 40);

            frame.getContentPane().add(openButton);

            openButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFileChooser fileChooser = new JFileChooser();
                    int returnValue = fileChooser.showOpenDialog(null);
                    if (returnValue == JFileChooser.APPROVE_OPTION) {
                        String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                        engine.loadDataFrame(filePath,"csv");
                    }
                }
            });

            frame.addWindowListener(new WindowAdapter()
            {
                @Override
                public void windowClosing(WindowEvent e)
                {
                    engine.stopSession();
                    e.getWindow().dispose();
                }
            });

            // Display the frame
            frame.setVisible(true);

        }
        catch (Exception e){
            System.out.println("ERROR:" + e.getMessage());
        }
    }
}