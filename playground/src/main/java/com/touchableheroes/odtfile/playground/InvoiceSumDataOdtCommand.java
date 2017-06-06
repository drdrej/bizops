package com.touchableheroes.odtfile.playground;


import java.util.Map;

import org.odftoolkit.simple.TextDocument;

import com.touchableheroes.odtcli.Config;
import com.touchableheroes.odtcli.commands.odt.OdtCommand;
import com.touchableheroes.odtcli.commands.odt.ReplaceUtil;
import com.touchableheroes.odtcli.utils.JSONModelReader;

public class InvoiceSumDataOdtCommand 
		implements OdtCommand<Object> {

	private Config config;

	public InvoiceSumDataOdtCommand(Config config) {
		this.config = config;
	} 
	
	public Object exec(final TextDocument workDoc) {
		Map json = JSONModelReader.read( config.getDataJSON().getAbsolutePath() ); 
		
		final ReplaceUtil repl = new ReplaceUtil( workDoc );
		repl.replaceAll("", json);
		
		return null;
	}

	public Config config() {
		return config;
	}

	
}
