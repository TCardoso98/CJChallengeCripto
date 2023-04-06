package ChallengeCJ.Cripto.mapper;

import ChallengeCJ.Cripto.Constants;
import ChallengeCJ.Cripto.model.Instrument;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InstrumentMapper extends Mapper<List<Instrument>>{
    private static InstrumentMapper singleton;
    public static InstrumentMapper getSingleton(){
        if(singleton!=null ) return singleton;
        return singleton = new InstrumentMapper();
    }

    private InstrumentMapper(){}
    @Override
    public List<Instrument> parseResponse(String response) throws IOException {
        JsonNode rootNode = objectMapper.readTree(response);
        JsonNode instrumentNode = rootNode.get("result").get("instruments");
        List<Instrument> instrumentList = new ArrayList<>();
        for (JsonNode instrument: instrumentNode) {
            instrumentList.add(new Instrument(instrument.get(Constants.Instrument.INSTRUMENT_NAME.label).asText()));
        }
        return instrumentList;
    }
}
