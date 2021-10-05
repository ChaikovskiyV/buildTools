package com.VChaikovsky.arrayClass.creater;

import com.VChaikovsky.arrayClass.entity.CustomArray;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class EntityCreater {
    final static Logger logger = LogManager.getLogger();

    public static CustomArray createEntity(Integer[] array){
        return new CustomArray(array);
    }
}
