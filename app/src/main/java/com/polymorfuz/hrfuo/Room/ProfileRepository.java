package com.polymorfuz.hrfuo.Room;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ProfileRepository {
    private ProfileDao profileDao;
    private LiveData<List<ProfileDB>>profile;

    ProfileRepository(Application application) {
        ProfileDatabase db=ProfileDatabase.getDatabase(application);
        profileDao=db.profileDao();
        profile=profileDao.getProfile();
    }
    LiveData<List<ProfileDB>>getProfile(){
        return profile;
    }
    void insertdata(ProfileDB profileDB){
        ProfileDatabase.databaseWrite.execute(()->{
            profileDao.insert(profileDB);
        });
    }
}
