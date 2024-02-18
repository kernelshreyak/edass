package com.edass.GUI;

import com.edass.Analysis.AnalysisEngine;
import com.edass.Analysis.AnalysisUtilities;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EdassGUI implements ActionListener {
    JFrame frame;

    JMenuBar menubar;
    JMenu file_menu, analysis_menu,transformation_menu;
    JMenuItem file_import, analysis_summary,transform_remove_duplicate_rows;

    AnalysisEngine engine;

    public EdassGUI(AnalysisEngine analysis_engine){
        JFrame frame = new JFrame("EDASS 1.0");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        this.frame = frame;
        this.engine = analysis_engine;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void createMenu(){
        menubar =new JMenuBar();
        file_menu=new JMenu("File");
        file_import = new JMenuItem("Import");
        file_import.addActionListener(this);
        file_menu.add(file_import);
        menubar.add(file_menu);

        analysis_menu=new JMenu("Analysis");
        analysis_summary = new JMenuItem("Descriptive Stats");
        analysis_summary.addActionListener(this);
        analysis_menu.add(analysis_summary);
        menubar.add(analysis_menu);

        transformation_menu=new JMenu("Transformation");
        transform_remove_duplicate_rows = new JMenuItem("Remove Duplicate Rows");
        transform_remove_duplicate_rows.addActionListener(this);
        transformation_menu.add(transform_remove_duplicate_rows);
        menubar.add(transformation_menu);

        frame.add(menubar);
        frame.setJMenuBar(menubar);
    }

    private void fileImport(){
        JFileChooser fileChooser = new JFileChooser("/home/shreyak/programming/datascience/datasets");
        int return_value = fileChooser.showOpenDialog(null);
        if (return_value == JFileChooser.APPROVE_OPTION) {
            String file_path = fileChooser.getSelectedFile().getAbsolutePath();
            engine.loadDataFrame(file_path,"csv");
            showDataTable(engine.getDataFrame());
        }
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == file_import){
            fileImport();
        }
    }

    public void showDataTable(Dataset<Row> df){
        String[][] rows = AnalysisUtilities.dataframeToRows(df,50);
        String[] columns = engine.getDataFrame().columns();
        JTable data_table = new JTable(rows,columns);
        data_table.setBounds(30,40,200,300);
        JScrollPane sp=new JScrollPane(data_table);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(sp);
        frame.setVisible(true);
    }
}
