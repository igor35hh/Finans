package daouser;

import java.sql.ResultSet;

public class Users {
	
	public String id;
    public String name;
    public ResultSet rs;
    
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
    
    public ResultSet getResult() {
        return rs;
    }

    public void setResult(ResultSet rs) {
        this.rs = rs;
    }

}
