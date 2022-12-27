package logic;

import data.TestData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SQLQueryGeneratorTests {

    @Test
    public void selectQuery() throws TableException {
        //given
        String expectedSelectQuery = "SELECT ID, NAME, AGE, WEAPON, GENDER, HASHORSE FROM \"ВОИНЫ\";";

        //when
        String actualSelectQuery = SQLQueryGenerator.getSelectQuery(TestData.CLAUS_WARRIOR.getClass());

        //then
        assertEquals(expectedSelectQuery, actualSelectQuery);
    }

    @Test
    public void insertQueryTest() throws TableException {
        // given
        String expectedInsertQuery = "INSERT INTO \"ВОИНЫ\" (ID, NAME, AGE, WEAPON, GENDER, HASHORSE) VALUES(1, 'Кл\''аус', 41, 'меч', UNKNOWN, false);";

        //when
        String actualInsertQuery = SQLQueryGenerator.getInsertQuery(TestData.CLAUS_WARRIOR);

        //then
        assertEquals(expectedInsertQuery, actualInsertQuery);
    }

    @Test
    public void updateQueryTest() throws TableException {
        // given
        String expectedUpdateQuery = "UPDATE \"ВОИНЫ\" SET NAME = 'Кл\''аус', AGE = 41, WEAPON = 'меч', GENDER = UNKNOWN, HASHORSE = false WHERE ID = 1;";

        //when
        String actualUpdateQuery = SQLQueryGenerator.getUpdateQuery(TestData.CLAUS_WARRIOR);

        //then
        assertEquals(expectedUpdateQuery, actualUpdateQuery);
    }

    @Test
    public void deleteQueryTest() throws TableException {
        //given
        String expectedDeleteQuery = "DELETE FROM \"ВОИНЫ\" WHERE ID = 2;";

        //when
        String actualDeleteQuery = SQLQueryGenerator.getDeleteQuery(TestData.EVRIDIKA_WARRIOR);

        //then
        assertEquals(expectedDeleteQuery, actualDeleteQuery);
    }

    @Test
    void deleteQueryForManyPrimaryKeysTest() throws TableException {
        // given
        String expectedDeleteQuery = "DELETE FROM \"СОЛДАТЫ\" WHERE FIRSTNAME = 'Rudolf', LASTNAME = 'Yohansen';";

        // when
        String actualDeleteQuery = SQLQueryGenerator.getDeleteQuery(TestData.RUDOLF_SOLDIER);

        // then
        assertEquals(expectedDeleteQuery, actualDeleteQuery);
    }
}
