package ua.dp.isd.ypys;

import java.io.IOException;

import ua.dp.isd.ypys.sevmeteo.R;
import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.widget.RemoteViews;

public class WeatherUpdatingService extends IntentService
{

	public WeatherUpdatingService()
	{
		super("Sevmeteo Svc");
	}

	@Override
	public void onHandleIntent(Intent intent)
	{
//		System.out.print("IntentService requested...");

		RemoteViews views = new RemoteViews(this.getPackageName(),
											R.layout.main);

		String currentTemperatureReadings = null;

		try
		{

			currentTemperatureReadings = WeatherParsingUtil.getTemperature();

			views.setTextViewText(R.id.temperature, currentTemperatureReadings);
			views.setTextColor(R.id.temperature, Color.WHITE);

		}
		catch (IOException e)
		{
//			System.err.println("Error accessing URL: http://pda.sevmeteo.info");
			views.setTextColor(R.id.temperature, Color.GRAY);
		}

//		System.out.println(currentTemperatureReadings);

		AppWidgetManager manager = AppWidgetManager.getInstance(this);
		ComponentName thisWidgetProvider =
						new ComponentName(this, WeatherWidgetProvider.class);
		manager.updateAppWidget(thisWidgetProvider, views);

	}

}
