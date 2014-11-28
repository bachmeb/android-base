
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
####implement create() method
```java 

    @Override
    public boolean create(ThingDTO dto) throws Exception {


        // Create a ContentValues object with same number of elements as DTO fields (minus 2)
        ContentValues cv = new ContentValues(5);
        
        // Add values from DTO fields
        cv.put(QUESTION_ID, dto.getQuestionId() );
        cv.put(STUDENT_ID, dto.getStudentId() );
        cv.put(MKO_ID, dto.getMkoId() );
        cv.put(DATE, dto.getDate().toString() );
        cv.put(GRADE,  dto.getGrade() );

        // put the values into database
        getWritableDatabase().insert(TABLE_NAME, PRIMARY_KEY, cv);

        return true;

    }
```
####Implement read() method
```java
    @Override
    public WordDTO read(int key) throws Exception {

        // our flexible query.
        String fetchQuery  = "select * from " + TABLE_NAME + " where "+ PRIMARY_KEY +" = '" + key +"' ";

        // run the query.
        Cursor cursor = getReadableDatabase().rawQuery(fetchQuery, null);

        if (cursor.getCount() == 1) {
        
            // move cursor to the first record
            cursor.moveToFirst();

            // Make DTO
            WordDTO dto = populateObjectFromCursor(cursor);

            // return DTO
            return dto;
            
        } else if (cursor.getCount() > 1){
            //Too many results
            throw new Exception("Too many results returned.  Expected 1, got " + cursor.getCount());
        } else {
            // No results
            return null;
        }
    }
```


####Implement update() method
```java
    @Override
    public void update(WordDTO word) {

    }
```
####Implment delete() method
```java
    @Override
    public void delete(WordDTO word) {

    }
```

####Implement list() method
```java

    @Override
    public ArrayList<WordDTO> list() {

        // Make a new ArrayList
        ArrayList<WordDTO> words = new ArrayList<WordDTO>();

        // Write the SQL query
        String sql  = "select * from " + TABLE_NAME;

        // Execute the query
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);

        // Move the cursor to the first record
        cursor.moveToFirst();

        // Loop for as long as the cursor is not after the last record
        while(!cursor.isAfterLast()) {
            // populate the object from cursor
            WordDTO dto = populateObjectFromCursor(cursor);

            // add the DTO to the collection of DTOs
            words.add(dto);

            // Move the cursor to the next record
            cursor.moveToNext();
        }
        // Return the collection
        return words;
    }
```
    
####create populateObjectFromCursor() method
```java

    private WordDTO populateObjectFromCursor(Cursor cursor) {

        // Create a new DTO
        WordDTO dto = new WordDTO();
        
        // Create variables for each cursor field
        int wid = cursor.getInt(cursor.getColumnIndex(PRIMARY_KEY) );
        String wor = cursor.getString(cursor.getColumnIndex(WORD));
        String let = cursor.getString(cursor.getColumnIndex(LETTERS));
        int len = cursor.getInt(cursor.getColumnIndex(LENGTH));
        String beg = cursor.getString(cursor.getColumnIndex(BEGINS_WITH));
        String end = cursor.getString(cursor.getColumnIndex(ENDS_WITH));
        String cat = cursor.getString(cursor.getColumnIndex(CATEGORY));
        String lan = cursor.getString(cursor.getColumnIndex(LANGUAGE));
        int dol = cursor.getInt(cursor.getColumnIndex(DOLCH));

        // Populate the DTO with the variables
        dto.setWordId(wid);
        dto.setWord(wor);
        //dto.setLetters(let.toString());
        dto.setLength(len);
        dto.setBeginsWith(beg);
        dto.setEndsWith(end);
        dto.setCategory(cat);
        dto.setLanguage(lan);
        dto.setDolch(  (dol==1) ? true : false );
        
        // Return the object
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
    public void onCreate(SQLiteDatabase db) {
        //drop the table if it already exists
        this.onUpdate(db);

        // define the schema
        String schema = "CREATE TABLE "
                + TABLE_NAME
                + " ("
                + PRIMARY_KEY
                + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + WORD
                + " TEXT, "
                + LETTERS
                + " TEXT, "
                + LENGTH
                + " INTEGER, "
                + BEGINS_WITH
                + " TEXT, "
                + ENDS_WITH
                + " TEXT, "
                + CATEGORY
                + " TEXT, "
                + LANGUAGE
                + " TEXT, "
                + DOLCH
                + " TEXT );";
        
        // create the table
        db.execSQL(schema);

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
#####Attach onClick listener to the button object
```java
        btnReadYes.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View currentView) {

                      doSomething();

                  }
              }
        );
```