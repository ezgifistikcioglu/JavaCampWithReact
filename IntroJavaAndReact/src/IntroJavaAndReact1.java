class IntroJavaAndReact1 {
    public static void main(String[] args) {
        String webButton = "Enter the internet branch";
        double yesterdayDollar = 8.20;
        double todayDollar = 8.20;
        int maturity = 36;
        boolean isFalled;

		    /*
		    System.out.println(webButton);

		    if(todayDollar<yesterdayDollar) {
		      System.out.println("Show picture of dollar dropped");
		    }else if(todayDollar>yesterdayDollar){
		      System.out.println("Show picture of dollar rise");
		    }else{
		      System.out.println("Show dollar equals picture");
		    }
		    */

        String[] credits = {"Fast Credit", "Happy Retirement Credit", "Housing Credit", "Farmer Credit", "MSB Credit", "Meb Credit", "Ministry of Culture Credit"};

        //sample foreach loop
        for (String credit : credits) {
            System.out.println(credit);
        }

        for (int i = 0; i < credits.length; i++) {
            System.out.println(credits[i]);
        }

        int num1 = 10;
        int num2 = 20;
        num1 = num2;
        num2 = 100;
        System.out.println(num1);

        int[] number1 = {1, 2, 3, 4, 5};
        int[] number2 = {10, 20, 30, 40, 50};
        number1 = number2;
        number2[0] = 100;
        System.out.println(number1[0]);

        String city1 = "Ankara";
        String city2 = "Istanbul";
        city1 = city2;
        city2 = "Izmir";
        System.out.println(city1);
    }
}
