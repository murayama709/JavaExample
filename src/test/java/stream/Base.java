package stream;

import java.text.ParseException;
import java.util.stream.Stream;

import com.mycompany.javaexample.dto.Friend;
import com.mycompany.javaexample.util.FriendUtil;

public abstract class Base {
    protected Stream<Friend> friends() throws ParseException {
        return FriendUtil.getFriends().stream();
    }

    protected Stream<Friend> fewfriends() throws ParseException {
        return FriendUtil.getFewFriends().stream();
    }
}
