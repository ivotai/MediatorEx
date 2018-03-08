package com.unicorn.mediatorex.app;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class NobodyConverterFactory extends Converter.Factory {

    //将响应对象responseBody转成目标类型对象(也就是Call里给定的类型)
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                            Retrofit retrofit) {
        final Converter<ResponseBody, ?> delegate = retrofit.nextResponseBodyConverter(this, type, annotations);

        //判断当前的类型是否是我们需要处理的类型
        //是则创建一个Converter返回转换数据
        return new Converter<ResponseBody, Object>() {
            @Override
            public Object convert(@NonNull ResponseBody value) throws IOException {
                if (value.contentLength() == 0) {
                    return "{}";
                }
                //这里直接返回null是因为我们不需要使用到响应体,本来也没有响应体.
                //返回的对象会存到response.body()里.
                return delegate.convert(value);
            }
        };
    }

}