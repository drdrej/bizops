package com.touchableheroes.odtfile.playground;

import java.util.List;
import java.util.Map;

import org.odftoolkit.odfdom.dom.element.table.TableTableRowElement;
import org.odftoolkit.simple.TextDocument;
import org.odftoolkit.simple.common.navigation.CellSelection;
import org.odftoolkit.simple.table.Cell;
import org.odftoolkit.simple.table.Row;
import org.odftoolkit.simple.table.Table;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.touchableheroes.odtcli.Config;
import com.touchableheroes.odtcli.commands.odt.OdtCommand;
import com.touchableheroes.odtcli.utils.JSONModelReader;

public class InvoiceDataOdtCommand implements OdtCommand<Object> {

	private Config config;

	public InvoiceDataOdtCommand(Config config) {
		this.config = config;
	}

	public Object exec(final TextDocument workDoc) {
		List data = loadData();
		int nrOfEntries = data.size();

		final Table invoiceSheet = workDoc.getTableByName("invoice-sheet");
		
		Row tmplRow = invoiceSheet.getRowByIndex(1);
		
		/*
		 * invoiceSheet
		 */
		final List<Row> inserted = invoiceSheet.insertRowsBefore(1, nrOfEntries);

		Map json = JSONModelReader.read(config.getDataJSON().getAbsolutePath());

		List entries = (List) json.get("data");

		int rowId = 0;
		Map entry = (Map) entries.get(rowId);

		int count = 0;
		for (Row row : inserted) {

			fillIdRow(count, row);
			fillDescFill((String) entry.get("description"), row);
			
			Map units = (Map) entry.get("units");
			
			fillPerUnit((String) units.get("perUnit"), row);
			fillUnitName( (String) units.get("type"), row);
			fillNrOfUnits((String) units.get("count"), row);
			fillValue((String) units.get("value"), row);

			count++;
		}
		
		return null;
	}

	
	private void fillValue(String value, Row row) {
		Cell cell = row.getCellByIndex(5);

		cell.setDisplayText( value );
	}

	private void fillUnitName(String value, Row row) {
		Cell cell = row.getCellByIndex(3);

		cell.setDisplayText( value );
	}

	private void fillNrOfUnits(String value, Row row) {
		Cell cell = row.getCellByIndex(2);

		cell.setDisplayText( value );
	}

	private void fillPerUnit(String value, Row row) {
		Cell cell = row.getCellByIndex(4);

		cell.setDisplayText( value );
	}

	public Config config() {
		return config;
	}

	private List loadData() {
		Map dom = JSONModelReader.read(config().getDataJSON().getAbsolutePath());

		List list = (List) dom.get("data");
		// for (Object row : list) {

		// }

		return list;
	}

	private void fillDescFill(final String description, Row row) {
		Cell cell = row.getCellByIndex(1);

		cell.setDisplayText( description );
	}

	private void fillIdRow(int count, Row row) {
		int id = 1 + count;
		Cell cell = row.getCellByIndex(0);
		cell.setDisplayText(id + ".");
	}

}
