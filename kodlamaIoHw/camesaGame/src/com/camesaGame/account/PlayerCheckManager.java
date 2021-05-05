package com.camesaGame.account;

import com.camesaGame.interfaces.PlayerCheckService;

public class PlayerCheckManager implements PlayerCheckService {
    @Override
    public boolean isRealAccount(Player player) {
        return true;
    }
}
