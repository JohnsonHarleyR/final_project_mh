package co.grandcircus.final_project_mh.QuoteApi;

public class Categories {
	
	private String inspire;
	private String management;
	private String sports;
	private String life;
	private String funny;
	private String love;
	private String art;
	private String students;
	
	//@return GET inspire
	public String getInspire() {
		return inspire;
	}
	
	//@param SET inspire
	public void setInspire(String inspire) {
		this.inspire = inspire;
	}
	
	//@return GET management
	public String getManagement() {
		return management;
	}
	
	//@param SET management
	public void setManagement(String management) {
		this.management = management;
	}
	
	//@return GET sports
	public String getSports() {
		return sports;
	}
	
	//@param SET sports
	public void setSports(String sports) {
		this.sports = sports;
	}
	
	//@return GET life
	public String getLife() {
		return life;
	}
	
	//@param SET life
	public void setLife(String life) {
		this.life = life;
	}
	
	//@return GET funny
	public String getFunny() {
		return funny;
	}
	
	//@param SET funny
	public void setFunny(String funny) {
		this.funny = funny;
	}
	
	//@return GET love
	public String getLove() {
		return love;
	}
	
	//@param SET love
	public void setLove(String love) {
		this.love = love;
	}
	
	//@return GET art
	public String getArt() {
		return art;
	}
	
	//@param SET art
	public void setArt(String art) {
		this.art = art;
	}
	
	//@return GET students
	public String getStudents() {
		return students;
	}
	
	//@param SET students
	public void setStudents(String students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "Categories [inspire=" + inspire + ", management=" + management + ", sports=" + sports + ", life=" + life
				+ ", funny=" + funny + ", love=" + love + ", art=" + art + ", students=" + students + "]";
	}
	
	

}
