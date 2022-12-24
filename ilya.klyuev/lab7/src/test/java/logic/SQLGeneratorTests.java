package logic;

import data.TestsData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class SQLGeneratorTests {

    @Test
    public void generateSelectQuery() {
        //given
        String expectedSelectQuery = "SELECT ID, FIO, SCHOLARSHIP, COURSE, GROUP FROM \"STUDENTS\";";

        //when
        String actualSelectQuery = null;
        try {
            actualSelectQuery = SQLGenerator.getSelectQuery(TestsData.ILYA_STUDENT.getClass());
        } catch (TableException e) {
            fail(e.toString());
        }

        //then
        assertEquals(expectedSelectQuery, actualSelectQuery);
    }

    @Test
    public void generateInsertQueryTest() {
        // given
        String expectedInsertQuery = "INSERT INTO \"STUDENTS\"(ID, FIO, SCHOLARSHIP, COURSE, GROUP) VALUES(0, 'Клюев Илья Алексеевич', 2500, 3, 91);";

        //when
        String actualInsertQuery = null;
        try {
            actualInsertQuery = SQLGenerator.getInsertQuery(TestsData.ILYA_STUDENT);
        } catch (TableException e) {
            fail(e.toString());
        }

        //then
        assertEquals(expectedInsertQuery, actualInsertQuery);
    }

    @Test
    public void generateUpdateQueryTest() {
        // given
        String expectedUpdateQuery = "UPDATE \"STUDENTS\" SET FIO = 'Клюев Илья Алексеевич', SCHOLARSHIP = 2500, COURSE = 3, GROUP = 91 WHERE ID = 0;";

        //when
        String actualUpdateQuery = null;
        try {
            actualUpdateQuery = SQLGenerator.getUpdateQuery(TestsData.ILYA_STUDENT);
        } catch (TableException e) {
            fail(e.toString());
        }

        //then
        assertEquals(expectedUpdateQuery, actualUpdateQuery);
    }

    @Test
    public void generateDeleteQueryTest() {
        //given
        String expectedDeleteQuery = "DELETE FROM \"STUDENTS\" WHERE ID = 0;";

        //when
        String actualDeleteQuery = null;
        try {
            actualDeleteQuery = SQLGenerator.getDeleteQuery(TestsData.ILYA_STUDENT);
        } catch (TableException e) {
            fail(e.toString());
        }

        //then
        assertEquals(expectedDeleteQuery, actualDeleteQuery);
    }

    @Test
    void generateInsertQueryWithApostropheInString() {
        //given
        String expectedInsertQuery = "INSERT INTO \"STUDENTS\"(ID, FIO, SCHOLARSHIP, COURSE, GROUP) VALUES(1, 'Ani''mani Mach''lac', 3500, 2, 9);";

        //when
        String actualInsertQuery = null;
        try {
            actualInsertQuery = SQLGenerator.getInsertQuery(TestsData.STUDENT_WITH_APOSTROPHE);
        } catch (TableException e) {
            fail(e.toString());
        }

        //then
        assertEquals(expectedInsertQuery, actualInsertQuery);
    }

    @Test
    void generateDeleteQueryForEntityWithManyPrimaryKeysTest() {
        // given
        String expectedDeleteQuery = "DELETE FROM \"ЛЮДИ\" WHERE FIRSTNAME = 'Vasiliy', LASTNAME = 'Ivanov';";

        // when
        String actualDeleteQuery = null;
        try {
            actualDeleteQuery = SQLGenerator.getDeleteQuery(TestsData.VASILIY_IVANOV);
        } catch (TableException e) {
            fail(e.toString());
        }

        // then
        assertEquals(expectedDeleteQuery, actualDeleteQuery);
    }
}
