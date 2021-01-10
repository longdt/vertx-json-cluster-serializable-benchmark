package com.github.longdt;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.Json;
import io.vertx.core.shareddata.impl.ClusterSerializable;

import java.util.LinkedHashMap;
import java.util.Map;

public class MyJsonObject implements ClusterSerializable {
    Map<String, Object> map;

    public MyJsonObject() {
        map = new LinkedHashMap<>();
    }

    public MyJsonObject(Map<String, Object> map) {
        if (map == null) {
            throw new NullPointerException();
        }
        this.map = map;
    }

    public MyJsonObject(Buffer buf) {
        if (buf == null) {
            throw new NullPointerException();
        }
        fromBuffer(buf);
        if (map == null) {
            throw new DecodeException("Invalid JSON object: " + buf);
        }
    }

    @Override
    public void writeToBuffer(Buffer buffer) {
        Buffers.appendJsonObject(buffer, this);
    }

    @Override
    public int readFromBuffer(int pos, Buffer buffer) {
        int length = buffer.getInt(pos);
        int start = pos + 4;
        Buffer buf = buffer.slice(start, start + length);
        fromBuffer(buf);
        return pos + length + 4;
    }

    @SuppressWarnings("unchecked")
    private void fromBuffer(Buffer buf) {
        map = Json.CODEC.fromBuffer(buf, Map.class);
    }

    public Map<String, Object> getMap() {
        return map;
    }
}
