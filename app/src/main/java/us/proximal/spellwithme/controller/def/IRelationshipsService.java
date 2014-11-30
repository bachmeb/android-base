package us.proximal.spellwithme.controller.def;

import us.proximal.spellwithme.model.dto.RelationshipDTO;

/**
 * Created by b on 11/30/14.
 */
public interface IRelationshipsService {

    long makeRelationship(RelationshipDTO dto) throws Exception;

    void changeRelationship(RelationshipDTO dto) throws Exception;

    void endRelationship(RelationshipDTO dto) throws Exception;

}
