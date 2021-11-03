package com.vchaikovsky.arrayclass.reader;

import com.vchaikovsky.arrayclass.exceptions.WrongDataException;

public interface ReaderFromFileInt {
    String readString(String filename) throws WrongDataException;
}
