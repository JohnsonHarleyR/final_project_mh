package co.grandcircus.final_project_mh;

public class Rank {

	private int bodyPoints;
	private int soulPoints;
	private int mindPoints;
	
	private int minBodyPoints;
	private int maxBodyPoints;
	
	private int minSoulPoints;
	private int maxSoulPoints;
	
	private int minMindPoints;
	private int maxMindPoints;
	
	private String name;
	
	private int totalPoints;

	
	public Rank(int bodyPoints, int soulPoints, int mindPoints, int minBodyPoints, int maxBodyPoints, int minSoulPoints,
			int maxSoulPoints, int minMindPoints, int maxMindPoints, String name, int totalPoints) {
		super();
		this.bodyPoints = bodyPoints;
		this.soulPoints = soulPoints;
		this.mindPoints = mindPoints;
		this.minBodyPoints = minBodyPoints;
		this.maxBodyPoints = maxBodyPoints;
		this.minSoulPoints = minSoulPoints;
		this.maxSoulPoints = maxSoulPoints;
		this.minMindPoints = minMindPoints;
		this.maxMindPoints = maxMindPoints;
		this.name = name;
		this.totalPoints = totalPoints;
	}

	public int getBodyPoints() {
		return bodyPoints;
	}

	public void setBodyPoints(int bodyPoints) {
		this.bodyPoints = bodyPoints;
	}

	public int getSoulPoints() {
		return soulPoints;
	}

	public void setSoulPoints(int soulPoints) {
		this.soulPoints = soulPoints;
	}

	public int getMindPoints() {
		return mindPoints;
	}

	public void setMindPoints(int mindPoints) {
		this.mindPoints = mindPoints;
	}

	public int getMinBodyPoints() {
		return minBodyPoints;
	}

	public void setMinBodyPoints(int minBodyPoints) {
		this.minBodyPoints = minBodyPoints;
	}

	public int getMaxBodyPoints() {
		return maxBodyPoints;
	}

	public void setMaxBodyPoints(int maxBodyPoints) {
		this.maxBodyPoints = maxBodyPoints;
	}

	public int getMinSoulPoints() {
		return minSoulPoints;
	}

	public void setMinSoulPoints(int minSoulPoints) {
		this.minSoulPoints = minSoulPoints;
	}

	public int getMaxSoulPoints() {
		return maxSoulPoints;
	}

	public void setMaxSoulPoints(int maxSoulPoints) {
		this.maxSoulPoints = maxSoulPoints;
	}

	public int getMinMindPoints() {
		return minMindPoints;
	}

	public void setMinMindPoints(int minMindPoints) {
		this.minMindPoints = minMindPoints;
	}

	public int getMaxMindPoints() {
		return maxMindPoints;
	}

	public void setMaxMindPoints(int maxMindPoints) {
		this.maxMindPoints = maxMindPoints;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(int totalPoints) {
		this.totalPoints = totalPoints;
	}
	
}
