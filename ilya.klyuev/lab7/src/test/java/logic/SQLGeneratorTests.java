package logic;

import data.TestsData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class SQLGeneratorTests {

    @Test
    public void generateSelectQuery() {
        //given
        String expectedSelectQuery = "SELECT ID, FIO, SCHOLARSHIP, COURSE, GROUP FROM STUDENT;";

        //when
        String actualSelectQuery = SQLGenerator.getSelectQuery(TestsData.ILYA_STUDENT.getClass());

        //then
        assertEquals(expectedSelectQuery, actualSelectQuery);
    }

    @Test
    public void generateInsertQueryTest() {
        // given
        String expectedInsertQuery = "INSERT INTO STUDENT(ID, FIO, SCHOLARSHIP, COURSE, GROUP) VALUES(0, Клюев Илья Алексеевич, 2500, 3, 91);";

        //when
        String actualInsertQuery = SQLGenerator.getInsertQuery(TestsData.ILYA_STUDENT);

        //then
        assertEquals(expectedInsertQuery, actualInsertQuery);
    }

    @Test
    public void generateUpdateQueryTest() {
        // given
        String expectedUpdateQuery = "UPDATE STUDENT SET FIO = Клюев Илья Алексеевич, SCHOLARSHIP = 2500, COURSE = 3, GROUP = 91 WHERE ID = 0;";

        //when
        String actualUpdateQuery = "";
        try {
            actualUpdateQuery = SQLGenerator.getUpdateQuery(TestsData.ILYA_STUDENT);
        } catch (EntityException e) {
            fail(e.toString());
        }

        //then
        assertEquals(expectedUpdateQuery, actualUpdateQuery);
    }

    @Test
    public void generateDeleteQueryTest() {
        //given
        String expectedDeleteQuery = "DELETE FROM STUDENT WHERE ID = 0;";

        //when
        String actualDeleteQuery = "";
        try {
            actualDeleteQuery = SQLGenerator.getDeleteQuery(TestsData.ILYA_STUDENT);
        } catch (EntityException e) {
            fail(e.toString());
        }

        //then
        assertEquals(expectedDeleteQuery, actualDeleteQuery);
    }
}
