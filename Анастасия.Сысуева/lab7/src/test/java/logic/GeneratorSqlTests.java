package logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import data.AnimeTestsData;

public class GeneratorSqlTests {

    @Test
    public void generatorSelectQueryTest() {
        //given
        String expectedSelectQuery = "SELECT id, animeName, filmmakerName, genre, yearOfCreation, rating FROM Anime";

        //when
        String actualSelectQuery = GeneratorSql.getSelectQuery(AnimeTestsData.PROSE_OF_STRAY_DOGS_ANIME.getClass());

        //then
        assertEquals(expectedSelectQuery, actualSelectQuery);
    }

    @Test
    public void generatorInsertQueryTest() {
        //given
        String expectedInsertQuery = "INSERT INTO Anime VALUES (2, 'Наруто', 'Хаято Датэ', 'сёнэн', 2002, 9.2)";

        //when
        String actualInsertQuery = GeneratorSql.getInsertQuery(AnimeTestsData.NARUTO_ANIME);

        //then
        assertEquals(expectedInsertQuery, actualInsertQuery);
    }

    @Test
    public void generatorUpdateQueryTest() {
        //given
        String expectedUpdateQuery = "UPDATE Anime SET animeName = 'Хоримия', filmmakerName = 'Масаси Исихама', genre = 'романтика', yearOfCreation = 2021, rating = 9.4 WHERE id = 3";

        //when
        String actualUpdateQuery = GeneratorSql.getUpdateQuery(AnimeTestsData.XORIMIYA_ANIME);

        //then
        assertEquals(expectedUpdateQuery, actualUpdateQuery);
    }

    @Test
    public void generatorDeleteQueryTest() {
        //given
        String expectedDeleteQuery = "DELETE FROM Anime WHERE id = 1";

        //when
        String actualDeleteQuery = GeneratorSql.getDeleteQuery(AnimeTestsData.PROSE_OF_STRAY_DOGS_ANIME);

        //then
        assertEquals(expectedDeleteQuery, actualDeleteQuery);
    }
}
