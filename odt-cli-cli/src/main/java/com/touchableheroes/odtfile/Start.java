package com.touchableheroes.odtfile;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * Usage:
 * 
 * >odtcli convert -i <file> -o <file> -m <mapper-name>
 * 
 * @author asiebert
 */
public class Start {

	public static void main(String[] args) throws ParseException {

		final Options basicOpts = createOptions();

		// create the command line parser
		final CommandLineParser parser = new DefaultParser();
		final CommandLine parsed = parser.parse(basicOpts, args);

		if (parsed.hasOption("convert")) {
			final Options convertOpts = createConvertOptions();
			doConvert(new DefaultParser().parse(convertOpts, args));
			
			return;
		}
		
		printInfo();
	}

	private static void doConvert(CommandLine parse) {
		// TODO Auto-generated method stub

	}

	private static Options createConvertOptions() {
		OptionGroup group = new OptionGroup();
		group.addOption( new Option( "i", "input", true, "Input file." ) );
		group.addOption( new Option( "o", "output", true, "Output file.") );
		
		group.setRequired(true);

		Options rval = new Options();
		rval.addOptionGroup(group);
		
		return rval;
	}

	private static void printInfo() {
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("-- ODTCLI - Version 0.0.1                                            --");
		System.out.println("--                                                                   --");
		System.out.println("-- Author:  Andreas Siebert / ask@touchableheroes.com                --");
		System.out.println("-- License: Apache License                                           --");
		System.out.println("-- (c) 2017 by A. Siebert                                            --");
		System.out.println("-----------------------------------------------------------------------");
	}

	private static Options createOptions() {
		final Options options = new Options();

		options.addOption("c", "convert", false,
				"Convert input openoffice file to some output file (current version only works with json).");
		options.addOption("v", "version", false, "Shows the version/build of this application.");

		return options;
	}

}
