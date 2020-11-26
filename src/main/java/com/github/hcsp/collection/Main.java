package com.github.hcsp.collection;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {
    // 请编写一个方法，对传入的List<User>进行如下处理：
    // 返回一个从部门名到这个部门的所有用户的映射。同一个部门的用户按照年龄进行从小到大排序。
    // 例如，传入的users是[{name=张三, department=技术部, age=40 }, {name=李四, department=技术部, age=30 },
    // {name=王五, department=市场部, age=40 }]
    // 返回如下映射：
    //    技术部 -> [{name=李四, department=技术部, age=30 }, {name=张三, department=技术部, age=40 }]
    //    市场部 -> [{name=王五, department=市场部, age=40 }]
    public static Map<String, List<User>> collect(List<User> users) {
        Map<String, List<User>> departMap = new HashMap<>();
        List<User> hasExistedDepartList = new ArrayList<User>();
        for (User currUser : users
        ) {
            if (!departMap.containsKey(currUser.getDepartment().toString())) {
                //当用户的部门不存在map的时候，将员工对象加入新的List
                List<User> notExistDepartList = new ArrayList<User>();
                notExistDepartList.add(currUser);
                departMap.put(currUser.getDepartment(), notExistDepartList);
                hasExistedDepartList = notExistDepartList;
            } else {
                //当用户的部门存在map的时候，将员工对象加入已经存在的List

                hasExistedDepartList.add(currUser);
                Collections.sort(hasExistedDepartList, new Comparator<User>() {
                    @Override
                    public int compare(User s1, User s2) {
                        int num = s1.getAge() - s2.getAge();
                        int num2 = num == 0 ? s1.getName().compareTo(s2.getName()) : num;
                        return num2;
                    }

                });
                departMap.put(currUser.getDepartment(), hasExistedDepartList);
            }

        }
        return departMap;
    }

    public static void main(String[] args) {
        System.out.println(
                collect(
                        Arrays.asList(
                                new User(1, "张三", 40, "技术部"),
                                new User(2, "李四", 30, "技术部"),
                                new User(3, "王五", 40, "市场部"))));
    }
}
