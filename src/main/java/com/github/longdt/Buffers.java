package com.github.longdt;

import io.netty.buffer.ByteBuf;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.buffer.impl.BufferImpl;

public class Buffers {
    public static Buffer appendJsonObject(Buffer buffer, MyJsonObject jsonObject) {
        ByteBuf buf = ((BufferImpl) buffer).byteBuf();
        int pos = buf.writerIndex();
        buf.writeInt(0);
        int length = MyJacksonCodec.toByteBuf(jsonObject.getMap(), buf,  pos,false);
        buf.setInt(pos, length);
        return buffer;
    }
}
