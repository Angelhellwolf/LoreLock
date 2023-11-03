package com.angel.lorelock.method;

import java.util.List;

public class RollString {
    public String rollString(List<String> list) {
        return list.get((int) (Math.random() * list.size()));
    }
}
