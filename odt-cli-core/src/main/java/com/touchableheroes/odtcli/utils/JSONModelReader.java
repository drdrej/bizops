package com.touchableheroes.odtcli.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author asiebert
 * 11.05.17
 */
public class JSONModelReader {
	
	public static Map read( final String path ) {
		final ObjectMapper mapper = new ObjectMapper();
				
		final File file = new File( /*"./config/Invoice_DE/prepare.json"*/ path );
		try {
			final HashMap rval = mapper.readValue( file, HashMap.class );
			return rval;
		} catch (final Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IllegalStateException( e );
		} 
				
	}

}
