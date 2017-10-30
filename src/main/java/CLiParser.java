import java.util.logging.Logger;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.CommandLine;
import java.util.logging.Level;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.HelpFormatter;

public class CLiParser
{

	private static final Logger log = Logger.getLogger(CLiParser.class.getName());
	private String[] 	args;
	private Options		options;
	private Object 		key;
	private boolean 	integerType;
	private Object[]	inputList;


	public Object 		getKey() 			{return key;}
	public Object[] 	getInputList()		{return inputList;}
	public boolean		isInteger()			{return integerType;}

	public CLiParser(String[] args)
	{
		options = new Options();
		this.args = args;
		options.addOption("h", "help", false, "show help.");
		options.addOption("t", "type", true, "Set type:“i” for integer and “s” for string.");
		options.addOption("k", "key", true, "Set search key.");
		Option option = new Option("l", "list", true, "Set your list of integers or strings.");
		option.setArgs(Option.UNLIMITED_VALUES);
		options.addOption(option);
	}

	public void parse()
	{
		CommandLineParser parser = new DefaultParser();
		CommandLine cmd;
		try 
		{
			cmd = parser.parse(options, args);

			if (cmd.hasOption("h"))
				help();
			if (cmd.hasOption("type")) 
				setIntegerType(cmd.getOptionValue("type"));			
			if (cmd.hasOption("key")) 
				setKey(cmd.getOptionValue("key"));
			if (cmd.hasOption("list")) 
			{
				setInputList(cmd.getOptionValues("list"));
			}

		} 
		catch (ParseException e) 
		{
			log.log(Level.SEVERE, "Failed to parse comand line properties", e);
			help();
		}
	}

	private void help() 
	{
		HelpFormatter formater = new HelpFormatter();

		formater.printHelp("Main", options);
		System.exit(0);
	}

		private void setIntegerType(String inputType)
	{
		if(inputType.equals("i"))
			integerType = true;
		else if (inputType.equals("s"))
			integerType = false;
		else
		{
			log.log(Level.SEVERE, "Wrong type: --type option accepts only 'i' or 's' not " + inputType);
			System.exit(0);
		}
	}

	private void setKey(String inputKey)
	{
		if(integerType)
			key = new Integer(inputKey);
		else
			key = new String(inputKey);
	}

	private void setInputList(String[] inputList)
	{
		if(integerType)
		{
			Integer[] intInputList = new Integer[inputList.length];
			for (int i=0; i < inputList.length; i++) 
			{
				try
				{
					intInputList[i] = new Integer(inputList[i]);
				}
				catch (NumberFormatException nfe) 
				{
					System.out.println(nfe);
				};
			}
			this.inputList = intInputList;
		}
		else
			this.inputList = inputList;
	}

}