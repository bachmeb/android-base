package com.bachmeb.basepkg.controller.def;

import com.bachmeb.basepkg.model.dto.PersonDTO;

import java.util.ArrayList;

/**
 * Created by b on 11/29/14.
 */
public interface IPeopleService {

    long addPerson(PersonDTO dto) throws Exception;
    long deletePerson(PersonDTO dto) throws Exception;
    long updatePerson(PersonDTO dto) throws Exception;

    ArrayList<PersonDTO> listPeople() throws Exception;

}
