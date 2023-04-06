package ChallengeCJ.Cripto.mapper;
import ChallengeCJ.Cripto.Constants;
import ChallengeCJ.Cripto.model.Ticker;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class TickerMapper extends Mapper<Ticker>{
    private static TickerMapper singleton;
    private TickerMapper(){}
    public static TickerMapper getSingleton(){
        if(singleton != null) return singleton;
        return singleton = new TickerMapper();
    }
    @Override
    public Ticker parseResponse(String response) throws IOException {
        JsonNode rootNode = objectMapper.readTree(response);
        //create constant class
        JsonNode tickerNode = rootNode.get("result").get("data").get(0);

        double priceChange = tickerNode.get(Constants.Ticker.PRICE_CHANGE.label).asDouble();
        int tradedVolume24h = tickerNode.get(Constants.Ticker.VOLUME_24H.label).asInt();
        double price24hHigh = tickerNode.get(Constants.Ticker.HIGHEST_PRICE_24H.label).asDouble();
        double price24hLow = tickerNode.get(Constants.Ticker.LOWEST_PRICE_24H.label).asDouble();

        return new Ticker(tradedVolume24h, price24hHigh, price24hLow,priceChange);
    }
}
