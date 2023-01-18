package com.dvs.grades;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class SubjectRepository {
    private SubjectDAO mSubjectDao;
    private LiveData<List<Subject>> mAllSubjects;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    SubjectRepository(Application application) {
        MyDatabase db = MyDatabase.getDatabase(application);
        mSubjectDao = db.subjectDAO();

    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Subject>> getAllWords() {
        return mAllSubjects;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(Subject subject) {
        MyDatabase.databaseWriteExecutor.execute(() -> {
            mSubjectDao.insert(subject);
        });
    }

}
