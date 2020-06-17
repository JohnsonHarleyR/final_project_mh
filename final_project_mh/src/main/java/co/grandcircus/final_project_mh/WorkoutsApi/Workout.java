package co.grandcircus.final_project_mh.WorkoutsApi;

public class Workout {

	private ExerciseInfo exercise;

	public ExerciseInfo getExcerciseInfo() {
		return exercise;
	}

	public void setExcerciseInfo(ExerciseInfo excerciseInfo) {
		this.exercise = excerciseInfo;
	}


	@Override
	public String toString() {
		return "Workout [exercise=" + exercise + "]";
	}
	
	
	
	
	
}
