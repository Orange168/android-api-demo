package com.orange.m;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by Edward Lin
 * on 6/17/15 12:40 AM.
 */
public class User extends BaseObservable {
    private String firstName;
    private String lastName;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Bindable
    public String getFirstName() {
        return this.firstName;
    }

    @Bindable
    public String getLastName() {
        return this.lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        notifyPropertyChanged(com.orange.m.BR.firstName);
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        notifyPropertyChanged(com.orange.m.BR.lastName);
    }
}

