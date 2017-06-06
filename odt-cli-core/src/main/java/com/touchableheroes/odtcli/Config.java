package com.touchableheroes.odtcli;

import java.io.File;
import java.io.InputStream;

import com.fasterxml.jackson.core.JsonGenerator;

public class Config {
		
		private final String configPath;
		private final String tmplName;
		private final String dataName;
		
		public Config(
				final String configPath,
				final String tmplName,
				final String dataName ) {
			this.configPath = configPath + "/" + tmplName;
			this.tmplName = tmplName + ".tmpl.odt";
			
			this.dataName = dataName;
		}
		
		public File getTemplate() {
			final File parent = new File( configPath );
			final File rval = new File( parent, tmplName );
			
			System.err.println( "> Check template-path: " + rval.getAbsolutePath() );
			
			if( !rval.exists() ) {
				System.err.println( "> [STOP] not exists!" );
				throw new IllegalStateException();
			}
			
			return rval;
		}

		public File getPrefillJSON() {
			final File parent = new File( configPath );
			
			final File rval = new File( parent, "prepare.json" );
			System.err.println( "> prepare.json -> " + rval.getAbsolutePath() );
			
			return rval;
		}

		public File getDataJSON() {
			final File parent = new File( configPath );
			
			// TODO: data needs to by dynamic.
			final File rval = new File( "./data.json" );
			System.err.println( "> prepare.json -> " + rval.getAbsolutePath() );
			
			return rval;
		}

		public File getPreparedPath() {
			return new File( "./prepared-doc.odt" );
		}

		public File getOutputPath() {
			return new File( "./output-doc.odt" );
		}

		public File getDataSpreadsheet() {
			final File parent = new File( this.configPath );
			return new File( parent, "./invoice-data.ods" );
		}

	}