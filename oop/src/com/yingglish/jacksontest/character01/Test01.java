package com.yingglish.jacksontest.character01;

import com.fasterxml.jackson.core.*;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * JsonFactory：Jackson主要的工厂方法，用于配置和构建解析器(JsonParser)和生成器(JsonGenerator)，这个工厂实例是线程安全的，所以可以重复使用
 * JsonGenerator：用来生成Json格式的内容的（序列化）
 * JsonParser：读取Json格式的内容（返序列化，必须是Json格式）
 */
public class Test01 {

    /**
     * JsonGenerator 写
     * @throws IOException
     */
    @Test
    public void jsonGeneratorWriteTest() throws IOException {
        JsonFactory factory = new JsonFactory();

        // 此处最终输输出到OutputStreams输出流（此处输出到文件）
        JsonGenerator jsonGenerator = factory.createGenerator(new File("person.json"), JsonEncoding.UTF8);
        jsonGenerator.writeStartObject(); //开始写，也就是这个符号 {

        jsonGenerator.writeStringField("name", "法王");
        jsonGenerator.writeNumberField("age", 18);

        // 写入Dog对象（枚举对象）
        jsonGenerator.writeFieldName("dog");
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("name", "旺财");
        jsonGenerator.writeStringField("color", "WHITE");
        jsonGenerator.writeEndObject();

        //写入一个数组格式
        jsonGenerator.writeFieldName("hobbies"); // "hobbies" :
        jsonGenerator.writeStartArray(); // [
        jsonGenerator.writeString("篮球"); // "篮球"
        jsonGenerator.writeString("football"); // "football"
        jsonGenerator.writeEndArray(); // ]

        jsonGenerator.writeEndObject(); //结束写，也就是这个符号 }

        // 关闭IO流
        jsonGenerator.close();
    }

    @Test
    public void jsonParserReadTest() throws IOException {
        // 1、直接new的方式
        JsonFactory factory = new JsonFactory();

        // 2、更具弹性的SPI方式
//        JsonFactory factory = null;
//        ServiceLoader<JsonFactory> load = ServiceLoader.load(JsonFactory.class);
//        Iterator<JsonFactory> it = load.iterator();
//        if (it.hasNext()) { // 此处是if不是while，因为我只需要一个而已
//            factory = it.next();
//        }

        // 此处InputStream来自于文件
        JsonParser jsonParser = factory.createParser(new File("person.json"));

        // 只要还没到末尾，也就是}这个符号，就一直读取
        // {"name":"YourBatman","age":18,"dog":{"name":"旺财","color":"WHITE"},"hobbies":["篮球","football"]}
        JsonToken jsonToken = null; // token类型
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String fieldname = jsonParser.getCurrentName();
            if ("name".equals(fieldname)) {
                jsonToken = jsonParser.nextToken();
                System.out.println("token类型是：" + jsonToken);
                System.out.println(jsonParser.getText());
            } else if ("age".equals(fieldname)) {
                jsonToken = jsonParser.nextToken();
                System.out.println("token类型是：" + jsonToken);
                System.out.println(jsonParser.getIntValue());
            } else if ("dog".equals(fieldname)) {
                jsonToken = jsonParser.nextToken();
                System.out.println("token类型是：" + jsonToken);
                while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                    String dogFieldName = jsonParser.getCurrentName();
                    if ("name".equals(dogFieldName)) {
                        jsonToken = jsonParser.nextToken();
                        System.out.println("token类型是：" + jsonToken);
                        System.out.println(jsonParser.getText());
                    } else if ("color".equals(dogFieldName)) {
                        jsonToken = jsonParser.nextToken();
                        System.out.println("token类型是：" + jsonToken);
                        System.out.println(jsonParser.getText());
                    }
                }
            } else if ("hobbies".equals(fieldname)) {
                jsonToken = jsonParser.nextToken();
                System.out.println("token类型是：" + jsonToken);
                while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                    System.out.println(jsonParser.getText());
                }
            }
        }

        // 关闭IO流
        jsonParser.close();
    }
}
