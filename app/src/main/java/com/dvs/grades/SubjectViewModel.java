package com.dvs.grades;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class SubjectViewModel extends AndroidViewModel {

    private SubjectRepository mRepository;

    private final LiveData<List<Subject>> mAllSubjects;

    public SubjectViewModel (Application application) {
        super(application);
        mRepository = new SubjectRepository(application);
        mAllSubjects = mRepository.getAllWords();
    }



    LiveData<List<Subject>> getAllWords() { return mAllSubjects; }

    public void insert(Subject subject) { mRepository.insert(subject); }
}

