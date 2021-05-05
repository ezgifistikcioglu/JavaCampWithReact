package com.camesaGame;

import com.camesaGame.account.Player;
import com.camesaGame.account.PlayerCheckManager;
import com.camesaGame.account.PlayerAccountManager;
import com.camesaGame.campaign.Campaign;
import com.camesaGame.campaign.GameCampaignManager;
import com.camesaGame.interfaces.GameSalesService;
import com.camesaGame.sales.GameSalesManager;
import com.camesaGame.sales.Sales;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        System.out.println("|------------------------------------------------------------------|");

        System.out.println("      ===>>> Player Knowledge... <<<=== ");

        System.out.println("|------------------------------------------------------------------|");

        Player player = new Player();
        player.setId(1);
        player.setName("EZGi");
        player.setSurname("FISTIKÇIOĞLU");
        player.setDateOfBirth(LocalDate.of(1995,7,14));
        player.setNationalityId("11670875246");

        Player secondPlayer = new Player();
        secondPlayer.setId(1);
        secondPlayer.setName("unknown");
        secondPlayer.setSurname("unknown");
        secondPlayer.setDateOfBirth(LocalDate.of(1955,5,5));
        secondPlayer.setNationalityId("12345677598");

        PlayerAccountManager playerAccountManager = new PlayerAccountManager(new PlayerCheckManager());
        playerAccountManager.addValue(player);
        System.out.println("          *************************");
        playerAccountManager.updateValue(secondPlayer);
        System.out.println("          *************************");
        playerAccountManager.deleteValue(player);

        System.out.println("|------------------------------------------------------------------|");

        System.out.println("      ===>>> Game Sales Knowledge... <<<=== ");

        System.out.println("|------------------------------------------------------------------|");

        Sales sales = new Sales();
        sales.setId(1);
        sales.setLevel(10);
        sales.setPrice(500);
        sales.setNameOfGameSold("LOL");

        GameSalesManager gameSalesService = new GameSalesManager();
        gameSalesService.addValue(player,sales);
        gameSalesService.updateValue(secondPlayer,sales);



        System.out.println("|------------------------------------------------------------------|");

        System.out.println("      ===>>> Game Campaign Knowledge... <<<=== ");

        System.out.println("|------------------------------------------------------------------|");

        Campaign campaign = new Campaign();
        campaign.setCampaignName("Spring Discount Has Started!!! Moreover, surprise  products are also gifts");
        campaign.setDiscount(40);
        campaign.setGiftName("The tester   product");

        GameCampaignManager campaignManager = new GameCampaignManager();
        campaignManager.addValue(campaign);
        campaignManager.updateValue(campaign);
        campaignManager.deleteValue(campaign);
    }
}
