package com.camesaGame.campaign;

import com.camesaGame.interfaces.BaseService;

public class GameCampaignManager implements BaseService<Campaign> {
    @Override
    public boolean addValue(Campaign value) {
        if (value.toString() != null) {
            System.out.println("Added Campaign : " + value.getCampaignName());
            return true;
        } else {
            System.out.println(" Uppps, Please Try again... ");
        }
        return false;
    }

    @Override
    public boolean updateValue(Campaign value) {
        if (value.toString() != null) {
            System.out.println("Updated Campaign : " + value.getCampaignName());
            return true;
        } else {
            System.out.println(" Uppps, Please Try again... ");
        }
        return false;
    }

    @Override
    public boolean deleteValue(Campaign value) {
        if (value.toString() != null) {
            System.out.println("Deleted Campaign : " + value.getCampaignName());
            return true;
        } else {
            System.out.println(" Uppps, Please Try again... ");
        }
        return false;
    }
}
