package us.proximal.spellwithme.model.dto;

/**
 * Created by b on 11/30/14.
 */
public class RelationshipDTO {

    private int relationshipId;
    private int primaryId;
    private int secondaryId;
    private String type;

    public int getPrimaryId() {
        return primaryId;
    }

    public void setPrimaryId(int primaryId) {
        this.primaryId = primaryId;
    }

    public int getRelationshipId() {
        return relationshipId;
    }

    public void setRelationshipId(int relationshipId) {
        this.relationshipId = relationshipId;
    }

    public int getSecondaryId() {
        return secondaryId;
    }

    public void setSecondaryId(int secondaryId) {
        this.secondaryId = secondaryId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
