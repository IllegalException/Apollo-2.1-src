//Decomped by XeonLyfe

package com.apollo.api.players.friends;

import java.util.*;

public class Friends
{
    public static List<Friend> friends;
    
    public Friends() {
        Friends.friends = new ArrayList<Friend>();
    }
    
    public static List<Friend> getFriends() {
        return Friends.friends;
    }
    
    public static boolean isFriend(final String name) {
        boolean b = false;
        for (final Friend f : getFriends()) {
            if (f.getName().equalsIgnoreCase(name)) {
                b = true;
            }
        }
        return b;
    }
    
    public Friend getFriendByName(final String name) {
        Friend fr = null;
        for (final Friend f : getFriends()) {
            if (f.getName().equalsIgnoreCase(name)) {
                fr = f;
            }
        }
        return fr;
    }
    
    public void addFriend(final String name) {
        Friends.friends.add(new Friend(name));
    }
    
    public void delFriend(final String name) {
        Friends.friends.remove(this.getFriendByName(name));
    }
}
