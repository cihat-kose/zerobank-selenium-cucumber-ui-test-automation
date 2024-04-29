package jdbc;

import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCPractices02 extends JDBCParent {

    @Test
    public void test1() throws SQLException {

        ResultSet resultTable = statement.executeQuery("select * from actor");

        resultTable.next(); // One step forward --> 1. Row
        System.out.println("resultTable.getString(2) = " + resultTable.getString(2)); // 1. Row and 2. Column

        resultTable.next(); // One step forward --> 2. Row
        System.out.println("resultTable.getString(\"first_name\") = " + resultTable.getString("first_name"));// 2. Row and name Column (2. Column)

        resultTable.previous(); // One step previous --> 1.Row
        System.out.println("resultTable.getString(2) = " + resultTable.getString(2));
    }

    @Test
    public void test2() throws SQLException {

        ResultSet resultTable = statement.executeQuery("select * from actor");

        resultTable.absolute(10); // Go directly to line 10
        System.out.println("resultTable.getString(2) = " + resultTable.getString(2));

        resultTable.absolute(5); // Go directly to line 5
        System.out.println("resultTable.getString(\"first_name\") = " + resultTable.getString("first_name"));

        resultTable.relative(5); // Wherever you are at the end, go 5 from there --> to line 10
        System.out.println("resultTable.getString(\"first_name\") = " + resultTable.getString("first_name"));

        resultTable.next(); // Goes to 11
        resultTable.previous(); // Goes to10

        // resultTable.next()          : Next Line
        // resultTable.previous        : Previous Line
        // resultTable.absolute(10)    : Moves forward to line 10 from the beginning.
        // resultTable.relative(10)    : It moves forward from its current position to the 10th line.
        // resultTable.absolute(-10)   : - (minus) means from the end, 10th line from the end
        // resultTable.relative(-5)    : - (minus) 5 lines back from where it is

        // If we want to go back 5 from the first line with relative,
        // it will give an error because it exceeds the limit.
    }

    @Test
    public void test3() throws SQLException {

        ResultSet resultTable = statement.executeQuery("select * from actor");

        resultTable.first(); // Goes to the first line
        System.out.println("resultTable.getRow() = " + resultTable.getRow());
        System.out.println("resultTable.getString(2) = " + resultTable.getString(2));

        resultTable.last(); // Goes to the last line
        System.out.println("resultTable.getString(2) = " + resultTable.getString(2));
        System.out.println("resultTable.getRow() = " + resultTable.getRow());
    }
}
