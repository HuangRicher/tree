package com.tongzhu.util;

import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class CollectionsUtil {

    public static void removeCombatant(List<Map<String,Object>> combatantList, int index){
        ListIterator<Map<String,Object>> it=combatantList.listIterator();
        while (it.hasNext()){
            if(index==it.nextIndex()){
                it.next();
                it.remove();
                break;
            }else{
                it.next();
            }
        }
    }
}
