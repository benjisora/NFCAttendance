package com.iem.lp.attendancechecker;

import java.util.ArrayList;

/**
 * Created by aturlier on 31/05/17.
 */
public class DataRepository {
    private static DataRepository ourInstance = new DataRepository();

    public static DataRepository getInstance() {
        return ourInstance;
    }

    public ArrayList<User> userList;

    private DataRepository() {
        userList = new ArrayList<>();
    }
}
