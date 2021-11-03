package com.VChaikovsky.arrayclass.reader;

import com.VChaikovsky.arrayclass.exceptions.WrongDataException;

public interface ReaderFromFileInt {
    String readString(String filename) throws WrongDataException;
}
