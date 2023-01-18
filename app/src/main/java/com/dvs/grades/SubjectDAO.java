package com.dvs.grades;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SubjectDAO {
    @Query("SELECT * FROM Subject")
    static List<Subject> getAll() {
        return getAll();
    }


    @Query("SELECT * FROM subject WHERE id IN (:userIds)")
    List<Subject> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM Subject WHERE name LIKE :name LIMIT 1")
    Subject findByName(String name);

    @Insert
    void insertAll(Subject... subjects);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Subject subject);


    @Delete
    void delete(Subject subject);

    @Query("DELETE FROM subjectTable")
    void deleteAll();

}
