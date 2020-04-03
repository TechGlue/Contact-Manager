public class contact{

    private String name;
    private String surName;
    private String phoneNumber;

    public contact(String name, String surName, String phoneNumber){
        this.name = name;
        this.surName = surName;
        this.phoneNumber = phoneNumber;
    }


    @Override
    public String toString(){
        return "First Name: " + name + "\nLast Name: " + surName + "\nPhone: " + phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getSurName() {
        return surName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

}
