public class contact{
    //class used for helper methods used in sorts, compares and equals

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

    @Override
    public boolean equals(Object that){
        if(that == this)
            return true;
        if(!(that instanceof contact))
            return false;

        contact another = (contact) that;

        return this.phoneNumber.equals(another.phoneNumber) && this.surName.equals(another.surName) && this.phoneNumber.equals(another.phoneNumber);
    }

    public int compareTo(contact that){

        if(this.name.compareTo(that.name)>0)
            return 1;

        if(this.name.compareTo(that.name)<0)
            return -1;
        if(this.surName.compareTo(that.surName)>0)
            return 1;

        if(this.surName.compareTo(that.surName)<0)
            return -1;
        if(this.phoneNumber.compareTo(that.phoneNumber)>0)
            return 1;

        if(this.phoneNumber.compareTo(that.phoneNumber)<0)
            return -1;

        return 0;
    }

}
