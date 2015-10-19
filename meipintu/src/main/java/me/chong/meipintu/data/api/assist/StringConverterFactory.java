package me.chong.meipintu.data.api.assist;

import android.support.annotation.StringRes;
import android.util.Xml;

import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import retrofit.Converter;

/**
 * Created by Chong on 2015/10/5.
 */
public class StringConverterFactory extends Converter.Factory {
    private StringConverterFactory() {
    }

    public static StringConverterFactory create() {
        return new StringConverterFactory();
    }

    @Override
    public Converter<ResponseBody, ?> fromResponseBody(Type type, Annotation[] annotations) {
        if (String.class.equals(type)) {
            return new StringConverter();
        }
        return null;
    }

    @Override
    public Converter<?, RequestBody> toRequestBody(Type type, Annotation[] annotations) {
        return null;
    }

    public static class StringConverter implements Converter<ResponseBody, String> {

        @Override
        public String convert(ResponseBody value) throws IOException {
            return new String(value.bytes(), "UTF-8");
        }
    }
}
