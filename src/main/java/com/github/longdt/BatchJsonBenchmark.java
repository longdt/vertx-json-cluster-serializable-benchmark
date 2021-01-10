/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.github.longdt;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@State(Scope.Benchmark)
public class BatchJsonBenchmark {
    volatile List<Buffer> buffers;
    volatile List<JsonObject> jsonObjects;
    volatile List<MyJsonObject> myJsonObjects;

    @Setup
    public void setup() {
        buffers = new CopyOnWriteArrayList<>();
        jsonObjects = new CopyOnWriteArrayList<>();
        myJsonObjects = new CopyOnWriteArrayList<>();
        JsonObject json = new JsonObject();
        for (int i = 0; i < 100; ++i) {
            json.put("attr" + i, i);
            Buffer jsonBuffer = json.toBuffer();
            buffers.add(Buffer.buffer(4 + jsonBuffer.length()).appendInt(jsonBuffer.length()).appendBuffer(jsonBuffer));
            JsonObject temp = json.copy();
            jsonObjects.add(temp);
            myJsonObjects.add(new MyJsonObject(temp.getMap()));
        }
    }

    @Benchmark
    public void readFromBuffer_JsonObject(Blackhole blackhole) {
        JsonObject json = new JsonObject();
        for (Buffer buffer : buffers) {
            json.readFromBuffer(0, buffer);
        }
        blackhole.consume(json);
    }

    @Benchmark
    public void readFromBuffer_MyJsonObject(Blackhole blackhole) {
        MyJsonObject json = new MyJsonObject();
        for (Buffer buffer : buffers) {
            json.readFromBuffer(0, buffer);
        }
        blackhole.consume(json);
    }

    @Benchmark
    public void writeToBuffer_JsonObject() {
        for (JsonObject json : jsonObjects) {
            json.writeToBuffer(Buffer.buffer());
        }
    }

    @Benchmark
    public void writeToBuffer_MyJsonObject() {
        for (MyJsonObject json : myJsonObjects) {
            json.writeToBuffer(Buffer.buffer());
        }
    }

}
