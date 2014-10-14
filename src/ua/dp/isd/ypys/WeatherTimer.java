package ua.dp.isd.ypys;

import java.io.IOException;
import java.util.TimerTask;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.widget.RemoteViews;

import com.example.sevastopolweather.R;

public class WeatherTimer extends TimerTask
{
	private Context context;

	public WeatherTimer(Context context)
	{
		this.context = context;
	}

	@Override
	public void run()
	{
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
				R.layout.main);
		ComponentName thisWidget = new ComponentName(context,
				SevastopolWeather.class);

		try
		{
			remoteViews.setTextViewText(R.id.temperature, Globals.TEMP_PREFIX
					+ ParsingUtil.getTemperature() + Globals.TEMP_SUFFIX);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		AppWidgetManager.getInstance(context).updateAppWidget(thisWidget,
				remoteViews);
	}
}
