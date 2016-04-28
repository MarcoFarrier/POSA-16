package vandy.mooc.model.aidl;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import vandy.mooc.model.aidl.WeatherData.Main;
import vandy.mooc.model.aidl.WeatherData.Sys;
import vandy.mooc.model.aidl.WeatherData.Weather;
import vandy.mooc.model.aidl.WeatherData.Wind;
import android.util.JsonReader;
import android.util.JsonToken;

/**
 * Parses the Json weather data returned from the Weather Services API
 * and returns a List of WeatherData objects that contain this data.
 */
public class WeatherDataJsonParser {
    /**
     * Used for logging purposes.
     */
    private final String TAG =
        this.getClass().getCanonicalName();

    /**
     * Parse the @a inputStream and convert it into a List of JsonWeather
     * objects.
     */
    public List<WeatherData> parseJsonStream(InputStream inputStream)
        throws IOException {

        // TODO -- you fill in here.
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

        try {
            return parseJsonWeatherDataArray(reader);
        } finally {
            reader.close();
        }
    }

    /**
     * Parse a Json stream and convert it into a List of WeatherData
     * objects.
     */
    public List<WeatherData> parseJsonWeatherDataArray(JsonReader reader)
        throws IOException {
        // TODO -- you fill in here.

        List<WeatherData> list = new ArrayList<WeatherData>();
        list.add(parseJsonWeatherData(reader));
        return list;
    }

    /**
     * Parse a Json stream and return a WeatherData object.
     */
    public WeatherData parseJsonWeatherData(JsonReader reader) 
        throws IOException {

        // TODO -- you fill in here.
        WeatherData data = new WeatherData();

        reader.beginObject();

        while (reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case WeatherData.name_JSON:
                    data.setName(reader.nextString());
                    break;
                case WeatherData.cod_JSON:
                    data.setCod(reader.nextLong());
                    break;
                case WeatherData.dt_JSON:
                    data.setDate(reader.nextLong());
                    break;
                case WeatherData.message_JSON:
                    data.setMessage(reader.nextString());
                    break;
                case WeatherData.weather_JSON:
                    data.setWeathers(parseWeathers(reader));
                    break;
                case WeatherData.sys_JSON:
                    data.setSys(parseSys(reader));
                    break;
                case WeatherData.wind_JSON:
                    data.setWind(parseWind(reader));
                    break;
                case WeatherData.main_JSON:
                    data.setMain(parseMain(reader));
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }

        reader.endObject();
        return data;
    }
    
    /**
     * Parse a Json stream and return a List of Weather objects.
     */
    public List<Weather> parseWeathers(JsonReader reader) throws IOException {
        // TODO -- you fill in here.

        List<Weather> list = new ArrayList<Weather>();
        reader.beginArray();
        while(reader.hasNext())
        {
            list.add(parseWeather(reader));
        }
        reader.endArray();
        return list;
    }

    /**
     * Parse a Json stream and return a Weather object.
     */
    public Weather parseWeather(JsonReader reader) throws IOException {
        // TODO -- you fill in here.
        Weather weather = new Weather();
        reader.beginObject();;
        while (reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case Weather.id_JSON:
                    weather.setId(reader.nextLong());
                    break;
                case Weather.description_JSON:
                    weather.setDescription(reader.nextString());
                    break;
                case Weather.icon_JSON:
                    weather.setIcon(reader.nextString());
                    break;
                case Weather.main_JSON:
                    weather.setMain(reader.nextString());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return weather;
    }

    /**
     * Parse a Json stream and return a Main Object.
     */
    public Main parseMain(JsonReader reader) 
        throws IOException {
        // TODO -- you fill in here.
        Main main = new Main();
        reader.beginObject();

        while (reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case Main.humidity_JSON:
                    main.setHumidity(reader.nextLong());
                    break;
                case Main.pressure_JSON:
                    main.setPressure(reader.nextDouble());
                    break;
                case Main.temp_JSON:
                    main.setTemp(reader.nextDouble());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }

        reader.endObject();
        return main;
    }

    /**
     * Parse a Json stream and return a Wind Object.
     */
    public Wind parseWind(JsonReader reader) throws IOException {
        // TODO -- you fill in here.
        Wind wind = new Wind();
        reader.beginObject();

        while (reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case Wind.deg_JSON:
                    wind.setDeg(reader.nextDouble());
                    break;
                case Wind.speed_JSON:
                    wind.setSpeed(reader.nextDouble());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }

        reader.endObject();
        return wind;
    }

    /**
     * Parse a Json stream and return a Sys Object.
     */
    public Sys parseSys(JsonReader reader)
        throws IOException {
        // TODO -- you fill in here.
        Sys sys = new Sys();
        reader.beginObject();

        while (reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case Sys.country_JSON:
                    sys.setCountry(reader.nextString());
                    break;
                case Sys.message_JSON:
                    sys.setMessage(reader.nextDouble());
                    break;
                case Sys.sunrise_JSON:
                    sys.setSunrise(reader.nextLong());
                    break;
                case Sys.sunset_JSON:
                    sys.setSunset(reader.nextLong());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }

        reader.endObject();
        return sys;
    }
}
