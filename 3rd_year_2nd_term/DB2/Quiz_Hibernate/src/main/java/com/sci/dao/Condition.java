package com.sci.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Condition <T extends Comparable<T>> {
    private String columnName;
    private T value;
    private Operator operator;

    public enum Operator{
        EQ,GT,LT,NEQ,LIKE
    }
}
