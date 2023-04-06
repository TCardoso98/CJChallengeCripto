package ChallengeCJ.Cripto.model;

import com.fasterxml.jackson.annotation.JsonProperty;



public record Weather(@JsonProperty("time") String[] time, @JsonProperty("temperature_2m") float[]temperature, @JsonProperty("relativehumidity_2m") int[] humidity) {

}
