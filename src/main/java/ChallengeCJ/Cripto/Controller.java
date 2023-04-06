package ChallengeCJ.Cripto;

import ChallengeCJ.Cripto.model.Instrument;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Controller {

    @GetMapping("/pairs/{instrumentName}")
    public ResponseEntity<?> getTradingPairInfo(@PathVariable String instrumentName, @RequestParam(value = "interval",defaultValue = "1M") String interval) {
        try {
            Map<String, Object> combinedResponse = new HashMap<>();
            combinedResponse.put("instrument_name", instrumentName);
            combinedResponse.put("interval", interval);
            combinedResponse.put("ticker", Service.getSingleton().getTicker(instrumentName));
            combinedResponse.put("candleStick", Service.getSingleton().getCandleSticks(instrumentName,interval));
            combinedResponse.put("Suggestion", Service.getSingleton().getSuggestion() ? "Buy" : "Sell");
            return ResponseEntity.ok(combinedResponse);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/pairs")
    public ResponseEntity<List<Instrument>> getAllPairs(){
        try {
            return ResponseEntity.ok(Service.getSingleton().getInstruments());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}