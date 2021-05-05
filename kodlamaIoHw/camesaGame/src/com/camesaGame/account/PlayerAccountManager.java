package com.camesaGame.account;

import com.camesaGame.interfaces.PlayerCheckService;

import com.camesaGame.interfaces.BaseService;

public class PlayerAccountManager implements BaseService<Player> {
    private PlayerCheckService checkService;

    public PlayerAccountManager(PlayerCheckService checkService) {
        this.checkService = checkService;
    }

    @Override
    public boolean addValue(Player value) {
        if (checkService.isRealAccount(value)) {
            System.out.println("Dear Mr/Mrs : " + value.getName() + " " + value.getSurname() + " Welcome to Camesa Game! ");
            return true;
        } else {
            System.out.println(" Please enter the correct information... ");
        }
        return false;
    }

    @Override
    public boolean updateValue(Player value) {
        if (addValue(value) && !value.getName().isEmpty() && !value.getSurname().isEmpty() && value.getNationalityId().length() == 11) {
            System.out.println("Dear Mr/Mrs : " + value.getName() + " " + value.getSurname() + " Your information has been updated! ");
            return true;
        } else {
            System.out.println(" please fill in the fields completely... ");
        }
        return false;
    }

    @Override
    public boolean deleteValue(Player value) {
        if (addValue(value)) {
            System.out.println("Dear Mr/Mrs : " + value.getName() + " " + value.getSurname() + " We are sorry that you left.. Are'u sure? ");
            return true;
        } else {
            System.out.println(" Uppps, Please Try again... ");
        }
        return false;
    }
}
