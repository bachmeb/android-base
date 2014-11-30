
###Make DTO
####Define fields
```java
public class ThingDTO {

    private int id;
    private String name;
    private Date date;

}

```
####Make getters/setters
```java

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

```
###Update DbAdapter
####Add table definition
```java

    public static final String SQL_QUESTIONS = "CREATE TABLE " + QuestionsDAO.TABLE_NAME + " (" +
            QuestionsDAO.PRIMARY_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            QuestionsDAO.WORD_ID + " INTEGER, " +
            QuestionsDAO.WORD + " TEXT, " +
            QuestionsDAO.DESCRIPTION + " TEXT, " +
            QuestionsDAO.TEXT + " TEXT, " +
            QuestionsDAO.TYPE + " TEXT " +
            ");";
```
####Add db.execSQL() statement to onCreate() method
```java
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_QUESTIONS);
            db.execSQL(SQL_WORDS);
            db.execSQL(SQL_ANSWERS);
        }

```
####Add db.execSQL() statement to onUpgrade() method
```java
        @Override
        public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
            db.execSQL("DROP TABLE IF EXISTS " + QuestionsDAO.TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + WordsDAO.TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + AnswersDAO.TABLE_NAME);
            onCreate(db);
        }
```
####Update database version
```java

    public static final int DATABASE_VERSION = 3;
```
###Make DAO interface
####Define CRUDL methods
```java

public interface IPeopleDAO {
    
    boolean create(PersonDTO dto) throws Exception;
    
    PersonDTO read(int key) throws Exception;
    
    boolean update(PersonDTO dto) throws Exception;
    
    void delete(PersonDTO dto) throws Exception;
    
    ArrayList<PersonDTO> list() throws Exception;
    
}
```

###Make DAO
####extend database adapter and implement DAO interface
```java
public class AnswersDAO extends ProximalDbAdapter implements IAnswersDAO {}
```

####implement abstract methods
```java

    @Override
    public boolean create(PersonDTO dto) throws Exception {
        return false;
    }

    @Override
    public PersonDTO read(int key) throws Exception {
        return null;
    }

    @Override
    public boolean update(PersonDTO dto) throws Exception {
        return false;
    }

    @Override
    public void delete(PersonDTO dto) throws Exception {

    }

    @Override
    public ArrayList<PersonDTO> list() throws Exception {
        return null;
    }

```
####create constructor matching super
```java

    public PeopleDAO(Activity a) {
        super(a);
    }
```
####define static fields for table name, primary key, and all fields in DTO
```java


    public static final String TABLE_NAME = "answers";
    public static final String PRIMARY_KEY = "answerId";

    public static final String QUESTION_ID = "questionId";
    public static final String STUDENT_ID = "studentId";
    public static final String MKO_ID = "mkoId";
    public static final String DATE = "date";
    public static final String GRADE = "grade";
    
```
####implement create() method
```java 

    @Override
    public long create(PersonDTO dto) throws Exception {

        // Instantiate result value
        long result = 0;
        
        // Create a ContentValues object
        ContentValues cv = new ContentValues();

        // Add values from DTO fields
        cv.put(FIRST_NAME, dto.getFirstName() );
        cv.put(LAST_NAME, dto.getLastName() );
        cv.put(BIRTH_DATE, dto.getBirthDate() );

        // Open the database
        super.open();

        // Insert values
        result = appDatabase.insert(TABLE_NAME, PRIMARY_KEY, cv);

        // Return the result
        return result;

    }
```
####Implement read() method
```java
    @Override
    public QuestionDTO read(int key) throws Exception {

        // Write the query
        String sql  = "select * from " + TABLE_NAME + " where "+ PRIMARY_KEY +" = '" + key +"' ";

        // Run the query
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);

        //Test cursor count
        if (cursor.getCount() == 1) {

            // Move the cursor to the first record
            cursor.moveToFirst();

            // Make a DTO object
            QuestionDTO dto = populateObjectFromCursor(cursor);

            // return DTO
            return dto;

        } else if (cursor.getCount() > 1){
            // More than one
            throw new Exception("Too many results returned.  Expected 1, got " + cursor.getCount());
        } else {
            // Less than one
            return null;
        }
    }
    
```


####Implement update() method
```java
    @Override
    public long update(PersonDTO dto) throws Exception {

        long result = 0;

        PersonDTO person;

        // Write the query
        String sql  = "update " + TABLE_NAME +
                " set " +
                PeopleDAO.FIRST_NAME + " = '" + dto.getFirstName() + "', " +
                PeopleDAO.LAST_NAME + " = '" + dto.getLastName() + "', " +
                PeopleDAO.BIRTH_DATE + " = '" + dto.getBirthDate() + "' " +
                " where "+ PRIMARY_KEY +" = '" + dto.getPersonId() +"' "
                ;

        // Run the query
        super.open();

        Cursor cursor = appDatabase.rawQuery(sql, null);

        result = cursor.getCount();

        return result;

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
    public ArrayList<QuestionDTO> list() {

        // Make a new ArrayList
        ArrayList<QuestionDTO> list = new ArrayList<QuestionDTO>();

        // Write the SQL query
        String sql  = "select * from " + TABLE_NAME;

        // Execute the query
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);

        // Move the cursor to the first record
        cursor.moveToFirst();

        // Loop for as long as the cursor is not after the last record
        while(!cursor.isAfterLast()) {
            
            // populate the object from the cursor
            QuestionDTO dto = populateObjectFromCursor(cursor);

            // add the DTO to the collection of DTOs
            list.add(dto);

            // Move the cursor to the next record
            cursor.moveToNext();
        }
        // Return the collection
        return list;
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
    IPeopleDAO daoPeople;
    ArrayList<PersonDTO> people;
```
####Make a constructor for the service and require an Activity
```java
    public ThingsService(Activity act){
    }

```
####Instantiate the DAO in the constructor and send it the Context object
```java

    public PeopleService(Activity act){
        daoPeople = new PeopleDAO(act);
        try {
            people = daoPeople.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    public long addPerson(PersonDTO dto) {
        //
        long result = 0;
        //
        try {
            result = daoPeople.create(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //
        return result;
    }
    
    @Override
    public ArrayList<PersonDTO> getListOfPeople() {
        try {
            people = daoPeople.list();
            return people;
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public PersonDTO getOnePersonObject(int key) {
        //
        PersonDTO person;
            //
            try {
                person = daoPeople.read(key);
                return person;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        
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
####Java class extends BaseActivity
```java

public class People extends BaseActivity {
```

####Remove onCreateOptionsMenu() and onOptionsItemSelected()
```java
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.people, menu);
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

    // Declare service
    private IPeopleService svcPeople;
```
####Edit onCreate method
#####Instantiate service 
```java

        // Instantiate service
        svcPeople = new PeopleService(this);
        
```
#####Initialize objects for UI components
```java
        // Initialize objects
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
#####If the button is opening another Activity...
```java


    private void openMeActivity(View view) {

        // declare the intent
        Intent intent = new Intent(view.getContext(), Read.class);
        // act on the intent
        startActivity(intent);

    }
```





