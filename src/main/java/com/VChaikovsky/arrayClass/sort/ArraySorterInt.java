package com.VChaikovsky.arrayClass.sort;

import com.VChaikovsky.arrayClass.exceptions.WrongDataException;

public interface ArraySorterInt {
    void choiceSort(Integer[] array) throws WrongDataException;

    void insertSort(Integer[] array) throws WrongDataException;

    void bubbleSort(Integer[] array) throws WrongDataException;

    Integer[] streamSort(Integer[] array) throws WrongDataException;
}
