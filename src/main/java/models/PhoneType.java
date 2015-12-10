package models;

public class PhoneType {
    private int typeId;
    private String phoneType;

    public PhoneType(int typeId, String phoneType) {
        this.phoneType = phoneType;
        this.typeId = typeId;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public int getTypeId() {
        return typeId;
    }
}
