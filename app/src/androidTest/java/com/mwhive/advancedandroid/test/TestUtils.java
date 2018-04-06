package com.mwhive.advancedandroid.test;


import com.mwhive.advancedandroid.model.AdapterFactory;
import com.mwhive.advancedandroid.model.ZonedDateTimeAdapter;
import com.squareup.moshi.Moshi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

import javax.inject.Inject;

/**
 * Created by MadWasp79 on 06-Apr-18.
 */
public class TestUtils {

    private final Moshi mMoshi;

    @Inject
    TestUtils(Moshi moshi) {

        mMoshi = moshi;
    }

    public <T> T loadJson(String path, Type type) {
        try {
            String json = getFileString(path);
            //noinspection unchecked
            return (T) mMoshi.adapter(type).fromJson(json);
        } catch (IOException e) {
            throw new IllegalArgumentException("Could not deserialize: " + path + " into type: " + type);
        }
    }

    public <T> T loadJson(String path, Class<T> clazz) {
        try {
            String json = getFileString(path);
            //noinspection unchecked
            return mMoshi.adapter(clazz).fromJson(json);
        } catch (IOException e) {
            throw new IllegalArgumentException("Could not deserialize: " + path + " into type: " + clazz);
        }
    }

    private String getFileString(String path) {
        try {
            StringBuilder sb = new StringBuilder();
            BufferedReader mReader = new BufferedReader(new InputStreamReader(
                    getClass().getClassLoader().getResourceAsStream(path)));
            String line;
            while ((line = mReader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
            throw new IllegalArgumentException("Could not read from resource at: " + path);
        }
    }
}

