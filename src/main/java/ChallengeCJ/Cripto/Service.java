package ChallengeCJ.Cripto;

import ChallengeCJ.Cripto.mapper.CandleStickMapper;
import ChallengeCJ.Cripto.mapper.InstrumentMapper;
import ChallengeCJ.Cripto.mapper.TickerMapper;
import ChallengeCJ.Cripto.model.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;

import java.util.List;
@org.springframework.stereotype.Service
public class Service {

    private static Service singleton;
    private  Service(){
        restTemplate = new RestTemplate();
    }
    public static Service getSingleton(){
        if(singleton != null) return singleton;
        return singleton = new Service();
    }

    private final RestTemplate restTemplate;
    public List<CandleStick> getCandleSticks(String instrumentName, String interval) throws IOException {
        String candleStickUrl = "https://api.crypto.com/v2/public/get-candlestick?instrument_name="
                + instrumentName + "&interval=" + interval;

        String candleStickResponse = restTemplate.getForObject(candleStickUrl, String.class);
        return CandleStickMapper.getSingleton().parseResponse(candleStickResponse);
    }

    public Ticker getTicker(String instrumentName) throws IOException {
        String tickerUrl = "https://api.crypto.com/v2/public/get-ticker?instrument_name="
                + instrumentName;

        String tickerResponse = restTemplate.getForObject(tickerUrl, String.class);
        return TickerMapper.getSingleton().parseResponse(tickerResponse);
    }

    public List<Instrument> getInstruments() throws IOException {
       String instrumentsUrl =  "https://api.crypto.com/v2/public/get-instruments";
        String instrumentsResponse = restTemplate.getForObject(instrumentsUrl, String.class);
        return InstrumentMapper.getSingleton().parseResponse(instrumentsResponse);
    }
    private Weather weather;
    private LocalDateTime weatherInfoDay;
    private Weather getWeather() {
        if(weatherInfoDay == null || weatherInfoDay.getDayOfMonth() < LocalDateTime.now().getDayOfMonth()) {
            String weatherUrl = "https://api.open-meteo.com/v1/forecast?latitude=38.68&longitude=-9.16&hourly=temperature_2m,relativehumidity_2m";
            WeatherResponse weatherResponse = restTemplate.getForObject(weatherUrl, WeatherResponse.class);
            if(weatherResponse == null) throw new NullPointerException();
            weatherInfoDay = LocalDateTime.now();
            return weather = weatherResponse.hourly();
        }
        return weather;
    }

    public boolean getSuggestion(){
        Weather weather = getWeather();
        return suggestionAlgorithm(weather);
    }

    private boolean suggestionAlgorithm(Weather weather) {
        int index = getArrayIndex(weather);
        if(index < 0) throw new RuntimeException("Weather time does not have current hour");
        double temperature = weather.temperature()[index];
        int humidity = weather.humidity()[index];

        double algorithmResult = temperature * temperature % humidity;
        System.out.printf("Temperature: %f %n Humidity: %d %n Algo: %f",temperature,humidity,algorithmResult);
        return temperature*2 % humidity > 10;
    }

    private int getArrayIndex(Weather weather) {
        for (int i = 0; i < weather.time().length; i++) {
            if(LocalDateTime.now().getHour() == getTimeHour(weather.time()[i])) return i;
        }
        return -1;
    }

    private int getTimeHour(String datetime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(datetime, formatter);
       return dateTime.getHour();
    }
}
