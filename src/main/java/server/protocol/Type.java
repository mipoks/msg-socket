package server.protocol;

public class Type {
    //For every type designate its max size in next line in bytes
    public static final int ROOM_CREATE = 1;
    public static final int ROOM_CREATE_MAX_SIZE = 1000;
    public static final int ROOM_CREATE_ANSWER = -1;
    public static final int ROOM_CREATE_ANSWER_MAX_SIZE = 1000;

    public static final int ROOM_CONNECT = 2;
    public static final int ROOM_CONNECT_MAX_SIZE = 1000;
    public static final int ROOM_CONNECT_ANSWER = -2;
    public static final int ROOM_CONNECT_ANSWER_MAX_SIZE = 1000;

    public static final int TEXT = 3;
    public static final int TEXT_MAX_SIZE = 10000;
    public static final int TEXT_ANSWER = -3;
    public static final int TEXT_ANSWER_MAX_SIZE = 1000;

    public static final int STATE = 4;
    public static final int STATE_MAX_SIZE = 10000;
    public static final int STATE_ANSWER = -4;
    public static final int STATE_ANSWER_MAX_SIZE = 1000;

    public static final int NAME_CHANGE = 5;
    public static final int NAME_CHANGE_MAX_SIZE = 60;
    public static final int NAME_CHANGE_ANSWER = -5;
    public static final int NAME_CHANGE_ANSWER_MAX_SIZE = 1000;

    public static final int ROOM_OPEN = 6;
    public static final int ROOM_OPEN_MAX_SIZE = 60;
    public static final int ROOM_OPEN_ANSWER = -6;
    public static final int ROOM_OPEN_ANSWER_MAX_SIZE = 1000;

    public static final int ROOM_CLOSE = 7;
    public static final int ROOM_CLOSE_MAX_SIZE = 60;
    public static final int ROOM_CLOSE_ANSWER = -7;
    public static final int ROOM_CLOSE_ANSWER_MAX_SIZE = 1000;

   //ToDo получить победителя,
}
