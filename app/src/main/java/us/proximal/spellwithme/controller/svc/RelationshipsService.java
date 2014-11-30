package us.proximal.spellwithme.controller.svc;

import android.app.Activity;

import java.util.ArrayList;

import us.proximal.spellwithme.controller.def.IRelationshipsService;
import us.proximal.spellwithme.model.dao.RelationshipsDAO;
import us.proximal.spellwithme.model.def.IRelationshipsDAO;
import us.proximal.spellwithme.model.dto.RelationshipDTO;

/**
 * Created by b on 11/30/14.
 */
public class RelationshipsService implements IRelationshipsService {

    IRelationshipsDAO daoRelationships;
    ArrayList<RelationshipDTO> relationships;

    public RelationshipsService(Activity act){
        daoRelationships = new RelationshipsDAO(act);
        try {
            relationships = daoRelationships.list();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public long makeRelationship(RelationshipDTO dto) throws Exception {
        //
        long result = 0;
        //
        try {
            result = daoRelationships.create(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //
        return result;

    }

    @Override
    public void changeRelationship(RelationshipDTO dto) throws Exception  {

    }

    @Override
    public void endRelationship(RelationshipDTO dto) throws Exception  {

    }
}
