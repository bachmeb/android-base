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

        // Open the database
        super.open();

        // Run the query
        Cursor cursor = appDatabase.rawQuery(sql, null);

        //TODO: remove this count
        result = cursor.getCount();

        //Close the database
        super.close();

        // Return the result
        return result;

    }

    @Override
    public void delete(PersonDTO dto) throws Exception {

    }

    @Override
    public ArrayList<PersonDTO> list() {

        // Make a new ArrayList
        ArrayList<PersonDTO> list = new ArrayList<PersonDTO>();

        // Write the SQL query
        String sql  = "select * from " + TABLE_NAME;

        // Open the database
        super.open();

        // Execute the query
        Cursor cursor = appDatabase.rawQuery(sql, null);

        // Move the cursor to the first record
        cursor.moveToFirst();

        // Loop for as long as the cursor is not after the last record
        while(!cursor.isAfterLast()) {

            // populate the object from the cursor
            PersonDTO dto = populateObjectFromCursor(cursor);

            // add the DTO to the collection of DTOs
            list.add(dto);

            // Move the cursor to the next record
            cursor.moveToNext();
        }
        // Close the database
        super.close();

        // Return the collection
        return list;
    }

    @Override
    public ArrayList<PersonDTO> list(String[] select, String[] from, String[][] where) throws Exception {

//        SELECT Name, Day FROM Customers, Reservations
//        WHERE Customers.CustomerId = Reservations.CustomerId;

        StringBuilder sbSelect = new StringBuilder();
        StringBuilder sbFrom = new StringBuilder();
        StringBuilder sbWhere = new StringBuilder();

        sbSelect.append("SELECT ");
        for(int i=0;i<select.length;i++){
            if(i>0){
                sbSelect.append(", ");
            }
            sbSelect.append(select[i]);
        }

        sbFrom.append(" FROM ");
        for(int i=0;i<from.length;i++){
            if(i>0){
                sbFrom.append(", ");
            }
            sbFrom.append(from[i]);
        }

        sbWhere.append(" WHERE ");
        for(int i=0;i<where.length;i++){
            if(i>0){
                sbWhere.append(" AND ");
            }
            sbWhere.append(where[i][0]);
            sbWhere.append("='");
            sbWhere.append(where[i][1]);
            sbWhere.append("' ");
        }

        // Make a new ArrayList
        ArrayList<PersonDTO> list = new ArrayList<PersonDTO>();

        // Write the SQL query
        String sql  = sbSelect.toString() + sbFrom.toString() + sbWhere.toString();

        // Open the database
        super.open();

        // Execute the query
        Cursor cursor = appDatabase.rawQuery(sql, null);

        // Move the cursor to the first record
        cursor.moveToFirst();

        // Loop for as long as the cursor is not after the last record
        while(!cursor.isAfterLast()) {

            // populate the object from the cursor
            PersonDTO dto = populateObjectFromCursor(cursor);

            // add the DTO to the collection of DTOs
            list.add(dto);

            // Move the cursor to the next record
            cursor.moveToNext();
        }
        // Close the database
        super.close();

        // Return the collection
        return list;


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

