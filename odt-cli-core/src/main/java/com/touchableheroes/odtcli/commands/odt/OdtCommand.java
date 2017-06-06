package com.touchableheroes.odtcli.commands.odt;

import org.odftoolkit.simple.TextDocument;

import com.touchableheroes.odtcli.commands.DocConverterCommand;

public interface OdtCommand<O>
		extends DocConverterCommand<TextDocument, O> {}
