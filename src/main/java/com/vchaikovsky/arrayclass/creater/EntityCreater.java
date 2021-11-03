package com.vchaikovsky.arrayclass.creater;

import com.vchaikovsky.arrayclass.entity.CustomArray;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class EntityCreater {
    static final Logger logger = LogManager.getLogger();

    public static CustomArray createEntity(Integer[] array){
        return new CustomArray(array);
    }
}
