package com.yingglish.jacksontest.annotationtest;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;

import com.fasterxml.jackson.databind.ser.VirtualBeanPropertyWriter;
import com.fasterxml.jackson.databind.util.Annotations;
import org.junit.Test;

public class JsonAppendTest {


    @Test
    public void fun1() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        System.out.println(objectMapper.writeValueAsString(new Response(1L,"张三"))); // {"Id":1,"Name":"张三","remark":"我是一个虚拟字段的值"}
    }



    @JsonAppend(props = @JsonAppend.Prop(name = "remark", value = MyAgeVirtualBeanPropertyWriter.class))
    @JsonNaming(value = PropertyNamingStrategy.UpperCamelCaseStrategy.class) // 属性名大写
    private static class Response {
        private Long id;
        private String name;

        public Response(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public Response() {
        }

        @Override
        public String toString() {
            return "Response{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    // 生成虚拟属性值
    private static class MyAgeVirtualBeanPropertyWriter extends VirtualBeanPropertyWriter {

        // 必须有个无参构造 否则报错
        public MyAgeVirtualBeanPropertyWriter() {
        }

        private MyAgeVirtualBeanPropertyWriter(BeanPropertyDefinition propDef,
                                               Annotations contextAnnotations, JavaType declaredType) {
            super(propDef, contextAnnotations, declaredType);
        }

        @Override
        protected Object value(Object bean, JsonGenerator gen, SerializerProvider prov) throws Exception {
            return "我是一个虚拟字段的值";
        }

        @Override
        public VirtualBeanPropertyWriter withConfig(MapperConfig<?> config, AnnotatedClass declaringClass, BeanPropertyDefinition propDef, JavaType type) {
            return new MyAgeVirtualBeanPropertyWriter(propDef, null, type);
        }
    }
}
