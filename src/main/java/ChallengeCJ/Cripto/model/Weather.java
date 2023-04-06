package ChallengeCJ.Cripto.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;


public record Weather(@JsonProperty("time") Date[] time, @JsonProperty("temperature_2m") float[]temperature, @JsonProperty("relativehumidity_2m") int[] humidity) {

}
