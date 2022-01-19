package com.project.my.homeworks.hw6.q4.bakend.entities.accounts;

import java.util.Objects;

public class ExpireDate {
    private int year;
    private int month;

    private ExpireDate(int year, int month) {
        this.year = year;
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public static ExpireDate of(int year, int month) {
        return new ExpireDate(year, month);
    }

    @Override
    public String toString() {
        return year + " / " + month;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExpireDate that = (ExpireDate) o;
        return year == that.year && month == that.month;
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, month);
    }
}
