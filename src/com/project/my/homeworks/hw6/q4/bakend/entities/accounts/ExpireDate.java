package com.project.my.homeworks.hw6.q4.bakend.entities.accounts;

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
}
