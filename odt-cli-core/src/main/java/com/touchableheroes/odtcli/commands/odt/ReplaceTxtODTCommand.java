package com.touchableheroes.odtcli.commands.odt;

import java.util.Map;

import org.odftoolkit.odfdom.pkg.OdfElement;
import org.odftoolkit.simple.TextDocument;
import org.odftoolkit.simple.common.field.VariableField;
import org.odftoolkit.simple.common.navigation.InvalidNavigationException;
import org.odftoolkit.simple.common.navigation.Selection;
import org.odftoolkit.simple.common.navigation.TextDocumentSelection;
import org.odftoolkit.simple.common.navigation.TextNavigation;
import org.odftoolkit.simple.common.navigation.TextSelection;

import com.touchableheroes.odtcli.Config;
import com.touchableheroes.odtcli.utils.JSONModelReader;

public class ReplaceTxtODTCommand 
	   implements OdtCommand<Map> {

	private Config config;

	public ReplaceTxtODTCommand( final Config config) {
		this.config = config;
	}
	
	public Map exec(
			final TextDocument workDoc) {
		
		Map json = JSONModelReader.read( config.getPrefillJSON().getAbsolutePath() ); 
		
		final ReplaceUtil repl = new ReplaceUtil( workDoc );
		repl.replaceAll("", json);
		
		return json;
	}


}



