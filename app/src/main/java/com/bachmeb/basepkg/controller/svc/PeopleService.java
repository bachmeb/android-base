package com.bachmeb.basepkg.controller.svc;

import android.app.Activity;

import com.bachmeb.basepkg.controller.def.IPeopleService;
import com.bachmeb.basepkg.model.dao.PeopleDAO;
import com.bachmeb.basepkg.model.def.IPeopleDAO;
import com.bachmeb.basepkg.model.dto.PersonDTO;

import java.util.ArrayList;

/**
 * Created by b on 11/29/14.
 */
public class PeopleService implements IPeopleService {

    IPeopleDAO daoPeople;
    ArrayList<PersonDTO> people;

    public PeopleService(Activity act) {
        daoPeople = new PeopleDAO(act);
        try {
            people = daoPeople.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public long addPerson(PersonDTO dto) throws Exception  {
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
    public long deletePerson(PersonDTO dto) throws Exception  {
        return 0;
    }

    @Override
    public long updatePerson(PersonDTO dto) throws Exception  {

        long result = 0;

        try {
            result = daoPeople.update(dto);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }

    @Override
    public ArrayList<PersonDTO> listPeople() throws Exception  {
        try {
            people = daoPeople.list();
            return people;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
