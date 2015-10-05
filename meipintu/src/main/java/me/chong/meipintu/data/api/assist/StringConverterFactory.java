package me.chong.meipintu.data.api.assist;

import android.support.annotation.StringRes;
import android.util.Xml;

import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import me.chong.meipintu.util.RetrofitUtil;
import retrofit.Converter;

/**
 * Created by Chong on 2015/10/5.
 */
public class StringConverterFactory extends Converter.Factory {
    private StringConverterFactory() {}

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

    @Override public Converter<?, RequestBody> toRequestBody(Type type, Annotation[] annotations) {
        return null;
    }

    public static class StringConverter implements Converter<ResponseBody, String> {

        @Override
        public String convert(ResponseBody value) throws IOException {
            long contentLength = value.contentLength();
            if (contentLength < 0) {
                contentLength = value.bytes().length;
            }
            int bufferSize = 4096;
            char[] buffer = new char[bufferSize];
            int now = 0;

            StringBuilder builder = new StringBuilder();
            while (now < contentLength) {
                int read = value.charStream().read(buffer, now, bufferSize);
                builder.append(buffer, 0, read);
                now += read;
            }

            return builder.toString();
        }
    }
}
