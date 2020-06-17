package co.grandcircus.final_project_mh.ExcerciseApi;

public class Exercises {

	private Long tag_id;
	private String user_input;
	private Double duration_min;
	private Double met;
	private Double nf_calories;
	private Photo photo;
	private Long compendium_code; 
	private String name;
	private String description;
	private String benefits;
	public Long getTag_id() {
		return tag_id;
	}
	public void setTag_id(Long tag_id) {
		this.tag_id = tag_id;
	}
	public String getUser_input() {
		return user_input;
	}
	public void setUser_input(String user_input) {
		this.user_input = user_input;
	}
	public Double getDuration_min() {
		return duration_min;
	}
	public void setDuration_min(Double duration_min) {
		this.duration_min = duration_min;
	}
	public Double getMet() {
		return met;
	}
	public void setMet(Double met) {
		this.met = met;
	}
	public Double getNf_calories() {
		return nf_calories;
	}
	public void setNf_calories(Double nf_calories) {
		this.nf_calories = nf_calories;
	}
	public Photo getPhoto() {
		return photo;
	}
	public void setPhoto(Photo photo) {
		this.photo = photo;
	}
	public Long getCompendium_code() {
		return compendium_code;
	}
	public void setCompendium_code(Long compendium_code) {
		this.compendium_code = compendium_code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDesription(String desription) {
		this.description = desription;
	}
	public String getBenefits() {
		return benefits;
	}
	public void setBenefits(String benefits) {
		this.benefits = benefits;
	}
	@Override
	public String toString() {
		return "Exercises [tag_id=" + tag_id + ", user_input=" + user_input + ", duration_min=" + duration_min
				+ ", met=" + met + ", nf_calories=" + nf_calories + ", photo=" + photo + ", compendium_code="
				+ compendium_code + ", name=" + name + ", desription=" + description + ", benefits=" + benefits + "]";
	}
	
	
	
	
	
	
	
	
}
