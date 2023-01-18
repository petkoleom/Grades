package com.dvs.grades;

import androidx.annotation.NonNull;
import androidx.room.*;

import java.util.*;

@Entity(tableName = "subjectTable")
public class Subject {

    String name;
    public Subject(@NonNull String name){
        this.name = name;
    }

    @ColumnInfo
    @PrimaryKey(autoGenerate = true)
    Long id;



    @ColumnInfo
    public double average;
    @ColumnInfo
    public int points;

    //public LinkedList<Grade> gradesList;


}




