package com.camesaGame.sales;

import com.camesaGame.account.Player;
import com.camesaGame.campaign.GameCampaignManager;
import com.camesaGame.interfaces.GameSalesService;

public class GameSalesManager implements GameSalesService {
    private GameCampaignManager gameCampaignManager;
    private Sales sales;

    public GameSalesManager() {
    }

    public GameSalesManager(GameCampaignManager gameCampaignManager, Sales sales) {
        this.gameCampaignManager = gameCampaignManager;
        this.sales = sales;
    }

    @Override
    public boolean addValue(Player value, Sales sales) {
        if (!value.getName().isEmpty()){
            System.out.println("Hello "  + value.getName() + " " + value.getSurname() + " ----> " + " Information of the game you want to sell: ");
            System.out.println("NameOfGameSold: " + sales.getNameOfGameSold() + " ▪Price: " + sales.getPrice() + " ▪Level: " + sales.getLevel());
            return true;
        } else {
            System.out.println(" Please try with correct account... ");
        }
        return false;
    }

    @Override
    public boolean updateValue(Player value, Sales sales) {
        if (addValue(value, sales)){

            String name = sales.getNameOfGameSold();
            int price = sales.getPrice();
            int level = sales.getLevel();

            System.out.println("Hello "  + value.getName() + " " + value.getSurname() + " ----> " + " Information of the game you want to update: ");

            System.out.println("NameOfGameSold: " + name + " ▪Price: " + price + " ▪Level: " + level);

        }else {
            System.out.println(" Please try again... ");
        }
        return false;
    }

    @Override
    public boolean deleteValue(Player value, Sales sales) {
        return false;
    }
}
