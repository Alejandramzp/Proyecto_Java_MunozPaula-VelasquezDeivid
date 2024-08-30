
package Model;

public class RoleModel {
    private int RoleID;
    private String RolName;
    private int activity1ID;
    private int activity2ID;

    public RoleModel(int RoleID, String RolName, int activity1ID, int activity2ID) {
        this.RoleID = RoleID;
        this.RolName = RolName;
        this.activity1ID = activity1ID;
        this.activity2ID = activity2ID;
    }

    public int getRoleID() {
        return RoleID;
    }

    public void setRoleID(int RoleID) {
        this.RoleID = RoleID;
    }

    public String getRolName() {
        return RolName;
    }

    public void setRolName(String RolName) {
        this.RolName = RolName;
    }

    public int getActivity1ID() {
        return activity1ID;
    }

    public void setActivity1ID(int activity1ID) {
        this.activity1ID = activity1ID;
    }

    public int getActivity2ID() {
        return activity2ID;
    }

    public void setActivity2ID(int activity2ID) {
        this.activity2ID = activity2ID;
    }

    @Override
    public String toString() {
        return "RoleModel{" + "RoleID= " + RoleID + ", RolName= " + RolName + ", activity1ID= " + activity1ID + ", activity2ID= " + activity2ID + '}';
    }
    
    
    
}
