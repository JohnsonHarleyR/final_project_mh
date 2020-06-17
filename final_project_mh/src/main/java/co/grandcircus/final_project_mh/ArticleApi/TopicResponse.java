package co.grandcircus.final_project_mh.ArticleApi;

import java.util.Arrays;

public class TopicResponse {
	
	private Meta meta;
	private Result[] results;
	
	//@return GET meta
	public Meta getMeta() {
		return meta;
	}
	
	//@param SET meta
	public void setMeta(Meta meta) {
		this.meta = meta;
	}
	
	//@return GET results
	public Result[] getResults() {
		return results;
	}
	
	//@param SET results
	public void setResults(Result[] results) {
		this.results = results;
	}

	@Override
	public String toString() {
		return "TopicResponse [meta=" + meta + ", results=" + Arrays.toString(results) + "]";
	}
	
	

}
