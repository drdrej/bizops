package com.touchableheroes.odtcli.commands.cli;

import java.io.File;

import com.touchableheroes.odtcli.Config;
import com.touchableheroes.odtcli.commands.DocConverterCommand;
import com.touchableheroes.odtcli.commands.ods.JsonFactoryCommand;
import com.touchableheroes.odtcli.models.SimpleTableModel;
import com.touchableheroes.odtcli.utils.JSONModelWriter;
import com.touchableheroes.odtcli.utils.ODSLoader;


public class ODS2JSONConverterCLICommand
       implements DocConverterCommand<Config, SimpleTableModel>{

	
	public static void main(String[] args) {
		// Params from utside:
		final String configPath = "./config";
		final String tmplName = "Invoice_DE"; // .tmpl.odt
		final String dataName = "april-customer-X.json";
		
		final Config conf = new Config(configPath, tmplName, dataName);
		new ODS2JSONConverterCLICommand().exec(conf);
	}
	
	public SimpleTableModel exec(final Config conf) {
		final ODSLoader odsLoader = new ODSLoader();
		
		final JsonFactoryCommand odsConverter = new JsonFactoryCommand(conf);
		
		final File dataFile = conf.getDataSpreadsheet();
		final SimpleTableModel json = odsConverter.exec( odsLoader.load(dataFile) );
		
		final JSONModelWriter jwrite = new JSONModelWriter();
		jwrite.write( json, conf.getDataJSON() );
		
		return json;
	}
}
