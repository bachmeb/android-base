
###Make DTO
####Define fields
```java
public class ThingDTO {

    private int id;
    private String name;
    private Date date;

}

```
Make getters/setters
```java



```
###Make DAO interface
####Define CRUDL methods
```java
public interface IThingsDAO {

    boolean create(ThingDTO thing) throws Exception;

    ThingDTO read(int thingId) throws Exception;

    boolean update(ThingDTO thing) throws Exception;

    void delete(ThingDTO thing) throws Exception;

    ArrayList<ThingDTO> list();
}
```

###Make DAO
####extend SQLiteOpenHelper and implement DAO interface
```java
public class AnswersDAO extends SQLiteOpenHelper implements IAnswersDAO {}
```

####implement abstract methods
####create constructor
```java
    public ThingsDAO(Context context) {

        super(context, "app.db", null, 1);
    }
```
####send Context object to super constructor in constructor
####define static fields for table name, primary key, and all fields in DTO
####create populateObjectFromCursor() method
####Create onUpdate() method

###Make Service interface
####Define methods to match UI buttons
###Make Service class
####Implement Service interface
####Instantiate DAO object
####Call DAO methods in Serivce methods to read/write to DB
