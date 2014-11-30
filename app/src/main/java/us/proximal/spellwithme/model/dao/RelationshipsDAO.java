package us.proximal.spellwithme.model.dao;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;

import us.proximal.spellwithme.model.ada.ProximalDbAdapter;
import us.proximal.spellwithme.model.def.IRelationshipsDAO;
import us.proximal.spellwithme.model.dto.RelationshipDTO;

/**
 * Created by b on 11/30/14.
 */
public class RelationshipsDAO extends ProximalDbAdapter implements IRelationshipsDAO {


    public static final String TABLE_NAME = "relationships";
    public static final String PRIMARY_KEY = "relationshipId";

    public static final String PRIMARY_ID = "primaryId";
    public static final String SECONDARY_ID = "secondaryId";
    public static final String TYPE = "type";



    public RelationshipsDAO(Activity a) {
        super(a);
    }

    @Override
    public long create(RelationshipDTO dto) throws Exception {

        // Instantiate result value
        long result = 0;

        // Create a ContentValues object
        ContentValues cv = new ContentValues();

        // Add values from DTO fields
        cv.put(PRIMARY_ID, dto.getPrimaryId() );
        cv.put(SECONDARY_ID, dto.getSecondaryId() );
        cv.put(TYPE, dto.getType() );

        // Open the database
        super.open();

        // Insert values
        result = appDatabase.insert(TABLE_NAME, PRIMARY_KEY, cv);

        // Return the result
        return result;

    }

    @Override
    public RelationshipDTO read(int key) throws Exception {

        // Write the query
        String sql  = "select * from " + TABLE_NAME + " where "+ PRIMARY_KEY +" = '" + key +"' ";

        // Run the query
        super.open();
        Cursor cursor = appDatabase.rawQuery(sql, null);

        //Test cursor count
        if (cursor.getCount() == 1) {

            // Move the cursor to the first record
            cursor.moveToFirst();

            // Make a DTO object
            RelationshipDTO dto = populateObjectFromCursor(cursor);

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

    @Override
    public long update(RelationshipDTO dto) throws Exception {

        long result = 0;

        RelationshipDTO relationship;

        // Write the query
        String sql  = "update " + TABLE_NAME +
                " set " +
                RelationshipsDAO.PRIMARY_ID + " = '" + dto.getPrimaryId() + "', " +
                RelationshipsDAO.SECONDARY_ID + " = '" + dto.getSecondaryId() + "', " +
                RelationshipsDAO.TYPE + " = '" + dto.getType() + "', " +

                " where "+ PRIMARY_KEY +" = '" + dto.getRelationshipId() +"' "
                ;

        // Open the database
        super.open();

        // Run the query
        Cursor cursor = appDatabase.rawQuery(sql, null);

        // Get the count
        result = cursor.getCount();

        // Close the database
        super.close();

        // Return the count
        return result;

    }

    @Override
    public void delete(RelationshipDTO dto) throws Exception {

    }


    @Override
    public ArrayList<RelationshipDTO> list() {

        // Make a new ArrayList
        ArrayList<RelationshipDTO> list = new ArrayList<RelationshipDTO>();

        // Write the SQL query
        String sql  = "select * from " + TABLE_NAME;

        // Execute the query
        super.open();
        Cursor cursor = appDatabase.rawQuery(sql, null);

        // Move the cursor to the first record
        cursor.moveToFirst();

        // Loop for as long as the cursor is not after the last record
        while(!cursor.isAfterLast()) {

            // populate the object from the cursor
            RelationshipDTO dto = populateObjectFromCursor(cursor);

            // add the DTO to the collection of DTOs
            list.add(dto);

            // Move the cursor to the next record
            cursor.moveToNext();
        }
        // Return the collection
        return list;
    }


    private RelationshipDTO populateObjectFromCursor(Cursor cursor) {

        // Create a new DTO
        RelationshipDTO dto = new RelationshipDTO();

        // Create variables for each cursor field
        int rid = cursor.getInt(cursor.getColumnIndex(PRIMARY_KEY) );

        int pid = cursor.getInt(cursor.getColumnIndex(PRIMARY_ID));
        int sid = cursor.getInt(cursor.getColumnIndex(SECONDARY_ID));
        String typ = cursor.getString(cursor.getColumnIndex(TYPE));

        // Populate the DTO with the variables
        dto.setRelationshipId(rid);
        dto.setPrimaryId(pid);
        //dto.setLetters(let.toString());
        dto.setSecondaryId(sid);
        dto.setType(typ);

        // Return the object
        return dto;
    }

}
