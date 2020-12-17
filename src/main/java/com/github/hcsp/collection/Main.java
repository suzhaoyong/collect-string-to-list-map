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
        //创建一个Map<String, List<User>>集合
        Map<String, List<User>> map = new HashMap<>();
        //创建一个接收部门的变量
        String department;
        // 插入
        for (User user : users) {
            // 获取user中的部门
            department = user.getDepartment();
            // 判断map集合中是否包含这个department，没有就添加
            if (!map.containsKey(department)) {
                map.put(department, new ArrayList<>());
            }
            //获取map集合中对应的值，这个值是一个集合，可以通过add方法添加对象
            map.get(department).add(user);
        }
        // 排序
        for (List<User> list : map.values()) {
            list.sort(Comparator.comparingInt(User::getAge));
        }
        return map;
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
