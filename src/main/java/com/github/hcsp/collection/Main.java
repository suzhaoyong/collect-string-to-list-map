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
        Set<String> departmentSet = new HashSet<>();
        for (User user : users) {
            departmentSet.add(user.getDepartment());
        }

        Map<String, List<User>> map = new HashMap();

        for (String s : departmentSet) {
            ArrayList<User> list = new ArrayList<>();
            for (User user : users) {
                if (user.getDepartment().equals(s)) {
                    list.add(user);
                }
            }

            list.sort(new Comparator<User>() {
                @Override
                public int compare(User o1, User o2) {
                    return o1.getAge() - o2.getAge();
                }
            });

            map.put(s, list);

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
