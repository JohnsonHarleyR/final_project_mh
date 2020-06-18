package co.grandcircus.final_project_mh.User;

import java.util.ArrayList;
import java.util.List;

public class UserMethods {
	
	
	//Methods for connecting with friends
	
	//Delete a friend
	public static void deleteFriend(User user, User friend, UserDao repo) {
		//Get list of friends
		List<String> friendIds = idStringToList(user.getFriends());
		List<String> theirFriendIds = idStringToList(friend.getFriends());
		System.out.println(friendIds);
		
		//loop through list, remove matching id
		if (!friendIds.isEmpty() && friendIds != null) {
			for (int i = 0; i < friendIds.size(); i++) {
				if (friendIds.get(i).equals(friend.getId() + "")) {
					System.out.println(friendIds.get(i));
					friendIds.remove(i);
				}
			}
		}
		
		//loop through list, remove matching id
		if (!theirFriendIds.isEmpty() && theirFriendIds != null) {
			for (int i = 0; i < theirFriendIds.size(); i++) {
				if (theirFriendIds.get(i).equals(user.getId() + "")) {
					System.out.println(theirFriendIds.get(i));
					theirFriendIds.remove(i);
				}
			}
		}
		
		//Turn list back into a string
		String idString = idListToString(friendIds);
		String theirIdString = idListToString(theirFriendIds);
		
		//set user's request string to idString
		user.setFriends(idString);
		friend.setFriends(theirIdString);
		
		System.out.println("friends " + user.getFriends());
		
		//Save the user
		repo.save(user);
		repo.save(friend);
		
		//duh, here I am making it complicated, 
		//turns out I forgot the link url lol
		
	}
	
	
	//Check if you are friends
	public static boolean checkIfFriends(User user, User friend) {
		
		System.out.println(user.getFriends());
		System.out.println(friend.getId().toString());
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
		
		//test
		System.out.println("\nSending request");
		System.out.println("User: " + user.getUsername());
		System.out.println("Friend: " + friend.getUsername());
		
		//if their requests and friends does not contain user's id, and they don't
		//have too many requests, and user isn't at friend limit, then proceed
		if (!friend.getRequests().contains(user.getId().toString()) &&
				!friend.getFriends().contains(user.getId().toString()) &&
				countRequests(friend) < MAX_REQUESTS &&
				countFriends(user) <  MAX_FRIENDS) {
			
			//add user's id to their request list
			//if it's the first request, don't add a comma
			String requestString = "";
			if (friend.getRequests().contentEquals("")) {
				requestString = user.getId().toString();
			} else {
				requestString = friend.getRequests() + "," + user.getId();
			}
			System.out.println("\nAdding request to string...");
			System.out.println("New request string: " + requestString);
			
			//Set their request list
			System.out.println("\nChanging friend's request string to new string");
			friend.setRequests(requestString);
			
			System.out.println("New possible friend's request string: " + friend.getRequests());
			
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
	public static void acceptRequest(User user, User friend, UserDao repo) {
		
		//Add new user to friend string
		String friendString = "";
		if (user.getFriends().equals("") || user.getFriends() == null) {
			friendString = friend.getId() + "";
		} else {
			friendString = user.getFriends() + "," + friend.getId();
		}
				
		
		//Add new friend to user string
		String userString = "";
		if (friend.getFriends().equals("") || friend.getFriends() == null) {
			userString = user.getId() + "";
		} else {
			userString = friend.getFriends() + "," + user.getId();
		}
		
		//set user's friend string to friendString
		user.setFriends(friendString);
		
		//set friend's user string to userString
		friend.setFriends(userString);
		
		//delete request
		deleteRequest(user, friend, repo);
		
		//Save the user
		repo.save(user);
		repo.save(friend);
		
	}
	
	
	//Deny request
	public static void denyRequest(User user, User friend, UserDao repo) {
		//this is basically the same as deleting a request
		deleteRequest(user, friend, repo);
	}
	
	
	
	//Delete request
	//deletes request from the USER'S request list
	public static void deleteRequest(User user, User friend, UserDao repo) {
		//Get list of requests
		List<String> requestIds = idStringToList(user.getRequests());
		System.out.println("Request list: " + requestIds);
		
		//loop through list, remove matching id
		if (!requestIds.isEmpty() && requestIds != null) {
			for (String id: requestIds) {
				if (id.equals(friend.getId() + "")) {
					System.out.println("removed:" + id);
					System.out.println("removed from:" + user.getId());
					requestIds.remove(id);
					System.out.println("Request list after removal: " + requestIds);
					break;
				}
			}
		}
		System.out.println("Request list after loop: " + requestIds);
		
		//Turn list back into a string
		String idString = "";
		
		if (!requestIds.isEmpty() || requestIds != null) {
			idString = idListToString(requestIds);
		}
		System.out.println("New request string: " + idString);
		
		//set user's request string to idString
		user.setRequests(idString);
		
		System.out.println("New requests in SQL: " + user.getRequests());
		
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
		if (string != "" && string != null) {
			String[] array = string.split(",");
			
			//turn array into list
			for (int i = 0; i < array.length; i++) {
				list.add(array[i]);
			}
		}
		
		return list;
	}
	
	
	//Turn list of ids into string
	public static String idListToString(List<String> list) {
		String string = "";
		
		if (list.isEmpty() || list == null) {
			return string;
		}
		
		for (int i = 0; i < list.size(); i++) {
			if (i == 0) {
				string += list.get(i);
			} else {
				string += "," + list.get(i);
			}
		}
		
		return string;
		
	}
	
	
	
	

}
