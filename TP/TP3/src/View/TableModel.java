package View;


import javax.swing.table.AbstractTableModel;

import Entity.StatePattern;

public class TableModel extends AbstractTableModel {

	private int[] values = new int[StatePattern.values().length + 1];
	
	public TableModel() {
		for(int i = 0; i < this.values.length; i++) {
			this.values[i] = 0;
		}
	}
	
	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public int getRowCount() {
		return StatePattern.values().length + 1;
	}
	
	public synchronized void add(StatePattern s) {
		if(s == StatePattern.ST_CHANGING_ROOM_IN || s == StatePattern.ST_CHANGING_ROOM_OUT) {
			this.values[this.values.length - 1] = this.values[this.values.length - 1] + 1;
			this.fireTableCellUpdated(this.values.length - 1, 1);
		}
		this.values[s.ordinal()] = this.values[s.ordinal()]+ 1;
		this.fireTableCellUpdated(s.ordinal(), 1);
	}
	
	public synchronized void sub(StatePattern s) {
		if(s == StatePattern.ST_CHANGING_ROOM_IN || s == StatePattern.ST_CHANGING_ROOM_OUT) {
			this.values[this.values.length - 1] = this.values[this.values.length - 1] - 1;
			this.fireTableCellUpdated(this.values.length - 1, 1);
		}
		this.values[s.ordinal()] = this.values[s.ordinal()] - 1;
		this.fireTableCellUpdated(s.ordinal(), 1);
//		System.out.println(s.name()+ " > " + this.values[s.ordinal()]);
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		return arg1 == 0 ? (arg0 < StatePattern.values().length) ? StatePattern.values()[arg0].name() : "CHANGING ROOM TOTAL" : this.values[arg0];
	}

}
