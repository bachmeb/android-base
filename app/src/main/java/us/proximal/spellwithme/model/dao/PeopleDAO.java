package us.proximal.spellwithme.model.dao;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;

import us.proximal.spellwithme.model.ada.ProximalDbAdapter;
import us.proximal.spellwithme.model.def.IPeopleDAO;
import us.proximal.spellwithme.model.dto.PersonDTO;

/**
 * Created by b on 11/26/14.
 */
public class PeopleDAO extends ProximalDbAdapter implements IPeopleDAO {

    public static final String TABLE_NAME = "people";
    public static final String PRIMARY_KEY = "personId";

    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String BIRTH_DATE = "birthDate";


    public PeopleDAO(Activity a) {
        super(a);
    }

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


    @Override
    public PersonDTO read(int key) throws Exception {

        // Write the query
        String sql  = "select * from " + TABLE_NAME + " where "+ PRIMARY_KEY +" = '" + key +"' ";

        // Run the query
        super.open();
        Cursor cursor = appDatabase.rawQuery(sql, null);


        if (cursor.getCount() == 1) {
            //
            cursor.moveToFirst();

            //
            PersonDTO dto = populateObjectFromCursor(cursor);

            //
            return dto;

        } else if (cursor.getCount() > 1){
            //
            throw new Exception("Too many results returned.  Expected 1, got " + cursor.getCount());
        } else {
            //
            return null;
        }

    }

    @Override
    public long update(PersonDTO dto) throws Exception {
        return 0;
    }

    @Override
    public void delete(PersonDTO dto) throws Exception {

    }

    @Override
    public ArrayList<PersonDTO> list() throws Exception {
        return null;
    }

    private PersonDTO populateObjectFromCursor(Cursor cursor) {

        // Create a new DTO
        PersonDTO dto = new PersonDTO();

        // Create variables for each cursor field
        int pid = cursor.getInt(cursor.getColumnIndex(PRIMARY_KEY) );

        String fir = cursor.getString(cursor.getColumnIndex(FIRST_NAME));
        String las = cursor.getString(cursor.getColumnIndex(LAST_NAME));
        String bir = cursor.getString(cursor.getColumnIndex(BIRTH_DATE));

        // Populate the DTO with the variables
        dto.setPersonId(pid);

        dto.setFirstName(fir);
        dto.setLastName(las);
        dto.setBirthDate(bir);

        // Return the object
        return dto;
    }
}

