package ml.smart_ideas.smarthome.ws.rest;

/**
 * Created by Admin on 5.11.2015..
 */
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

import retrofit.Converter;

public final class ToStringConverter implements Converter<String> {

    @Override
    public String fromBody(ResponseBody body) throws IOException {
        return body.string();
    }

    @Override
    public RequestBody toBody(String value) {
        return RequestBody.create(MediaType.parse("text/plain"), value);
    }
}
