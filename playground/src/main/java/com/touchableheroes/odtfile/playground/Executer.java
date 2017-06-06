package com.touchableheroes.odtfile.playground;

import java.io.InputStream;

import org.odftoolkit.simple.TextDocument;

import com.touchableheroes.odtcli.Config;
import com.touchableheroes.odtcli.commands.odt.OdtCommand;
import com.touchableheroes.odtcli.commands.odt.ReplaceTxtODTCommand;

public class Executer {


	public static void exec(
			final Config config,
			final Class<OdtCommand>... types) {
		
		for (final Class<OdtCommand> type : types) {
			try {
				OdtCommand cmd = type.newInstance();
				
				//cmd.exec(workDoc);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// TODO Auto-generated method stub
		
		throw new UnsupportedOperationException();
	}
}
