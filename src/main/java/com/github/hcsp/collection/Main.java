package com.github.hcsp.collection;

import java.util.*;

public class Main {
    // 请编写一个方法，对传入的List<User>进行如下处理：
    // 返回一个从部门名到这个部门的所有用户的映射。同一个部门的用户按照年龄进行从小到大排序。
    // 例如，传入的users是[{name=张三, department=技术部, age=40 }, {name=李四, department=技术部, age=30 },
    // {name=王五, department=市场部, age=40 }]
    // 返回如下映射：
    //    技术部 -> [{name=李四, department=技术部, age=30 }, {name=张三, department=技术部, age=40 }]
    //    市场部 -> [{name=王五, department=市场部, age=40 }]
    public static Map<String, List<User>> collect(List<User> users) {
        Map<String, List<User>> usersOfDepartment = new HashMap<>();
        for (User user : users) {
            if (!usersOfDepartment.containsKey(user.getDepartment())) {
                List<User> usersList = new ArrayList<>();
                usersOfDepartment.put(user.getDepartment(), usersList);
            }
            usersOfDepartment.get(user.getDepartment()).add(user);
        }
        for (Map.Entry<String, List<User>> entry : usersOfDepartment.entrySet()) {
            Collections.sort(entry.getValue(), Comparator.comparingInt(User::getAge));
        }
        return usersOfDepartment;
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
