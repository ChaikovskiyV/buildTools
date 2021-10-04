package com.VChaikovsky.arrayClass.reader;

import com.VChaikovsky.arrayClass.exceptions.WrongDataException;

public interface ReaderFromFileInt {
    String readString(String filename) throws WrongDataException;
}
