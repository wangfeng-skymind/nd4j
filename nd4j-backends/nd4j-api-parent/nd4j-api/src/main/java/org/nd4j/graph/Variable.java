// automatically generated by the FlatBuffers compiler, do not modify

package org.nd4j.graph;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

@SuppressWarnings("unused")
public final class Variable extends Table {
  public static Variable getRootAsVariable(ByteBuffer _bb) { return getRootAsVariable(_bb, new Variable()); }
  public static Variable getRootAsVariable(ByteBuffer _bb, Variable obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; }
  public Variable __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  public long id() { int o = __offset(4); return o != 0 ? bb.getLong(o + bb_pos) : 0L; }
  public String name() { int o = __offset(6); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer nameAsByteBuffer() { return __vector_as_bytebuffer(6, 1); }
  public int shape(int j) { int o = __offset(8); return o != 0 ? bb.getInt(__vector(o) + j * 4) : 0; }
  public int shapeLength() { int o = __offset(8); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer shapeAsByteBuffer() { return __vector_as_bytebuffer(8, 4); }
  public float values(int j) { int o = __offset(10); return o != 0 ? bb.getFloat(__vector(o) + j * 4) : 0; }
  public int valuesLength() { int o = __offset(10); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer valuesAsByteBuffer() { return __vector_as_bytebuffer(10, 4); }

  public static int createVariable(FlatBufferBuilder builder,
      long id,
      int nameOffset,
      int shapeOffset,
      int valuesOffset) {
    builder.startObject(4);
    Variable.addId(builder, id);
    Variable.addValues(builder, valuesOffset);
    Variable.addShape(builder, shapeOffset);
    Variable.addName(builder, nameOffset);
    return Variable.endVariable(builder);
  }

  public static void startVariable(FlatBufferBuilder builder) { builder.startObject(4); }
  public static void addId(FlatBufferBuilder builder, long id) { builder.addLong(0, id, 0L); }
  public static void addName(FlatBufferBuilder builder, int nameOffset) { builder.addOffset(1, nameOffset, 0); }
  public static void addShape(FlatBufferBuilder builder, int shapeOffset) { builder.addOffset(2, shapeOffset, 0); }
  public static int createShapeVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addInt(data[i]); return builder.endVector(); }
  public static void startShapeVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static void addValues(FlatBufferBuilder builder, int valuesOffset) { builder.addOffset(3, valuesOffset, 0); }
  public static int createValuesVector(FlatBufferBuilder builder, float[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addFloat(data[i]); return builder.endVector(); }
  public static void startValuesVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static int endVariable(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
}

