package co.grandcircus.final_project_mh.User;

import java.util.ArrayList;
import java.util.List;

public class UserMethods {
	
	
	//Methods for connecting with friends
	
	//Delete a friend
	public static void deleteFriend(User user, String friendId, UserDao repo) {
		//Get list of requests
		List<String> friendIds = idStringToList(user.getFriends());
		
		//loop through list, remove matching id
		for (String id: friendIds) {
			if (id.equals(friendId)) {
				friendIds.remove(id);
			}
		}
		
		//Turn list back into a string
		String idString = idListToString(friendIds);
		
		//set user's request string to idString
		user.setRequests(idString);
		
		//Save the user
		repo.save(user);
		
	}
	
	
	//Check if you are friends
	public static boolean checkIfFriends(User user, User friend) {
		
		if (user.getFriends().contains(friend.getId().toString())) {
			return true;
		} else {
			return false;
		}
		
	}
		
		
	
	//Count friends
	public static int countFriends(User user) {
		
		//turn friend ids into array
		String[] friendIds = user.getFriends().split(",");
		
		//return number in array
		return friendIds.length;
	}
	
	
	
	
	//Send friend request
	public static void sendRequest(User user, User friend, UserDao repo) {
		final int MAX_REQUESTS = 250; //determined by varchar length in SQL table
		final int MAX_FRIENDS = 500; //determined by varchar length in SQL table
		
		//if their requests and friends does not contain user's id, and they don't
		//have too many requests, and user isn't at friend limit, then proceed
		if (!friend.getRequests().contains(user.getId().toString()) &&
				!friend.getFriends().contains(user.getId().toString()) &&
				countRequests(friend) < MAX_REQUESTS &&
				countFriends(user) <  MAX_FRIENDS) {
			
			//add user's id to their request list
			friend.setRequests(friend.getRequests() + "," + user.getId());
			//update friend
			repo.save(friend);
		}
	}
	
	
	//Count requests
	public static int countRequests(User user) {
		
		//turn request ids into array
		String[] requestIds = user.getRequests().split(",");
		
		//return number in array
		return requestIds.length;
	}
	
	
	
	//Accept request
	public static void acceptRequest(User user, String requestId, UserDao repo) {
		
		//Add new user to friend string
		String friendString = user.getFriends() + "," + requestId;
		
		//set user's request string to idString
		user.setFriends(friendString);
		
		//Save the user
		repo.save(user);
		
	}
	
	
	//Deny request
	public static void denyRequest(User user, String requestId, UserDao repo) {
		//this is basically the same as deleting a request
		deleteRequest(user, requestId, repo);
	}
	
	
	
	//Delete request
	public static void deleteRequest(User user, String requestId, UserDao repo) {
		//Get list of requests
		List<String> requestIds = idStringToList(user.getRequests());
		
		//loop through list, remove matching id
		for (String id: requestIds) {
			if (id.equals(requestId)) {
				requestIds.remove(id);
			}
		}
		
		//Turn list back into a string
		String idString = idListToString(requestIds);
		
		//set user's request string to idString
		user.setRequests(idString);
		
		//Save the user
		repo.save(user);
	}
	
	
	
	//Check if already requested
	public static boolean checkIfRequested(User user, User request) {
		
		if (user.getRequests().contains(request.getId().toString())) {
			return true;
		} else {
			return false;
		}
		
	}
	
	
	//Block user - does not do anything yet
	public static void blockUser() {
		
	}
	
	
	//Turn string of ids into list
	public static List<String> idStringToList(String string) {
		List<String> list = new ArrayList<>();
		String[] array = string.split(",");
		
		//turn array into list
		for (int i = 0; i < array.length; i++) {
			list.add(array[i]);
		}
		
		return list;
	}
	
	
	//Turn list of ids into string
	public static String idListToString(List<String> list) {
		String string = "";
		
		for (int i = 0; i < list.size(); i++) {
			if (i != list.size() - 1) {
				string += list.get(i);
			} else {
				string += list.get(i) + ",";
			}
		}
		
		return string;
		
	}
	
	
	
	

}
