package com.yingglish.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class App {
    public static void main(String[] args){
        Person p1 = new Person();
        p1.setId(10);  //查询Id为10的用户

        Person p2 = new Person();
        p1.setUserName("二狗");

        Person p3 = new Person();
        p3.setEmail("1085963924@qq.com,7777@ssd");
        p3.setUserName("TOM");
       System.out.println(query(p3)); //SELECT * FROM user WHERE 1 = 1 AND userName AND userName = 'TOM'AND Email in('1085963924@qq.com','7777@ssd')
    }
    public static String  query(Person person){
        StringBuilder stringBuilder = new StringBuilder();
        // 获取class
        Class c = person.getClass();
        // 通过 Class 对象的 isAnnotationPresent() 方法判断它是否应用了某个注解
        boolean isExist = c.isAnnotationPresent(Table.class);
        //System.out.println(isExist);

        if(!isExist){
            return null;
        }else {
            // 通过 getAnnotation() 方法来获取 Annotation 对象
            Table table = (Table)c.getAnnotation(Table.class);
            String tableName = table.value(); // 获取table名
            stringBuilder.append("SELECT * FROM ").append(tableName).append(" WHERE 1 = 1 "); //添加WHERE 防止报错
            // 获取当前类 所有声明的字段，即包括public、private和protected，但是不包括父类的声明字段
            Field[] fields = c.getDeclaredFields(); // getFields()：获得某个类的所有的公共（public）的字段，包括父类中的字段
            //System.out.println(fields);
            for (Field field : fields){
                /**
                 * 处理每个字段对应的SQL 获取字段名---> 2. 获取字段值 ---> 3.拼装
                 */
                // 字段上是否应用注解
                boolean fIsExist = field.isAnnotationPresent(Column.class);
                //System.out.println(fIsExist);
                if(!fIsExist){
                    continue;
                }else {
                    // 1. 获取 成员变量上的注解
                    Column column = field.getAnnotation(Column.class);
                    String columnName = column.value(); // id userName age Email

                    //System.out.println(columnName);
                    // 获取字段名
                    String fieldName = field.getName();
                    //System.out.print(fieldName + " ");  //id userName age Email

                    // 获取字段的修饰符
                    // int fieldValue = field.getModifiers();//如：private、static、final等

                    // getter method
                    String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    Object fieldValue = null;

                    try {
                        Method method = c.getMethod(getMethodName);
                        fieldValue = method.invoke(person);  //相当与调用getXxx()方法
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if(fieldValue == null ||(fieldValue instanceof Integer && (Integer)fieldValue == 0)){
                        continue;
                    }
                    stringBuilder.append("AND ").append(fieldName);
                    if(fieldValue instanceof String){
                        if(((String) fieldValue).contains(",")){
                            String[] values = ((String) fieldValue).split(",");
                            stringBuilder.append(" in(");
                            for (String value : values){
                                stringBuilder.append("'").append(value).append("'").append(",");
                            }
                            stringBuilder.deleteCharAt(stringBuilder.length()-1); //删除最后一个逗号
                            stringBuilder.append(")");
                         }else {
                        stringBuilder.append(" AND ").append(fieldName).append(" = ").append("'").append(fieldValue).append("'");
                        }
                    }else {
                    stringBuilder.append(" AND ").append(fieldName).append(" = ").append(fieldValue);//拼装
                         }
                }
            }
        }
       return stringBuilder.toString();
    }
}
