package bf.kulturman.model;

public class Contact
{
    private String name , firstname , mail , image , birth , number;
    private int id;
	
    public String getName()
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    public String getMail()
    {
        return mail;
    }

    public void setMail(String mail)
    {
        this.mail = mail;
    }

    public String getImage() 
    {
        return image;
    }

    public void setImage(String image)
    {
        this.image = image;
    }

    public String getBirth() 
    {
        return birth;
    }

    public void setBirth(String birth) 
    {
        this.birth = birth;
    }

    public String getNumber() 
    {
        return number;
    }

    public void setNumber(String number) 
    {
        this.number = number;
    }

    public Contact(){}
    public Contact(String name, String firstname, String mail, String image, String birth, String number)
    {
        this.name = name;
        this.firstname = firstname;
        this.mail = mail;
        this.image = image;
        this.birth = birth;
        this.number = number;
    }
        
    public int getId()
    {
    	return id;
    }
    
    public void setId(int id)
    {
    	this.id = id;
    }
}
