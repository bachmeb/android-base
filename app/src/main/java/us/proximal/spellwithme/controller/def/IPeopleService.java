package us.proximal.spellwithme.controller.def;

import java.util.ArrayList;

import us.proximal.spellwithme.model.dto.PersonDTO;

/**
 * Created by b on 11/29/14.
 */
public interface IPeopleService {

    long addPerson(PersonDTO dto);
    long deletePerson(PersonDTO dto);
    long updatePerson(PersonDTO dto);

    ArrayList<PersonDTO> getListOfPeople();

    ArrayList<PersonDTO> getListOfMyStudents();

    PersonDTO getOnePersonObject(int key);

    PersonDTO getMyPersonObject();



}
