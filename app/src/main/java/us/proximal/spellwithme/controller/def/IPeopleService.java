package us.proximal.spellwithme.controller.def;

import java.util.ArrayList;

import us.proximal.spellwithme.model.dto.PersonDTO;

/**
 * Created by b on 11/29/14.
 */
public interface IPeopleService {

    long addPerson(PersonDTO dto) throws Exception;
    long deletePerson(PersonDTO dto) throws Exception;
    long updatePerson(PersonDTO dto) throws Exception;

    ArrayList<PersonDTO> getListOfPeople() throws Exception;

    ArrayList<PersonDTO> getListOfMyStudents() throws Exception;

    PersonDTO getOnePersonObject(int key) throws Exception;

    PersonDTO getMyPersonObject() throws Exception;



}
