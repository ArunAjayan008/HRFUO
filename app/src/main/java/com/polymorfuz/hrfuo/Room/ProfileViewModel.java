package com.polymorfuz.hrfuo.Room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ProfileViewModel extends AndroidViewModel {
    private ProfileRepository repository;
    private LiveData<List<ProfileDB>> getprofile;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        repository = new ProfileRepository(application);
        getprofile = repository.getProfile();
    }

    public LiveData<List<ProfileDB>> getprofiledata() {
        return getprofile;
    }
    public void insert(ProfileDB db) { repository.insertdata(db); }
}
