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

//    方案一：研究了guava的官方文档，发现可以用TreeMultimap将key和value按照相应compare规则进行排序，以下函数经过测试可行
//    public static Multimap<String, User> collect(List<User> users) {
//        Multimap<String, User> map = TreeMultimap.create();
//        for (User i : users) {
//            map.put(i.getDepartment(), i);
//        }
//        return map;
//    }

    //方案二：先提取部门名称，然后根据不同的部门名称创建相应的list表，将属于该部门的user放进list中，再根据年龄进行排序
    public static Map<String, List<User>> collect(List<User> users) {
        Map<String, List<User>> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        for (User user : users) {
            set.add(user.getDepartment());
        }
        for (String department : set) {
            List<User> list = new ArrayList<>();
            for (User user : users) {
                if (department.equals(user.getDepartment())) {
                    list.add(user);
                }
            }
            Collections.sort(list);
            map.put(department, list);
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
