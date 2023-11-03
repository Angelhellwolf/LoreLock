package com.angel.lorelock.utils;

import java.util.List;

public class RollString {
    public String rollString(List<String> list) {
        return list.get((int) (Math.random() * list.size()));
    }
}
