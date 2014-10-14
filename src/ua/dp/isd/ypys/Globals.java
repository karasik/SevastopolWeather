package ua.dp.isd.ypys;

public class Globals
{
	// Prevent instantiation.
	private Globals()
	{
		throw new IllegalStateException("Bad boy");
	}

	public static final String TEMP_PREFIX = "";
	public static final String TEMP_SUFFIX = "";
	public static final String TEMP_URL = "http://www.sevmeteo.info/data/temp.htm";
	public static final int UPDATE_INTERVAL = 5 * 60 * 1000;
	public static final String TEMP_SEARCH_TEXT = "Текущая температура воздуха";

}
