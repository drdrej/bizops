package com.touchableheroes.odtcli.commands;

import com.touchableheroes.odtcli.Config;

public abstract class AbstractDocConverterCommand<I, O> 
       implements DocConverterCommand<I, O>{

	private final Config config;
	
	public AbstractDocConverterCommand( final Config config) {
		this.config = config;
	}
	
	public Config config() {
		return config;
	}
	
}
