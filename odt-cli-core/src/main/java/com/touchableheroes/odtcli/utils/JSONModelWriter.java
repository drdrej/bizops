package com.touchableheroes.odtcli.utils;

import java.io.File;
import java.io.FileWriter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.touchableheroes.odtcli.models.SimpleTableModel;

public class JSONModelWriter {

	public void write(
			final SimpleTableModel json, 
			final File output) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(output, json.values() );
			
	/*
			String asStr = mapper.writeValueAsString( json.va );
			FileWriter fout = new FileWriter( output );
			
			fout.write( asStr );
			*/
		} catch (final Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
