package ChallengeCJ.Cripto.mapper;
import ChallengeCJ.Cripto.Constants;
import ChallengeCJ.Cripto.model.CandleStick;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CandleStickMapper  extends Mapper<List<CandleStick>>{
    private CandleStickMapper(){}
    private static CandleStickMapper singleton;
    public static CandleStickMapper getSingleton() {
        if(singleton != null) return singleton;
        return singleton = new CandleStickMapper();
    }
    @Override
    public List<CandleStick> parseResponse(String response) throws IOException {
        JsonNode rootNode = objectMapper.readTree(response);
        JsonNode candleStickDataNode = rootNode.get("result").get("data");
        List<CandleStick> candleStickList = new ArrayList<>();
        for (JsonNode candleStick: candleStickDataNode) {
            candleStickList.add(new CandleStick(
                    candleStick.get(Constants.CandleStick.END_TIME.label).asInt(),
                    candleStick.get(Constants.CandleStick.OPEN.label).asDouble(),
                    candleStick.get(Constants.CandleStick.HIGH.label).asDouble(),
                    candleStick.get(Constants.CandleStick.LOW.label).asDouble(),
                    candleStick.get(Constants.CandleStick.CLOSE.label).asDouble(),
                    candleStick.get(Constants.CandleStick.VOLUME.label).asDouble()
            ));
        }
        return candleStickList;
       }


}
