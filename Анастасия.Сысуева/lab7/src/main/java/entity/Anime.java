package entity;

import logic.IdPK;
import logic.TableName;

@TableName(name = "Anime")
public record Anime(@IdPK int id, String animeName, String filmmakerName, String genre, int yearOfCreation,
                    double rating) {

}