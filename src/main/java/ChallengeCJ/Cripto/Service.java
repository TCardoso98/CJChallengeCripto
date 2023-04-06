package ChallengeCJ.Cripto;

import ChallengeCJ.Cripto.mapper.CandleStickMapper;
import ChallengeCJ.Cripto.mapper.InstrumentMapper;
import ChallengeCJ.Cripto.mapper.TickerMapper;
import ChallengeCJ.Cripto.model.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Date;
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
    private Date weatherInfoDay;
    public Weather getWeather() {
        if(weatherInfoDay == null || weatherInfoDay.getDay() < new Date().getDay()) {
            String weatherUrl = "https://api.open-meteo.com/v1/forecast?latitude=38.68&longitude=-9.16&hourly=temperature_2m,relativehumidity_2m";
            WeatherResponse weatherResponse = restTemplate.getForObject(weatherUrl, WeatherResponse.class);
            if(weatherResponse == null) throw new NullPointerException();
            weatherInfoDay = new Date();
            return weather = weatherResponse.hourly();
        }
        return weather;
    }
}
