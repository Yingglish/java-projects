package com.yingglish.jacksontest.character01;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.yingglish.jacksontest.ref.Person;

import java.io.File;
import java.io.IOException;

public class JsonParserReadValueAsTest {
    public static void main(String[] args) throws IOException {
        JsonFactory factory = new JsonFactory();

        // 此处InputStream来自于文件
        JsonParser jsonParser = factory.createParser(new File("person.json"));
        jsonParser.setCodec(new MyObjectCodec()); // 若使用readValueAs系列方法，必须指定解码器

        Person person = jsonParser.readValueAs(Person.class);
        System.out.println(person);

        // 关闭IO流
        jsonParser.close();
    }
}
