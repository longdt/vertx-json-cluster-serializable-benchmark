package com.github.longdt;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import java.nio.charset.StandardCharsets;

@State(Scope.Thread)
public class RealJsonBenchmark {
    Buffer buffer;
    JsonObject jsonObject;
    MyJsonObject myJsonObject;

    @Setup
    public void setup() {
        //a json object from https://www.json-generator.com/
        String json = "{\"_id\":\"5ffb043382afae8705c31592\",\"index\":0,\"guid\":\"e8a3ecd9-b745-4660-82b6-3daada86d3a3\",\"isActive\":false,\"balance\":\"$3,183.32\",\"picture\":\"http://placehold.it/32x32\",\"age\":38,\"eyeColor\":\"brown\",\"name\":\"Deena Stanley\",\"gender\":\"female\",\"company\":\"EXOTECHNO\",\"email\":\"deenastanley@exotechno.com\",\"phone\":\"+1 (867) 493-2861\",\"address\":\"626 Hillel Place, Lorraine, South Carolina, 670\",\"about\":\"Eu dolore proident reprehenderit dolore enim dolore aliquip cillum deserunt. Et id magna mollit irure laborum adipisicing laboris consequat ad. Nisi sit dolor qui cillum laborum fugiat magna cupidatat qui amet est culpa reprehenderit nisi. Pariatur sit adipisicing excepteur laboris laboris mollit dolore nisi quis ut. Laboris id occaecat cupidatat nulla sint ea enim enim ut.\\r\\n\",\"registered\":\"2019-09-13T12:06:52 -07:00\",\"latitude\":-7.405681,\"longitude\":-110.160877,\"tags\":[\"nostrud\",\"cupidatat\",\"commodo\",\"laboris\",\"sunt\",\"proident\",\"excepteur\"],\"friends\":[{\"id\":0,\"name\":\"Lyons Obrien\"},{\"id\":1,\"name\":\"Mamie Crane\"},{\"id\":2,\"name\":\"Lori Malone\"}],\"greeting\":\"Hello, Deena Stanley! You have 2 unread messages.\",\"favoriteFruit\":\"apple\"}";
        jsonObject = new JsonObject(json);
        myJsonObject = new MyJsonObject(jsonObject.getMap());
        byte[] jsonBytes = json.getBytes(StandardCharsets.UTF_8);
        buffer = Buffer.buffer(jsonBytes.length + 4).appendInt(jsonBytes.length).appendBytes(jsonBytes);
    }

    @Benchmark
    public void readFromBuffer_JsonObject(Blackhole blackhole) {
        JsonObject json = new JsonObject();
        json.readFromBuffer(0, buffer);
        blackhole.consume(json);
    }

    @Benchmark
    public void readFromBuffer_MyJsonObject(Blackhole blackhole) {
        MyJsonObject json = new MyJsonObject();
        json.readFromBuffer(0, buffer);
        blackhole.consume(json);
    }

    @Benchmark
    public void writeToBuffer_JsonObject(Blackhole blackhole) {
        Buffer buffer = Buffer.buffer();
        jsonObject.writeToBuffer(buffer);
        blackhole.consume(buffer);
    }

    @Benchmark
    public void writeToBuffer_MyJsonObject(Blackhole blackhole) {
        Buffer buffer = Buffer.buffer();
        myJsonObject.writeToBuffer(buffer);
        blackhole.consume(buffer);
    }
}
