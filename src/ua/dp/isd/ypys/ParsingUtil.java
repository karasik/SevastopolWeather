package ua.dp.isd.ypys;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ParsingUtil
{
	// Prevent instantiation.
	private ParsingUtil()
	{
		throw new IllegalStateException("Bad boy");
	}

	public static String getTemperature() throws IOException
	{
		Document doc = Jsoup.connect(Globals.TEMP_URL).get();
		String result = fetchTemperature(doc);
		return result;
	}

	private static String fetchTemperature(Element element)
	{
		Elements innerElements = element
				.getElementsMatchingText(Globals.TEMP_SEARCH_TEXT);

		for (Element e : innerElements)
		{
			if (e.tag().getName().equals("div"))
			{
				String html = e.html();
				String[] data = html.split("<br>");
				// Temperature
				return data[4].split(":")[1].trim();
			}
		}

		return "ERROR";
	}
}
