package ua.dp.isd.ypys;

import java.io.IOException;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.graphics.Color;
import android.widget.RemoteViews;

public class UpdatingThread extends Thread
{
	private Service parentService;
	private int layout;
	private int textView;

	public UpdatingThread(Service parentService, int layout, int textView)
	{
		super();
		this.parentService = parentService;
		this.layout = layout;
		this.textView = textView;
	}

	@Override
	public void run()
	{
		String current = null;
		try
		{
			current = WeatherParsingUtil.getTemperature();
		}
		catch (IOException e)
		{
			System.err.println("Error accessing website");
		}

		System.out.println(current);

		RemoteViews view = new RemoteViews(parentService.getPackageName(),
				layout);
		if (current != null)
		{
			view.setTextViewText(textView, current);
			view.setTextColor(textView, Color.WHITE);
		}
		else
		{
			view.setTextColor(textView, Color.YELLOW);
		}
		ComponentName thisWidget = new ComponentName(parentService,
				WeatherWidgetProvider.class);
		AppWidgetManager manager = AppWidgetManager.getInstance(parentService);
		manager.updateAppWidget(thisWidget, view);
	}
}