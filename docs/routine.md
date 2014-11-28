
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
####create constructor and send Context object to super constructor
```java
    public ThingsDAO(Context context) {

        super(context, "app.db", null, 1);
    }
```
####define static fields for table name, primary key, and all fields in DTO
```java

    private static final String TABLE_NAME = "things";
    private static final String PRIMARY_KEY = "id";

    private static final String NAME = "name";
    private static final String DATE = "date";

```

####create populateObjectFromCursor() method
```java

    private ThingDTO populateObjectFromCursor(Cursor cursor) {

        // create a new user object to hold our data.
        ThingDTO dto = new ThingDTO();

        // populate object with DTO fields
        int id = cursor.getInt(cursor.getColumnIndex(PRIMARY_KEY) );
        //String date = cursor.get(cursor.getColumnIndex(DATE));
        String name = cursor.getString(cursor.getColumnIndex(NAME));

        // populate the dto
        dto.setId(id);
        //dto.setDate(date);
        dto.setName(name);

        //
        return dto;
    }
```
####Create onUpdate() method

###Make Service interface
####Define methods to match UI buttons
###Make Service class
####Implement Service interface
####Instantiate DAO object
####Call DAO methods in Serivce methods to read/write to DB
