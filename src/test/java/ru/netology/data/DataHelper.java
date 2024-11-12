package ru.netology.data;

import lombok.Value;

public class DataHelper {
    @Value
    public static class cardInfo{
        String cardNubmer;
        String month;
        String yars;
        String nameOwner;
        String cvc;
    }

    public static cardInfo getValidCard(){
        return new cardInfo("4444 4444 4444 4441", "08","25","Ivan","999");
    }

    public static cardInfo getNoValidCard(){
        return new cardInfo("4444 4444 4444 4442", "08","25","Ivan","999");
    }

    public static cardInfo getEmptyNumberCard(){
        return new cardInfo(" ", "08","25","Ivan","999");
    }
    public static cardInfo getEmptyMonth(){
        return new cardInfo("4444 4444 4444 4442", " ","25","Ivan","999");
    }
    public static cardInfo getEmptyYear(){
        return new cardInfo("4444 4444 4444 4442", "08"," ","Ivan","999");
    }
    public static cardInfo getEmptyName(){
        return new cardInfo("4444 4444 4444 4442", "08","25"," ","999");
    }
    public static cardInfo getEmptyCVC(){
        return new cardInfo("4444 4444 4444 4442", "08","25","Ivan"," ");
    }
    public static cardInfo getZeroMonth(){
        return new cardInfo("4444 4444 4444 4442", "00","25","Ivan","999");
    }
    public static cardInfo getYearLessToday(){
        return new cardInfo("4444 4444 4444 4442", "08","20","Ivan","999");
    }
}
