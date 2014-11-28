
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
```java
    public void onUpdate(SQLiteDatabase db) {

        //Run a DROP IF EXISTS statement to drop the ACCOUNTS table.
        //Invoke onCreate.

        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_NAME + "'");

    }
```

####Implement onCreate method
Reference: http://www.sqlite.org/datatype3.html
```java

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //drop the table if it exists
        this.onUpdate(sqLiteDatabase);

        // define the schema.
        String schema = "CREATE TABLE "
                + TABLE_NAME
                + " ("
                + PRIMARY_KEY
                + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME
                + " TEXT, "
                + DATE
                + " TEXT );";

        // create our table.
        sqLiteDatabase.execSQL(schema);

    }
```
####Leave onUpgrade method empty
```java

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        // Empty method from SQLiteOpenHelper
    }
```

###Make Service interface
```java

public interface IThingsService {

}
```

####Define methods to match UI buttons
```java

    public void createThing();
    public void deleteThing();

```

###Make Service class and implement Service interface
```java
public class ThingsService implements IThingsService{


}
```
####Declare the DAO object
```java
    IThingsDAO daoThings;
```
####Make a constructor for the service and require a context object
```java
    public ThingsService(Context ctx){
    }

```
####Instantiate the DAO in the constructor and send it the Context object
```java
    public ThingsService(Context ctx){
        daoThings = new ThingsDAO(ctx);
        things = daoThings.list();
    }
```

####Implement interface methods
```java
    @Override
    public void createThing() {
    }
    @Override
    public void deleteThing() {
    }
```

####Call DAO methods in Serivce methods to read/write to DB
```java
    @Override
    public void createThing() {
        try {
            daoThings.create(answer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void deleteThing() {
        //TODO
    }

```
####Create activity
```java
public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
```

```xml
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    android:paddingBottom="@dimen/activity_vertical_margin"
    tools:context="us.proximal.spellwithme.view.MyActivity">

    <TextView
        android:text="@string/hello_world"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

</RelativeLayout>
```
####Add components to layout xml
```xml


    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textAppearance="?android:attr/textAppearanceLarge"
        android:text="Let's Read"
        android:id="@+id/textReadTitle"
        android:layout_alignParentTop="true"
        android:layout_centerHorizontal="true" />

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textAppearance="?android:attr/textAppearanceLarge"
        android:text="What does this word say?"
        android:paddingTop="64dp"
        android:id="@+id/textReadQuestion"
        android:layout_below="@+id/textReadTitle"
        android:layout_centerHorizontal="true" />

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textAppearance="?android:attr/textAppearanceLarge"
        android:text="WORD"
        android:id="@+id/textReadWord"

        android:layout_centerVertical="true"
        android:layout_centerHorizontal="true" />

    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Got it"
        android:id="@+id/buttonReadYes"

        android:layout_alignParentBottom="true"
        android:layout_alignParentLeft="true"

        />

    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Missed It"
        android:id="@+id/buttonReadNo"

        android:layout_alignParentBottom="true"
        android:layout_alignParentRight="true"
        android:layout_alignParentEnd="true" />
```
####Declare objects in java to match components in XML
```java
    private Button btnReadYes;
    private Button btnReadNo;
    private TextView txtWord;
    private WordDTO word;
    private QuestionDTO question;
```

####Declare Service object as class field
```java
private IThingsService service;
```
####Edit onCreate method
#####Instantiate service 
```java
        service = new ThingsService();
```
#####Initialize objects for UI components
```java
        btnReadYes = (Button) findViewById(R.id.buttonReadYes);
        btnReadNo = (Button) findViewById(R.id.buttonReadNo);
        txtWord = (TextView) findViewById(R.id.textReadWord);
```
