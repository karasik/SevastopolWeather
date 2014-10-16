package ua.dp.isd.ypys;

import java.io.IOException;

import ua.dp.isd.ypys.sevastopolweather.R;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

public class WeatherUpdatingService extends Service
{
	@Override
	public void onCreate()
	{
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		buildUpdate();
		return super.onStartCommand(intent, flags, startId);
	}

	private void buildUpdate()
	{
		System.out.println("Call update!");
		RemoteViews view = new RemoteViews(getPackageName(), R.layout.main);
		try
		{
			String current = WeatherParsingUtil.getTemperature();
			view.setTextViewText(R.id.temperature, current);
			ComponentName thisWidget = new ComponentName(this,
					WeatherWidgetProvider.class);
			AppWidgetManager manager = AppWidgetManager.getInstance(this);
			manager.updateAppWidget(thisWidget, view);
		}
		catch (IOException e)
		{
			System.out.println("Error accessing website");
		}
	}

	@Override
	public IBinder onBind(Intent intent)
	{
		return null;
	}
}
