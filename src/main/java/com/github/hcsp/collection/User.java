package com.github.hcsp.collection;

import java.util.Objects;

public class User implements Comparable<User> {
    // 用户的id
    private final Integer id;
    // 用户的姓名
    private final String name;
    // 用户的年龄
    private final int age;
    // 用户的部门，例如"技术部"/"市场部"
    private final String department;

    public User(Integer id, String name, int age, String department) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.department = department;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    /*
     equals()方法默认判断两个对象的地址值是否相等；不同的两个对象，地址值必定不同，所以比较地址值，没有任何意义；
    所以我们通常也会重写equals()，用来比较两个对象中的成员变量(属性)是否相同；
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {//getClass() 返回此 Object 的运行时类。
            return false;
        }
        User person = (User) o;
        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(User user) {
        return Integer.compare(this.age, user.age);
    }

    @Override
    /*
    对于对象来说，默认的toString()方法输出的是一个内存地址，也就是哈希码值，并不是输出这个类的各个变量的值，
    默认返回的是：  getClass().getName() + "@" +Integer.toHexString(hashCode());
    翻译过来也就是： 类名 + @ +hashCode的值  这3者组合的
    如果想要打印某个对象的各个变量值，就必须要重写toString(),返回我们需要的值
    打印一个对象p，可以直接System.out.println(p);其实println方法内部会自动调用p 的 toString() 方法。
     */
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", department=" + department +
                ", age='" + age + '\'' +
                '}';
    }
}
