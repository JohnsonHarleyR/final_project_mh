package co.grandcircus.final_project_mh.ExcerciseApi;

public class Photo {

	private String highres;
	private String thumb;
	private Boolean is_user_uploaded;
	public String getHighres() {
		return highres;
	}
	public void setHighres(String highres) {
		this.highres = highres;
	}
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	public Boolean getIs_user_uploaded() {
		return is_user_uploaded;
	}
	public void setIs_user_uploaded(Boolean is_user_uploaded) {
		this.is_user_uploaded = is_user_uploaded;
	}
	@Override
	public String toString() {
		return "Photo [highres=" + highres + ", thumb=" + thumb + ", is_user_uploaded=" + is_user_uploaded + "]";
	}
	
	
	
	
}
