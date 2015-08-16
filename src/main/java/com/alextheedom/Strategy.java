package com.alextheedom;

import java.util.List;

public interface Strategy<T> {

    List<T> execute(Config config);

}
