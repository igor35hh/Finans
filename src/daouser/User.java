package daouser;

import java.sql.ResultSet;

public class User {
	
	private String id;
	private String name;
    
    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public ResultSet getResult(ResultSet rs) {
        return rs;
    }

}
