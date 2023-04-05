package ChallengeCJ.Cripto.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Instrument (@JsonProperty(value = "instrument_name") String instrumentName){
}
