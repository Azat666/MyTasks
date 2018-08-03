package com.example.student.mytasks.provaider;


import com.example.student.mytasks.models.Accounts;
import com.example.student.mytasks.models.ModelTasks;
import java.util.ArrayList;
import java.util.List;

public class UserProvaider {

    private static List<Accounts> list = new ArrayList<>();
    private static List<ModelTasks> listTasks = new ArrayList<>();

    public static List<ModelTasks> getListTasks() {
        return listTasks;
    }

    public static void setListTasks(List<ModelTasks> listTasks) {
        UserProvaider.listTasks = listTasks;
    }

    public static List<Accounts> getList() {
        return list;
    }

    public static void setList(List<Accounts> list) {
        UserProvaider.list = list;
    }
}
