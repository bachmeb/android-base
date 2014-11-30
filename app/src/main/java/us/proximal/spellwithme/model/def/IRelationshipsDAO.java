package us.proximal.spellwithme.model.def;

import java.util.ArrayList;

import us.proximal.spellwithme.model.dto.RelationshipDTO;

/**
 * Created by b on 11/30/14.
 */
public interface IRelationshipsDAO {

    long create(RelationshipDTO dto) throws Exception;

    RelationshipDTO read(int key) throws Exception;

    long update(RelationshipDTO dto) throws Exception;

    void delete(RelationshipDTO dto) throws Exception;

    ArrayList<RelationshipDTO> list() throws Exception;

}
