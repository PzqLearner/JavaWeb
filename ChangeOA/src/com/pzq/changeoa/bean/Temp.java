package com.pzq.changeoa.bean;

import java.util.Objects;

public class Temp {
    private String deptno;
    private String name;
    private String location;

    public Temp() {
    }

    public Temp(String deptno, String name, String location) {
        this.deptno = deptno;
        this.name = name;
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Temp temp = (Temp) o;
        return Objects.equals(deptno, temp.deptno) && Objects.equals(name, temp.name) && Objects.equals(location, temp.location);
    }

    @Override
    public String toString() {
        return "Temp{" +
                "deptno='" + deptno + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(deptno, name, location);
    }

    public String getDeptno() {
        return deptno;
    }

    public void setDeptno(String deptno) {
        this.deptno = deptno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
