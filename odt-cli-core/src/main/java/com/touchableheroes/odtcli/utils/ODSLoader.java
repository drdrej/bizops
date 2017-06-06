package com.touchableheroes.odtcli.utils;

import java.io.File;

import org.odftoolkit.simple.SpreadsheetDocument;

public class ODSLoader {
	
	public SpreadsheetDocument load(final File ods) {
		try {
			return SpreadsheetDocument.loadDocument( ods );
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	

	/*
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
	*/
}
