import org.hyperskill.hstest.stage.StageTest;
import org.hyperskill.hstest.testcase.CheckResult;
import org.hyperskill.hstest.testcase.TestCase;

import java.util.List;
import java.util.function.BiFunction;
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
                .setInput("1\n600\n2\n" +
                    "1\nMilk\n3.5\n" +
                    "2\nMen's Dual Defense Crew Socks 12 Pairs\n13\n" +
                    "3\nCinema\n8.73\n" +
                    "5\n3\n1\n2\n3\n5\n6\n0")
                .setCheckFunc(BudgetManagerTest::test5)

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


    //Checking adding purchase
    private static CheckResult test5(String reply, String attach) {

        String[] blocks = reply.split("\n(\n+)?\n");

        if (blocks.length != 22) {
            return new CheckResult(false,
                "Your program shows wrong blocks of output. Expected: 22\n" +
                    "You have: " + blocks.length + "\n" +
                    "Make sure that you print an empty line after each chosen action");
        }


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
        String totalSum = temp[temp.length - 1];

        Pattern doublePattern = Pattern.compile("\\d+[,.]\\d+");
        Matcher matcher = doublePattern.matcher(totalSum);

        if (!matcher.find()) {
            return new CheckResult(false,
                "Your food total sum is wrong!\n" +
                    "Expected:\n" +
                    "Total sum: $3.50\n" +
                    "Your output:\n" +
                    totalSum);
        }

        double foodTotalSum = Double.parseDouble(matcher.group());

        if (Math.abs(foodTotalSum - 3.5) > 0.0001) {
            return new CheckResult(false,
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
