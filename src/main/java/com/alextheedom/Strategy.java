package com.alextheedom;

import java.util.List;

/**
 * Created by atheedom on 24/07/15.
 */
public interface Strategy<T> {

    List<T> execute(Config config);

}
