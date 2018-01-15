package my.edu.umk.pams.account.identity.model;

public enum AcStudentStatus {
	MATRICULATED, //0
	ACTIVE,		  //1
	BARRED,		  //2
	INACTIVE,	  //3
	GRADUATED;	  //4
	
	public static AcStudentStatus get(int index){
        return values()[index];
    }
}
