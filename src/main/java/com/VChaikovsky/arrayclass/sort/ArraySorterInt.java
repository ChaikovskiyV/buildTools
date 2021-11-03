package com.VChaikovsky.arrayclass.sort;

import com.VChaikovsky.arrayclass.exceptions.WrongDataException;

public interface ArraySorterInt {
    void choiceSort(Integer[] array) throws WrongDataException;

    void insertSort(Integer[] array) throws WrongDataException;

    void bubbleSort(Integer[] array) throws WrongDataException;

    Integer[] streamSort(Integer[] array) throws WrongDataException;
}
