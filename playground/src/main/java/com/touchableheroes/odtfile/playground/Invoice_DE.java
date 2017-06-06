package com.touchableheroes.odtfile.playground;

import java.io.File;

import org.odftoolkit.simple.TextDocument;

import com.touchableheroes.odtcli.Config;
import com.touchableheroes.odtcli.commands.ods.JsonFactoryCommand;
import com.touchableheroes.odtcli.commands.odt.ReplaceTxtODTCommand;
import com.touchableheroes.odtcli.models.SimpleTableModel;
import com.touchableheroes.odtcli.utils.JSONModelWriter;
import com.touchableheroes.odtcli.utils.ODSLoader;
import com.touchableheroes.odtfile.commands.Executer;
import com.touchableheroes.odtfile.commands.InvoiceDataCalculatorJSONModifier;
import com.touchableheroes.odtfile.commands.InvoiceDataOdtCommand;
import com.touchableheroes.odtfile.commands.InvoiceSumDataOdtCommand;

/**
 * 11.05.17
 * @author asiebert
 */
public class Invoice_DE {
	
	// Ablauf:
	// >odtcli Invoice_DE 2810.json -c="config"
	// 1. lade tmpl
	// 2. lade prepare.json
	// 3. erstelle zwischenergebnis odt
	
	// lade data.json
	// lade zwischenergebnis odt
	// änere values/data
	// schreibe ergebnis
	
	
	// Additional Tasks :::
	// --------------------
	// konvertiere odt zu pdf?
	// send email an mich sebst.
	
	
	
	public static void main(String[] args) {
		// Params from utside:
		final String configPath = "./config";
		final String tmplName = "Invoice_DE"; // .tmpl.odt
		final String dataName = "april-customer-X.json";
		
		final Config conf = new Config(configPath, tmplName, dataName);
		
		ODSLoader odsLoader = new ODSLoader();
		
		JsonFactoryCommand odsConverter = new JsonFactoryCommand(conf);
		
		final File dataFile = conf.getDataSpreadsheet();
		SimpleTableModel json = odsConverter.exec( odsLoader.load(dataFile) );
		
		JSONModelWriter jwrite = new JSONModelWriter();
		jwrite.write( json, conf.getDataJSON() );
		
		
		
		// ----------------- 
		/*
		new InvoiceDataCalculatorJSONModifier(conf).exec();
		
		
		final TextDocument tmpl = loadTemplate( conf.getTemplate());
		PrepareInvoiceCommand prepare = new PrepareInvoiceCommand(conf);
		prepare.exec( tmpl );
		
		save(conf.getPreparedPath(), tmpl);
		
		final TextDocument tmpl2 = loadTemplate( conf.getPreparedPath() );
		
		InvoiceDataOdtCommand invoice = new InvoiceDataOdtCommand(conf);
		invoice.exec(tmpl2);
		
		save(conf.getOutputPath(), tmpl2);

		InvoiceSumDataOdtCommand total = new InvoiceSumDataOdtCommand(conf);
		total.exec(tmpl2);

		save(conf.getOutputPath(), tmpl2);
		*/
	}

	private static void save(
			final File file, 
			final TextDocument tmpl) {
		try {
			tmpl.save( file );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IllegalStateException();
		}
	}

	private static TextDocument loadTemplate(
			final File template) {
		
		try {
			return TextDocument.loadDocument( template );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			throw new IllegalStateException(e);
		}
	}

}
