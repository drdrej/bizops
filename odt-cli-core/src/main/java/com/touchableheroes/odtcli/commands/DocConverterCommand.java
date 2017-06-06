package com.touchableheroes.odtcli.commands;

public interface DocConverterCommand<I, O> {

	public O exec(final I workDoc);
	
}
