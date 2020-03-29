package com.yingglish.enumtest.serialization;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

//@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@JsonSerialize(using = DistanceSerializer.class)
public enum Distance {
    KILOMETER("km", 1000),
    MILE("miles", 1609.34),
    METER("meters", 1),
    INCH("inches", 0.0254),
    CENTIMETER("cm", 0.01),
    MILLIMETER("mm", 0.001);

    private String unit;
    private final double meters;

    private Distance(String unit, double meters) {
        this.unit = unit;
        this.meters = meters;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getMeters() {
        return meters;
    }

    /**
     * Usage example: Distance.MILE.convertFromMeters(1205.5);
     */
    public double convertFromMeters(double distanceInMeters) {
        return distanceInMeters / meters;

    }

    /**
     * Usage example: Distance.MILE.convertToMeters(0.5);
     */
    public double convertToMeters(double distanceInMeters) {
        return distanceInMeters * meters;
    }

    public static void main(String[] args) throws JsonProcessingException {
        // 将枚举序列化为JSON
        String s = new ObjectMapper().writeValueAsString(Distance.MILE);
        System.out.println(s); // 不加@JsonFormat注释 输出 "MILE" 否则 {"unit":"miles","meters":1609.34}

    }
}
