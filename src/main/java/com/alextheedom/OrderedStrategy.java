package com.alextheedom;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by atheedom on 24/07/15.
 */
public class OrderedStrategy implements Strategy<Integer> {


    @Override
    public List<Integer> execute(Config config) {

        List<Integer> numbersSelected = new ArrayList<>();

        for (int j = 0; j < config.getNumberPerSelection(); j++) {
            Integer nextDigitalTicket = config.getNextNumber();

            if (nextDigitalTicket == null) {
                nextDigitalTicket = config.getInitialNumber();
            }

            numbersSelected.add(nextDigitalTicket);
            config.setNextNumber(++nextDigitalTicket);
        }


        return numbersSelected;
    }


}
