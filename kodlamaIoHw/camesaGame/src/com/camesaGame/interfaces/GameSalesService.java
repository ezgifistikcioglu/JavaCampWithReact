package com.camesaGame.interfaces;

import com.camesaGame.account.Player;
import com.camesaGame.sales.Sales;

public interface GameSalesService {

    boolean addValue(Player value, Sales sales);

    boolean updateValue(Player value, Sales sales);

    boolean deleteValue(Player value, Sales sales);
}
