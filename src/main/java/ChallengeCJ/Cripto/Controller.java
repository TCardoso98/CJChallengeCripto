package ChallengeCJ.Cripto;

import ChallengeCJ.Cripto.model.CandleStick;
import ChallengeCJ.Cripto.model.Ticker;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Controller {

    @GetMapping("/get-ticker")
    public String index() {
        return "Greetings from Spring Boot!";
    }



    @GetMapping("/trading-pair")
    public ResponseEntity<?> getTradingPairInfo(@RequestParam("instrument_name") String instrumentName,@RequestParam("interval") String interval) {
        try {
            Map<String, Object> combinedResponse = new HashMap<>();
            combinedResponse.put("instrument_name", instrumentName);
            combinedResponse.put("ticker", Service.getTicker(instrumentName));
            combinedResponse.put("candleStick", Service.getCandleStick(instrumentName,interval));
            return ResponseEntity.ok(combinedResponse);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

  /*  @GetMapping("/pairs")
    public String getAllPairs(){
        return ResponseEntity.ok();
    }*/


}