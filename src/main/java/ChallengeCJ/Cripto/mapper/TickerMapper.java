package ChallengeCJ.Cripto.mapper;
import ChallengeCJ.Cripto.model.Ticker;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class TickerMapper extends Mapper<Ticker>{

    @Override
    public Ticker parseResponse(String response) throws IOException {
        JsonNode rootNode = objectMapper.readTree(response);
        JsonNode tickerNode = rootNode.get("result").get("data").get(0);


        double priceChange = tickerNode.get("c").asDouble();
        int tradedVolume24h = tickerNode.get("v").asInt();
        double price24hHigh = tickerNode.get("h").asDouble();
        double price24hLow = tickerNode.get("l").asDouble();

        return new Ticker(tradedVolume24h, price24hHigh, price24hLow,priceChange);
    }
}
