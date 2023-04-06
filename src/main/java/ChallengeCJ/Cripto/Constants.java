package ChallengeCJ.Cripto;

public class Constants {
    public enum Ticker{
        PRICE_CHANGE("c"),
        VOLUME_24H("v"),
        HIGHEST_PRICE_24H("h"),
        LOWEST_PRICE_24H("l");
        public final String label;
        private Ticker(String label){
            this.label = label;
        }
    }
    public enum CandleStick{
        END_TIME("t"),
        OPEN("o"),
        HIGH("h"),
        LOW("l"),
        CLOSE("c"),
        VOLUME("v");
        public final String label;
        private CandleStick(String label){
            this.label = label;
        }
    }

    public enum Instrument{
        INSTRUMENT_NAME("instrument_name"),
        API_URL("https://api.crypto.com/v2/public/get-instruments");

        public final String label;
        private Instrument(String label){
            this.label = label;
        }
    }
}
