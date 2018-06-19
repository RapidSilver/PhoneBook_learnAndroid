package ru.android.kira.phonebook;



public class Contact {
    int id;
    String name;
    String family;
    String phone;
    String photo;


    public Contact() {

    }

    public Contact(String name, String family, String phone, String photo) {
        this.name = name;
        this.family = family;
        this.phone = phone;
        this.photo = photo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
