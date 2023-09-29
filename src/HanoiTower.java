import java.awt.*;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Stack;

public class HanoiTower {
    public static Stack<HanoiBlock> leftBar;
    public static Stack<HanoiBlock> centerBar;
    public static Stack<HanoiBlock> rightBar;
    HanoiPanel hanoiPanel;


    HanoiTower(int floor,HanoiPanel hanoiPanel){
        this.hanoiPanel = hanoiPanel;
        leftBar = new Stack<>();
        centerBar = new Stack<>();
        rightBar = new Stack<>();
        for(int i = 0; i < floor; i++){
            leftBar.push(new HanoiBlock(floor-i ,floor));
        }
    }
    public void moveHanoi(int numberOfBlockToMove,String from, String temp, String to){
        if (numberOfBlockToMove == 1)
        {
            HanoiPanel.hanoiMoveEvents.add(new HanoiMoveEvent(from, to));
            return ;
        }
        this.moveHanoi(numberOfBlockToMove - 1,from, to, temp); //블럭 개수 n-1개를 tmp로 움겨라
        this.moveHanoi(1, from, temp, to);                      //위의 작업을 끝네고 한개 남은 블럭을 to로 옴겨라
        this.moveHanoi(numberOfBlockToMove - 1, temp, from, to);//그로 tmp에 있는 나머지 블럭을 to로 움겨라

    }
    public  void move(HanoiMoveEvent event){
        String from = event.getFrom();
        String to = event.getTo();

        if(from.equals("leftBar")){

            if(leftBar.size() != 0){
                HanoiBlock fromBlock = leftBar.pop();
                if (Objects.equals(to, "centerBar")) {
                    centerBar.push(fromBlock);
                } else if (Objects.equals(to, "rightBar")) {
                    rightBar.push(fromBlock);
                }
            }
        } else if (from.equals("centerBar")) {
            if(!centerBar.isEmpty()){
                HanoiBlock fromBlock = centerBar.pop();
                if(Objects.equals(to, "leftBar")){
                    leftBar.push(fromBlock);
                } else if (Objects.equals(to, "rightBar")) {
                    rightBar.push(fromBlock);
                }
            }
        } else if (from.equals("rightBar")) {
            if(!rightBar.isEmpty()){
                HanoiBlock fromBlock = rightBar.pop();
                if(Objects.equals(to, "leftBar")){
                    leftBar.push(fromBlock);
                } else if (Objects.equals(to, "centerBar")) {
                    centerBar.push(fromBlock);
                }
            }
        }

    }
}
