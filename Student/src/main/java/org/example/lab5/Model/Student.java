package org.example.lab5.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class Student
{
    private Integer ID;
    private String name;
    private int age;
    private String degree;
    private double GPA;
}
