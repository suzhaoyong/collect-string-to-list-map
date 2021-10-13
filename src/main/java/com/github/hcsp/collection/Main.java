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
        // 先遍历整个列表，挑选出所有的部门列表
        Set<String> set = new HashSet();
        users.forEach(user -> {
            set.add(user.getDepartment());
        });
        Map<String, List<User>> map = new HashMap<>();
        // 遍历所有的部门，再遍历所有对象将是这个的对象加到这个部门
        set.forEach(department -> {
            ArrayList list = new ArrayList();
            users.forEach(user -> {
                if (user.getDepartment() == department) {
                    list.add(user);
                }
            });

            // 将每个部门的对象的年龄按照从小到大的顺序排序
            Collections.sort(list, new SortByUserId());
            map.put(department, list);
        });
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

    private static class SortByUserId implements Comparator<User> {
        @Override
        public int compare(User u1, User u2) {
            if (u1.getAge() > u2.getAge()) {
                return 1;
            }
            return -1;
        }
    }
}

