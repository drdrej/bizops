package com.touchableheroes.odtcli.commands.ods;

import java.util.List;

import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.table.Table;

import com.touchableheroes.odtcli.Config;
import com.touchableheroes.odtcli.commands.AbstractDocConverterCommand;
import com.touchableheroes.odtcli.commands.DocConverterCommand;
import com.touchableheroes.odtcli.models.SimpleTableModel;

public class JsonFactoryCommand 
	    extends AbstractDocConverterCommand<SpreadsheetDocument, SimpleTableModel>
        implements DocConverterCommand<SpreadsheetDocument, SimpleTableModel> {

	
	public JsonFactoryCommand(final Config config) {
		super(config);
	}

	public SimpleTableModel exec(final SpreadsheetDocument doc) {
		final List<Table> tables = doc.getTableList();
		 
		final SimpleTableModel json = new SimpleTableModel();
		for (final Table table : tables) {
			json.put(table);
		}
		
		return json; 
	}
		
}

