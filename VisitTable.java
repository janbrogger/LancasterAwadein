  import java.awt.*;
  import java.awt.event.*;
  import javax.swing.*;
  import javax.swing.event.*;
 import javax.swing.table.*;
 
  public class VisitTable extends JPanel {
    // Create table column names
    String[] columnNames =
      {"Number", "Date"};
 
   // Create table data
   Object[][] data = { };
 
  // Create a model
   DefaultTableModel tableModel =
     new DefaultTableModel(data, columnNames);
 
   // Create a table
    JTable Table1 = new JTable(tableModel);

 public VisitTable() {

     add(new JScrollPane(Table1));     

}

}
