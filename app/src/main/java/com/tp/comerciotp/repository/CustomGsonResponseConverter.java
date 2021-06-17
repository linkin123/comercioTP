package com.tp.comerciotp.repository;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.tp.comerciotp.utils.AESCryptoUtils;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

public class CustomGsonResponseConverter <T> implements Converter<ResponseBody, T> {

    private final Gson gson;
    private final TypeAdapter<T> adapter;

    CustomGsonResponseConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        try {
            String originalBody = value.string();
            // decrypt
            String body = AESCryptoUtils.decrypt(originalBody);
            Log.d("webService", body);
            //                        / / Get the code in json, pre-processing json
            JSONObject json = new JSONObject(body);
            int code = json.optInt("code");
            // When the code is not 0, set the data to {}, so the conversion will not go wrong.
            if (code != 0) {
                json.put("data", new JSONObject());
                body = json.toString();
            }

            return adapter.fromJson(body);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            value.close();
        }
    }
}

