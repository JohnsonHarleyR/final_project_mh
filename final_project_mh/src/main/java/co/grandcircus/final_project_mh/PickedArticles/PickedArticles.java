package co.grandcircus.final_project_mh.PickedArticles;
import java.util.HashMap;

public class PickedArticles {
	
	HashMap<String, PickedArticle> map = new HashMap<>();
	
	public PickedArticles() {
		
		//Add chosen articles
		map.put("important",
				new PickedArticle("How sleep, exercise and food "
						+ "affect your mental health", 
"Have you ever wondered how what you eat affects what you think?\r\n" + 
"Evidence reveals a definite connection between chronic diseases and mental health disorders. A certain number of "
+ "conditions are related to genetics and others can be traced to environmental factors. "
+ "Still, studies show lifestyle have an impact on risk factors.",
"https://www.thatorganicmom.com/sleep-exercise-food-mental-health/",
"https://www.thatorganicmom.com/wp-content/uploads/2018/01/how-sleep-exercise-and-food-affect-your-mental-health-3-1024x682.jpg"));
		
		map.put("mindfulness",
				new PickedArticle("Mindfulness for Mental Health",
						"The good news is that simple changes in lifestyle can lead to improved mental health and wellbeing. "
						+ "Mindfulness is one such practice—with strong research supporting its usefulness for those suffering from anxiety, "
						+ "depression, or even just daily stress.", 
						"https://www.takingcharge.csh.umn.edu/mindfulness-mental-health",
						"https://www.takingcharge.csh.umn.edu/sites/default/files/alex-klopcic-204589%20(1).jpg"));
		
		
	}

	public HashMap<String, PickedArticle> getMap() {
		return map;
	}

	public void setMap(HashMap<String, PickedArticle> map) {
		this.map = map;
	}

	@Override
	public String toString() {
		return "PickedArticles [map=" + map + "]";
	}
	
	
	
	

}
