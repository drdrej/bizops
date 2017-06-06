package com.touchableheroes.odtcli.commands.odt;

import java.util.Map;


import org.odftoolkit.simple.TextDocument;
import org.odftoolkit.simple.common.navigation.InvalidNavigationException;
import org.odftoolkit.simple.common.navigation.Selection;
import org.odftoolkit.simple.common.navigation.TextNavigation;
import org.odftoolkit.simple.common.navigation.TextSelection;

public class ReplaceUtil {

		private final TextDocument doc;

		public ReplaceUtil( TextDocument workDoc ) {
			this.doc = workDoc;
		}
		
		public void replace( final String what,
						  final String with) {
	        final TextNavigation search = new TextNavigation( "\\$\\{" + what + "\\}", doc);
	        
	        while (search.hasNext()) {
	            Selection selected = search.nextSelection();
	            TextSelection asTxt = (TextSelection) selected;
	            
	            try {
	            	System.err.println( ">>> replace ${" + what + "} with " + with );
					asTxt.replaceWith( with );
				} catch (InvalidNavigationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
					throw new IllegalStateException(e);
				}
	        }       

		}
	
		public void replaceAll(
				final String prefix,
				final Map json) {
			for (final Object key : json.keySet() ) {
				final Object val = json.get(key);
				final String fqn = (prefix.trim().length() < 1 ? 
											String.valueOf(key) : 
											prefix + "." + key);
				
				if( val == null ) {
					System.err.println( "> skip key: " + fqn);
					continue;
				}
				
				if( val instanceof Map ) {
				    replaceAll(fqn, (Map) val);
				    continue;
				}
				
				final String name = String.valueOf(key);
				replace( 
						fqn, 
						String.valueOf(val) );			
			}
		}
	}