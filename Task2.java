import java.util.*;

public class Task2{
    public static void main(String [] args){
        Friends friend=new Friends();
        friend.addFriend("alice","bob");
        friend.addFriend("alice","jacob");
        friend.addFriend("jacob","ram");
        friend.addFriend("bob","janice");
        friend.addFriend("janice","riya");
        friend.friendsofPerson("jacob");
        friend.commonfriendof("bob","jacob");
        friend.nthConnection("alice","ram");
        friend.show();
    }
}


