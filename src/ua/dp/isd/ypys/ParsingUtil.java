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
		System.out.println(result);
		return result;
	}

	private static String fetchTemperature(Element element)
	{
		Elements innerElements = element
				.getElementsMatchingText(Globals.TEMP_SEARCH_TEXT);

		int strSize = Integer.MAX_VALUE;
		Element result = null;
		for (Element e : innerElements)
		{
			if (e.text().length() < strSize)
			{
				result = e;
				strSize = e.text().length();
			}
		}

		String inner = result.getElementsByTag("b").text();

		return inner.substring(0, inner.indexOf(' '));
	}
}
