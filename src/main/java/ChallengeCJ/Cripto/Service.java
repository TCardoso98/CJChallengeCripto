package ChallengeCJ.Cripto;

import ChallengeCJ.Cripto.mapper.CandleStickMapper;
import ChallengeCJ.Cripto.mapper.TickerMapper;
import ChallengeCJ.Cripto.model.CandleStick;
import ChallengeCJ.Cripto.model.Ticker;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

public class Service {

    private static final TickerMapper tickerMapper = new TickerMapper();
    private static final CandleStickMapper candleStickMapper = new CandleStickMapper();
    private static final RestTemplate restTemplate = new RestTemplate();
    public static List<CandleStick> getCandleStick(String instrumentName, String interval) throws IOException {
        String candleStickUrl = "https://api.crypto.com/v2/public/get-candlestick?instrument_name="
                + instrumentName + "&interval=" + interval;

        String candleStickResponse = restTemplate.getForObject(candleStickUrl, String.class);
        return candleStickMapper.parseResponse(candleStickResponse);
    }

    public static Ticker getTicker(String instrumentName) throws IOException {
        String tickerUrl = "https://api.crypto.com/v2/public/get-ticker?instrument_name="
                + instrumentName;

        String tickerResponse = restTemplate.getForObject(tickerUrl, String.class);
        return tickerMapper.parseResponse(tickerResponse);
    }
}
