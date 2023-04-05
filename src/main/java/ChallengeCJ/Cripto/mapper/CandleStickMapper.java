package ChallengeCJ.Cripto.mapper;
import ChallengeCJ.Cripto.model.CandleStick;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CandleStickMapper  extends Mapper<List<CandleStick>>{

    @Override
    public List<CandleStick> parseResponse(String response) throws IOException {
        JsonNode rootNode = objectMapper.readTree(response);
        JsonNode candleStickDataNode = rootNode.get("result").get("data");
        List<CandleStick> candleStickList = new ArrayList<>();
        for (JsonNode candleStick: candleStickDataNode) {
            candleStickList.add(new CandleStick(
                    candleStick.get("t").asInt(),
                    candleStick.get("o").asDouble(),
                    candleStick.get("h").asDouble(),
                    candleStick.get("l").asDouble(),
                    candleStick.get("c").asDouble(),
                    candleStick.get("v").asDouble()
            ));
        }
        return candleStickList;
       }

}
