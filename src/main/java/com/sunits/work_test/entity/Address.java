package com.sunits.work_test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private String stationCode;
    private String  cityName;
    private  String cityNameNext;
    private  String stationName;
    private String  stationType;
}
