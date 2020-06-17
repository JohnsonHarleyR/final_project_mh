package co.grandcircus.final_project_mh.Favorites;

	import java.sql.Date;

	import javax.persistence.Column;
	import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.GenerationType;
	import javax.persistence.Id;
	import javax.persistence.Table;

	@Entity
	@Table(name = "favorite_exercises")
	public class FavExercises implements Comparable<FavExercises> {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		private Date datetime;
		private String name;
		@Column(name = "user_id")
		private Long userId;
		private Double nf_calories;
		private Double duration_min;
		
		public FavExercises() {}
		
		public FavExercises(Date datetime, Double nf_calories, Double duration_min, String name, Long userId) {
			this.datetime = datetime;
			this.nf_calories = nf_calories;
			this.duration_min = duration_min;
			this.name = name;
			this.userId = userId;
			
		}
		
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Date getDatetime() {
			return datetime;
		}

		public void setDatetime(Date datetime) {
			this.datetime = datetime;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

		public Double getNf_calories() {
			return nf_calories;
		}

		public void setNf_calories(Double nf_calories) {
			this.nf_calories = nf_calories;
		}

		public Double getDuration_min() {
			return duration_min;
		}

		public void setDuration_min(Double duration_min) {
			this.duration_min = duration_min;
		}
		
		
		
		

		@Override
		public String toString() {
			return "FavExercises [id=" + id + ", datetime=" + datetime + ", name=" + name + ", userId=" + userId
					+ ", nf_calories=" + nf_calories + ", duration_min=" + duration_min + "]";
		}

		//Compare by date. If they're the same, compare by id order.
		//(Guess you could probably just compare by id but oh well. This is
		//more error proof.)
		@Override
		public int compareTo(FavExercises o) {
			if (datetime == o.getDatetime()) {
				return id.compareTo(o.getId());
			} else {
				return datetime.compareTo(o.getDatetime());
			}
		}


		
		
		
	}
