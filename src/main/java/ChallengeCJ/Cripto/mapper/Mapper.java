package ChallengeCJ.Cripto.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public abstract class Mapper<T> {
    protected final ObjectMapper objectMapper = new ObjectMapper();

    public abstract T parseResponse(String response) throws IOException;
}
