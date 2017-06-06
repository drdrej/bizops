package com.touchableheroes.odtcli.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.odftoolkit.simple.table.Row;
import org.odftoolkit.simple.table.Table;

public class SimpleTableModel {

	private Map<String, List<Object[]>> content = new HashMap<String, List<Object[]>>();
	
	public void put(final Table table) {
		final List<Object[]> values = new ArrayList<Object[]>(
				table.getRowCount() );
		
		for (final Row row : table.getRowList()) {
			final Object[] newRow = asJsonRow(row);
			
			if( newRow.length > 5)
				System.err.println( "row: " + newRow[0] + " | " + newRow[1] + " | " + newRow[2] + " | " + newRow[3] + " | " + newRow[4] + " | " + newRow[5]);
			
			values.add( newRow );
		}
		
		content.put(table.getTableName(), values);
	}

	private Object[] asJsonRow(Row row) {
		final int cells = row.getCellCount();
		
		System.err.println( "cell count : " + cells );
		final Object[] rval = new Object[ cells ]; 
		
		for (int i = 0; i < cells; i++) {
			rval[i] = row.getCellByIndex(i).getDisplayText();
		}
		
		return rval;
	}
	
	public Map<String, List<Object[]>> values() {
		return content;
	}
	
}
