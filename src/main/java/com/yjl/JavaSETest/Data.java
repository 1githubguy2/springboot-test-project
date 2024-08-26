package com.yjl.JavaSETest;

/**
 * @author unbroken
 * @Description TODO
 * @Version 1.0
 * @date 2023/4/14 22:14
 */
public class Data {
    private int val1;
    private int val2;
    public Data(int val1, int val2) {
        this.val1 = val1;
        this.val2 = val2;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Data data = (Data) o;
        return val1 == data.val1 && val2 == data.val2;
    }
    @Override
    public String toString() {
        return "Data{" +
                "val1=" + val1 +
                ", val2=" + val2 +
                '}';
    }
}
