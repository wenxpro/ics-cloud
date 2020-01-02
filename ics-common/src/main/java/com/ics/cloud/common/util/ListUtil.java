package com.ics.cloud.common.util;

import java.util.List;

public class ListUtil {

    public static boolean is(List list) {
        if (list != null && list.size() > 0) {
            return true;
        }
        return false;
    }
}
