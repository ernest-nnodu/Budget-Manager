import org.hyperskill.hstest.stage.StageTest;
import org.hyperskill.hstest.testcase.CheckResult;
import org.hyperskill.hstest.testcase.TestCase;

<<<<<<< HEAD
import java.io.File;
=======
>>>>>>> 4abcd1c195b717dfa90bb43f972be462cd438a41
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BudgetManagerTest extends StageTest<String> {

    @Override
    public List<TestCase<String>> generate() {
        return List.of(

            new TestCase<String>()
                .setInput("0\n")
                .setCheckFunc(BudgetManagerTest::test1),

            new TestCase<String>()
                .setInput("0\n")
                .setCheckFunc(BudgetManagerTest::test2),

            new TestCase<String>()
                .setInput("4\n0")
                .setCheckFunc(BudgetManagerTest::test3),

            new TestCase<String>()
                .setInput("1\n400\n4\n1\n200\n4\n0")
                .setCheckFunc(BudgetManagerTest::test4),

            new TestCase<String>()
<<<<<<< HEAD
                .setInput("1\n1000\n2\n" +
                    "1\nAlmond 250g\n35.43\n" +
                    "1\nMilk\n3.50\n" +
                    "1\nRed Fuji Apple\n5.99\n" +
                    "1\nEggs\n3.99\n" +
                    "1\nFIJI Natural Artesian Water\n25.98\n" +
                    "1\nHershey's milk chocolate bars\n8.54\n" +
                    "1\nGreat Value Broccoli Florets\n1.00\n" +
                    "1\nKeystone Ground Bee\n6.28\n" +
                    "2\nGildan LT\n8.61\n" +
                    "2\nMen's Dual Defense Crew Socks 12 Pairs\n13.00\n" +
                    "2\nWrangler Men's Stretch Cargo Pant\n19.97\n" +
                    "3\nLEGO DUPLO Town Farm Animals\n10.10\n" +
                    "3\nCinema\n8.73\n" +
                    "3\nSkate rental\n30\n" +
                    "4\nSensodyne Pronamel Toothpaste\n19.74\n" +
                    "4\nChick-fil-A $10 Gift Card\n10\n" +
                    "4\nDebt\n3.50\n" +
                    "5\n5\n0").setCheckFunc(BudgetManagerTest::test5),

            new TestCase<String>()
                .setInput("6\n4\n3\n5\n1\n6\n0")
                .setCheckFunc(BudgetManagerTest::test6)
=======
                .setInput("1\n600\n2\n" +
                    "1\nMilk\n3.5\n" +
                    "2\nMen's Dual Defense Crew Socks 12 Pairs\n13\n" +
                    "3\nCinema\n8.73\n" +
                    "5\n3\n1\n2\n3\n5\n6\n0")
                .setCheckFunc(BudgetManagerTest::test5)

>>>>>>> 4abcd1c195b717dfa90bb43f972be462cd438a41
        );
    }

    //Checking program stop
    private static CheckResult test1(String reply, String attach) {
        if (!reply.contains("Bye!")) {
            return new CheckResult(false,
                "Your program should stop after choosing \"Exit\"");
        }
        return new CheckResult(true);
    }


    //Checking menu
    private static CheckResult test2(String reply, String attach) {
        String[] menuPatterns = {"1)", "2)", "3)", "4)", "0)"};
        for (String menuPattern : menuPatterns) {
            if (!reply.contains(menuPattern)) {
                return new CheckResult(false,
                    "Your menu doesn't have item " + menuPattern);
            }
        }
        return new CheckResult(true);
    }


    //Checking balance
    private static CheckResult test3(String reply, String attach) {

        String[] blocks = reply.split("\n(\n+)?\n");

        if (blocks.length != 4) {
            return new CheckResult(false,
                "Your program shows wrong blocks of output. Expected: 4\n" +
                    "You have: " + blocks.length + "\n" +
                    "Make sure that you print an empty line after each chosen action");
        }

        String balance = blocks[1];

        if (!balance.toLowerCase().contains("balance")) {
            return new CheckResult(false,
                "Your program should show balance after choosing 4th item");
        }

        Pattern doublePattern = Pattern.compile("\\d+[,.]\\d+");
        Matcher matcher = doublePattern.matcher(balance);

        if (!matcher.find()) {
            return new CheckResult(false,
                "Your balance should contain a number!");
        }

        double balanceDouble = Double.parseDouble(matcher.group());

        if (Math.abs(balanceDouble - 0) > 0.0001) {
            System.out.println(balance);
            return new CheckResult(false,
                "Balance should be $0.00 at the beginning");
        }

        return new CheckResult(true);

    }


    //Checking adding income
    private static CheckResult test4(String reply, String attach) {

        String[] blocks = reply.split("\n(\n+)?\n");

        if (blocks.length != 10) {
            return new CheckResult(false,
                "Your program shows wrong blocks of output. Expected: 10\n" +
                    "You have: " + blocks.length + "\n" +
                    "Make sure that you print an empty line after each chosen action");
        }

        String balanceAfterFirstAddingIncome = blocks[3];

        if (!balanceAfterFirstAddingIncome.contains("$400")) {
            return new CheckResult(false,
                "Balance is wrong after adding income!.\n" +
                    "Expected:\n" +
                    "Balance: $400.00\n" +
                    "Your output:\n" +
                    balanceAfterFirstAddingIncome);
        }

        return new CheckResult(true);
    }

    //Checking the saving purchases
    private static CheckResult test5(String reply, String attach) {
        File file = new File("purchases.txt");
        if (!file.exists()) {
            return new CheckResult(false,
                "Your program should create purchases.txt file and save purchases there!");
        }
        return new CheckResult(true);
    }

    //Checking the downloading purchases
    private static CheckResult test6(String reply, String attach) {

        String[] blocks = reply.split("\n(\n+)?\n");

<<<<<<< HEAD
        if (blocks.length != 12) {
            return new CheckResult(false,
                "Your program shows wrong blocks of output. Expected: 12\n" +
=======
        if (blocks.length != 22) {
            return new CheckResult(false,
                "Your program shows wrong blocks of output. Expected: 22\n" +
>>>>>>> 4abcd1c195b717dfa90bb43f972be462cd438a41
                    "You have: " + blocks.length + "\n" +
                    "Make sure that you print an empty line after each chosen action");
        }

<<<<<<< HEAD
        String balanceAfterDownloadingPurchases = blocks[3];

        if (!balanceAfterDownloadingPurchases.replace(",", ".").contains("785.64")) {
            return new CheckResult(false,
                "Your program reads balance from file wrong!");
        }

        //All purchases list

        String allPurchases = blocks[6];

        String[] expectedPurchases = {
            "Almond 250g $35.43",
            "Milk $3.50",
            "Red Fuji Apple $5.99",
            "Eggs $3.99",
            "FIJI Natural Artesian Water $25.98",
            "Hershey's milk chocolate bars $8.54",
            "Great Value Broccoli Florets $1.00",
            "Keystone Ground Bee $6.28",
            "Gildan LT $8.61",
            "Men's Dual Defense Crew Socks 12 Pairs $13.00",
            "Wrangler Men's Stretch Cargo Pant $19.97",
            "LEGO DUPLO Town Farm Animals $10.10",
            "Cinema $8.73",
            "Skate rental $30.00",
            "Sensodyne Pronamel Toothpaste $19.74",
            "Chick-fil-A $10 Gift Card $10.00",
            "Debt $3.50"
        };

        for (String expectedPurchase : expectedPurchases) {
            if (!allPurchases.contains(expectedPurchase)) {
                return new CheckResult(false,
                    "Your all purchases list doesn't have purchase:\n" +
                        expectedPurchase + "\n" +
                        "But should have!");
            }
        }

        String[] temp = allPurchases.split("\n");
=======

        //Food
        String foodList = blocks[12];
        if (!foodList.contains("Milk")) {
            return new CheckResult(false,
                "Wrong food purchase list.\n" +
                    "Expected:\n" +
                    "Food:\n" +
                    "Milk $3.50\n" +
                    "Total sum: $3.50\n" +
                    "Your output:\n" + foodList);
        }

        String[] temp = foodList.split("\n");
>>>>>>> 4abcd1c195b717dfa90bb43f972be462cd438a41
        String totalSum = temp[temp.length - 1];

        Pattern doublePattern = Pattern.compile("\\d+[,.]\\d+");
        Matcher matcher = doublePattern.matcher(totalSum);

        if (!matcher.find()) {
            return new CheckResult(false,
<<<<<<< HEAD
                "Total sum of all purchases is wrong. Expected:\n" +
                    "Total sum: $214.36\n" +
=======
                "Your food total sum is wrong!\n" +
                    "Expected:\n" +
                    "Total sum: $3.50\n" +
>>>>>>> 4abcd1c195b717dfa90bb43f972be462cd438a41
                    "Your output:\n" +
                    totalSum);
        }

<<<<<<< HEAD
        double allTotalSum = Double.parseDouble(matcher.group());

        if (Math.abs(allTotalSum - 214.36) > 0.0001) {
            return new CheckResult(false,
                "Your all total sum is wrong!");
        }


        //Food list

        String foodList = blocks[8];

        expectedPurchases = new String[] {
            "Almond 250g $35.43",
            "Milk $3.50",
            "Red Fuji Apple $5.99",
            "Eggs $3.99",
            "FIJI Natural Artesian Water $25.98",
            "Hershey's milk chocolate bars $8.54",
            "Great Value Broccoli Florets $1.00",
            "Keystone Ground Bee $6.28"
        };

        for (String expectedPurchase : expectedPurchases) {
            if (!foodList.contains(expectedPurchase)) {
                return new CheckResult(false,
                    "Your food list doesn't have purchase:\n" +
                        expectedPurchase + "\n" +
                        "But should have!");
            }
        }

        temp = foodList.split("\n");
        totalSum = temp[temp.length - 1];

        matcher = doublePattern.matcher(totalSum);

        if (!matcher.find()) {
            return new CheckResult(false,
                "Total sum of food list is wrong. Expected:\n" +
                    "Total sum: $90.71\n" +
                    "Your output:\n" +
                    totalSum);
        }

        double foodTotalSum = Double.parseDouble(matcher.group());

        if (Math.abs(foodTotalSum - 90.71) > 0.0001) {
            return new CheckResult(false,
=======
        double foodTotalSum = Double.parseDouble(matcher.group());

        if (Math.abs(foodTotalSum - 3.5) > 0.0001) {
            return new CheckResult(false,
>>>>>>> 4abcd1c195b717dfa90bb43f972be462cd438a41
                "Your food total sum is wrong!");
        }

        //Clothes
        String clothesList = blocks[14];

        if (!clothesList.contains("Men's Dual Defense Crew Socks 12 Pairs")) {
            return new CheckResult(false,
                "Wrong clothes purchase list.\nExpected:\n" +
                    "Clothes:\n" +
                    "Men's Dual Defense Crew Socks 12 Pairs $13.00\n" +
                    "Total sum: $13.00\n" +
                    "Your output:\n" + clothesList);
        }

        temp = clothesList.split("\n");
        totalSum = temp[temp.length - 1];

        matcher = doublePattern.matcher(totalSum);

        if (!matcher.find()) {
            return new CheckResult(false,
                "Your clothes total sum is wrong!\n" +
                    "Expected:\n" +
                    "Total sum: $13.00\n" +
                    "Your output:\n" +
                    totalSum);
        }

        double clothesTotalSum = Double.parseDouble(matcher.group());

        if (Math.abs(clothesTotalSum - 13) > 0.0001) {
            return new CheckResult(false,
                "Your clothes total sum is wrong!");
        }

        //Entertainment
        String entertainmentList = blocks[16];

        if (!entertainmentList.contains("Cinema")) {
            return new CheckResult(false,
                "Wrong entertainment purchase list.\nExpected:\n" +
                    "Entertainment:\n" +
                    "Cinema $8.73\n" +
                    "Total sum: $8.73\n" +
                    "Your output:\n" + entertainmentList);
        }

        temp = entertainmentList.split("\n");
        totalSum = temp[temp.length - 1];

        matcher = doublePattern.matcher(totalSum);

        if (!matcher.find()) {
            return new CheckResult(false,
                "Your entertainment total sum is wrong!\n" +
                    "Expected:\n" +
                    "Total sum: $8.73\n" +
                    "Your output:\n" +
                    totalSum);
        }

        double entertainmentTotalSum = Double.parseDouble(matcher.group());

        if (Math.abs(entertainmentTotalSum - 8.73) > 0.0001) {
            return new CheckResult(false,
                "Your entertainment total sum is wrong!");
        }

        //All
        String allList = blocks[18];

        if (!allList.contains("Milk")
            || !allList.contains("Men's Dual Defense Crew Socks 12 Pairs")
            || !allList.contains("Cinema")) {
            return new CheckResult(false,
                "Wrong all purchase list.\n" +
                    "Expected:\n" +
                    "All:\n" +
                    "Milk $3.50\n" +
                    "Men's Dual Defense Crew Socks 12 Pairs $13.00\n" +
                    "Cinema $8.73\n" +
                    "Total sum: $25.23" +
                    "Your output:\n" + allList);
        }

        temp = allList.split("\n");
        totalSum = temp[temp.length - 1];

        matcher = doublePattern.matcher(totalSum);

        if (!matcher.find()) {
            return new CheckResult(false,
                "Your all total sum is wrong!\n" +
                    "Expected:\n" +
                    "Total sum: $25.23\n" +
                    "Your output:\n" +
                    totalSum);
        }

        double allTotalSum = Double.parseDouble(matcher.group());

        if (Math.abs(allTotalSum - 25.23) > 0.0001) {
            return new CheckResult(false,
                "Your all total sum is wrong!");
        }


        return new CheckResult(true);
    }
}
