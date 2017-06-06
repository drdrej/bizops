package com.touchableheroes.odtfile.playground;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.TextDocument;
import org.odftoolkit.simple.table.Cell;
import org.odftoolkit.simple.table.CellRange;
import org.odftoolkit.simple.table.Table;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.touchableheroes.odtcli.Config;

/**
 * @author asiebert
 */
public class InvoiceDataCalculatorJSONModifier {

	private Config config;

	public InvoiceDataCalculatorJSONModifier(
			final Config config) {
		this.config = config;
	}
	
	public void exec() {
		try {
			final File dataFile = config().getDataSpreadsheet();
			final SpreadsheetDocument sheet = SpreadsheetDocument.loadDocument( dataFile  );
			
			final Table table = sheet.getTableByName( "Invoices" );
			final CellRange range1 = table.getCellRangeByName( "invoiceData" ); // range: $Invoices.$A$2:$F$11 name: 
			
			final CellRange range = table.getCellRangeByPosition( "$A$2", "$F$11" ); 
			// todo: null checks                               invoiceData
			// final List values = new ArrayList();
			
			int rowId = 0;
			
			final Map<String, Object> row = new HashMap<String, Object>();
			
			row.put( "id", createId() );
		
			// row.put( "deliverWorkIn", 
			//		range.getCellByPosition(0, rowId ).getDisplayText() );

			row.put( "description", 
					 range.getCellByPosition(5, rowId).getDisplayText() );

			final Map<String, Object> units = new HashMap<String, Object>();
			row.put( "units", units );
			
			Object nrOfUnits = range.getCellByPosition(1, rowId).getDisplayText();
			units.put( "count", 
					nrOfUnits  );
			
			
			
			Object perUnit = range.getCellByPosition(2, rowId).getDisplayText();
			units.put( "perUnit", 
					perUnit );

			units.put( "type", "Std" );
			units.put( "value", 
					range.getCellByPosition(3, rowId).getDisplayText() );
			
			// create FullDoc:
			Map root = new HashMap();
			
			List data = new ArrayList();
			data.add( row );
			
			root.put( "data", data );
			root.put( "invoice", createIvoice( parseUnitsDouble(nrOfUnits ),
											   parseUnitsDouble(perUnit),
											   range.getCellByPosition(0, rowId ).getDisplayText() ));
			
			// save JSON:
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(  config.getDataJSON(), root );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IllegalStateException(e);
		}
	}

	private double parseUnitsDouble(Object nrOfUnits) {
		String str = String.valueOf( nrOfUnits );
		return Double.parseDouble( str );
	}

	private Object createIvoice(double nrOfUnits, double perUnit, String deliverIn ) {		
		Map rval = new HashMap();
		
		rval.put( "id", createId() );
		rval.put( "deliverWorkIn", deliverIn );
		rval.put( "createdAt", Calendar.getInstance().getTime().toString() );
		
		double sum = calcSum(nrOfUnits, perUnit);
		rval.put( "sumValue", sum );
		
		double vat = calcVat( sum );
		rval.put( "vatValue", vat );
		
		rval.put( "total", calcTotal(sum, vat) );
		
		return rval;
	}

	private double calcVat(double sum) {
		// TODO: precise
		return sum*0.19;
	}

	private double calcTotal(
			final double sum, double vat) {
		// TODO: precise!
		return sum + vat;
	}

	private Double calcSum(double nrOfUnits, double perUnit ) {
		// TODO: precise!
		return nrOfUnits*perUnit;
	}

	private Object createId() {
		// TODO: impl your own way for invoice-ids!
		return UUID.randomUUID();
	}

	private Config config() {
		return config;
	}
	
}
