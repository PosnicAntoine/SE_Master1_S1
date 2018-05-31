package View;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;

public class Vue extends JFrame  {
	
	private TableModel model;
	
	public Vue() {
		this.setTitle("monitoring ...");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		this.model = new TableModel();
		JTable table = new JTable(this.model);
		this.setContentPane(table);
		this.pack();
		this.setSize(new Dimension(600, this.getSize().height));
		this.setVisible(true);
	}
	
	public TableModel getModel() {
		return this.model;
	}
}
