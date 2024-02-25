package com.edass;
import com.edass.Analysis.AnalysisEngine;
import com.edass.GUI.EdassGUI;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] args) {
        try {
            AnalysisEngine engine = new AnalysisEngine();
            engine.initSession();
            EdassGUI gui = new EdassGUI(engine);
            gui.createMenu();

            // ACTION: window close
            gui.getFrame().addWindowListener(new WindowAdapter()
            {
                @Override
                public void windowClosing(WindowEvent e)
                {
                    engine.stopSession();
                    e.getWindow().dispose();
                }
            });


            gui.getFrame().setVisible(true);

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}