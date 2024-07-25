import java.util.*;
public class Friends {
    // Creating a Hashmap which maps a person(key) to all its friends(value).
    // the value in map is of type set so that we have unique friends and no duplicates.
    private HashMap<String,Set<String>>AllFriend;

    void show(){
        System.out.println(AllFriend);
    }

    // constructor for initializing the structure
    public  Friends (){
        AllFriend=new HashMap<>();
    }

    // add friend method takes 2 input -> whose friend is it , and the name of the particular person
    // imp:if we are adding s1->s2 we also have to add s2->s1 because they are friends of each other.
    public  void addFriend(String friendof,String name){
        if(!AllFriend.containsKey(friendof)){
                AllFriend.put(friendof, new HashSet<>());
        }
        AllFriend.get(friendof).add(name);

        if(!AllFriend.containsKey(name)){
            AllFriend.put(name, new HashSet<>());
        }
        AllFriend.get(name).add(friendof);

        System.out.println("Friend added");
        return;
    }

    // friendofPerson method takes a string and return all friends of that person
    // using stream api for iterating over all the friends of a given person
    public void friendsofPerson(String name){
        if(AllFriend.containsKey(name)){
            System.out.print("Friends of "+name + " are: ");
            AllFriend.get(name).stream().forEach(el-> System.out.print(el+", "));
        }
        else {
            System.out.println("-1, No friends :(");
        }
        System.out.println(" ");
        return;
    }

    // this method takes two names and return common friends of both.
    public void commonfriendof(String s1,String s2){
        HashSet<String>commonFriends=new HashSet<>();
        for(String s:AllFriend.get(s1)){
            if(AllFriend.get(s2).contains(s)){
                commonFriends.add(s);
            }
        }
        if(!commonFriends.isEmpty())
        for(String st:commonFriends){
            System.out.println(st);
        }
        else
            System.out.println("No common friend");
        return;
    }

    // bfs code to find nth connection.
    private int bfs(String start,String target,HashMap<String,Integer>vis,int dist){
      Queue<String>q=new LinkedList<>();
      q.add(start);
      vis.putIfAbsent(start,0);
      while(!q.isEmpty()){
          String st=q.poll();
          int currDist=vis.get(st);

          for(String neighbour: AllFriend.getOrDefault(st,Collections.emptySet())){
              if(!vis.containsKey(neighbour)){
                  vis.put(neighbour,currDist+1);
                  q.add(neighbour);
                  if(neighbour==target){
                      return vis.get(neighbour);
                  }
              }
          }
      }
        return dist;
    }

    // for finding nth connection we just have to perform a dfs traversal from the start to the target node and count.
    public void nthConnection(String s1,String s2){
        if(s1==s2){
            System.out.println("0");
            return;
        }
        HashMap<String, Integer>vis=new HashMap<>();
        int n=bfs(s1,s2,vis,0);
        System.out.println(n);
    }
}
