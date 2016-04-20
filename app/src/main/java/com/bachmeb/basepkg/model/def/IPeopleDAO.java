package com.bachmeb.basepkg.model.def;

import com.bachmeb.basepkg.model.dto.PersonDTO;

import java.util.ArrayList;

/**
 * Created by b on 11/26/14.
 */
public interface IPeopleDAO {

    long create(PersonDTO dto) throws Exception;

    PersonDTO read(int key) throws Exception;

    long update(PersonDTO dto) throws Exception;

    void delete(PersonDTO dto) throws Exception;

    ArrayList<PersonDTO> list() throws Exception;

    ArrayList<PersonDTO> list(String[] select, String[] from, String[][] where) throws Exception;

}
