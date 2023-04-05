package ChallengeCJ.Cripto.model;

public record Ticker(
        int tradedVolume,
        double highPrice24h,
        double lowPrice24h,
        double priceChange) { }
