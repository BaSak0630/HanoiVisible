public class HanoiMoveEvent {
    String from;
    String to;
    HanoiMoveEvent(String from,String to){
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}
