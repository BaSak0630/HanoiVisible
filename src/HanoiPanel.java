import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Stack;

public class HanoiPanel extends JPanel {
    public static LinkedList<HanoiMoveEvent> hanoiMoveEvents = new LinkedList<>();
    public static Stack<HanoiMoveEvent> beforeHanoiMoveEvent = new Stack<>();
    JLabel label1;
    JTextField floorTextField;
    JButton beforeBut;
    JButton startBut;
    JButton nextBut;
    JButton resetBut;
    HanoiTower hanoiTower;
    public HanoiPanel() {
        setBackground(new Color(255,255,204));
        this.add(label1= new JLabel("층수를 입력해수세요"));
        floorTextField = new JTextField("                    ");
        floorTextField.setSize(100,30);
        this.add(floorTextField);
        beforeBut = new JButton("Before");
        this.add(beforeBut);
        startBut = new JButton("Start");
        this.add(startBut);
        nextBut = new JButton("Next");
        this.add(nextBut);
        resetBut = new JButton("resetBut");
        this.add(resetBut);

        beforeBut.addActionListener(e -> {
            if(hanoiTower == null){
                JOptionPane.showMessageDialog(null, "층수를 입력해주세요", "층수 확인!",
                        JOptionPane.PLAIN_MESSAGE);
            }else {
                if(!beforeHanoiMoveEvent.isEmpty()){
                    HanoiMoveEvent eventTmp = beforeHanoiMoveEvent.pop();

                    //순서를 바꿔줌
                    String from = eventTmp.getTo();
                    String to = eventTmp.getFrom();
                    HanoiMoveEvent beforeTmp = new HanoiMoveEvent(from, to);

                    hanoiTower.move(beforeTmp);
                    hanoiMoveEvents.addFirst(eventTmp);
                    repaint();
                    try {
                        Thread.sleep(10);
                    }catch(Exception ignored){

                    }

                }else {
                    JOptionPane.showMessageDialog(null, "next", "next",
                            JOptionPane.PLAIN_MESSAGE);
                }
            }
            repaint();
        });

        nextBut.addActionListener(e -> {
            if(hanoiTower == null){
                JOptionPane.showMessageDialog(null, "층수를 입력해주세요", "층수 확인!",
                        JOptionPane.PLAIN_MESSAGE);
            }else {
                if(!hanoiMoveEvents.isEmpty()){
                    HanoiMoveEvent eventTmp = hanoiMoveEvents.pollFirst();
                    hanoiTower.move(eventTmp);
                    beforeHanoiMoveEvent.push(eventTmp);
                    repaint();
                    try {
                        Thread.sleep(100);
                    }catch(Exception ignored){

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "before", "before",
                            JOptionPane.PLAIN_MESSAGE);
                }
            }
            repaint();
        });

        startBut.addActionListener(e -> {
            hanoiTower = null;
            hanoiMoveEvents.clear();
            beforeHanoiMoveEvent.clear();
            repaint();
            String text = floorTextField.getText().trim();
            if(text.isEmpty()){
                JOptionPane.showMessageDialog(null, "층수를 입력해주세요", "층수 확인!",
                        JOptionPane.PLAIN_MESSAGE);
            }else {
                try {
                    int floor = Integer.parseInt(floorTextField.getText().trim());
                    if(0 < floor && floor< 13){

                        hanoiTower = new HanoiTower(floor,this);
                        System.out.println( hanoiTower.leftBar.size());
                        hanoiTower.moveHanoi(floor,"leftBar","centerBar","rightBar");
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "1 ~ 12의 숫자를 입력해주세요", "층수 확인!",
                                JOptionPane.PLAIN_MESSAGE);
                    }
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, "층수를 입력해주세요", "층수 확인!",
                            JOptionPane.PLAIN_MESSAGE);
                }
            }
            repaint();
        });
        resetBut.addActionListener(e -> {
            hanoiTower = null;
            hanoiMoveEvents.clear();
            beforeHanoiMoveEvent.clear();
            repaint();
        });
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawPoll(g);
        if(hanoiTower != null){
            drawBlock(g);
        }

    }
    public void drawPoll(Graphics g) {
        int pollWidth = getWidth()/130;
        int pollHeight =getHeight()-(getHeight()/2);
        int pollX1 = (getWidth()/10)*2 - pollWidth/2;
        int pollX2 = (getWidth()/10)*5 - pollWidth/2;
        int pollX3 = (getWidth()/10)*8 - pollWidth/2;
        int pollY = getHeight() -pollHeight;

        g.fillRect(pollX1,pollY,pollWidth,pollHeight);
        g.fillRect(pollX2,pollY,pollWidth,pollHeight);
        g.fillRect(pollX3,pollY,pollWidth,pollHeight);
    }

    public void drawBlock(Graphics g) {
        int wight = getWidth();
        int height = getHeight();
        int pollX1 = (wight/10)*2;
        int pollX2 = (wight/10)*5;
        int pollX3 = (wight/10)*8;
        int blockDefaultSize = wight/60;
        int numOfLeftBlock;
        int blockwight;

        //left출력
        //stack 딥카피 해서 사용
        numOfLeftBlock = hanoiTower.leftBar.size();
        Stack<HanoiBlock> tmpStack = new Stack<>();
        for (int i = 0; i < numOfLeftBlock; i++) {
            tmpStack.push(hanoiTower.leftBar.elementAt(i));
        }
        for (int i = 0 ; i < numOfLeftBlock; i++) {
            HanoiBlock tmp = tmpStack.pop();
            g.setColor(tmp.color);
            blockwight = blockDefaultSize*tmp.blockSize;
            g.fillRect(pollX1 -(blockwight/2),height -(blockDefaultSize*(numOfLeftBlock-i)),blockwight,blockDefaultSize);
        }
        System.out.println("left갯수"+ numOfLeftBlock);

        //center출력
        numOfLeftBlock = hanoiTower.centerBar.size();
        tmpStack.clear();
        for (int i = 0; i < numOfLeftBlock; i++) {
            tmpStack.push(hanoiTower.centerBar.elementAt(i));
        }
        for (int i = 0 ; i < numOfLeftBlock; i++) {
            HanoiBlock tmp = tmpStack.pop();
            g.setColor(tmp.color);
            blockwight = blockDefaultSize*tmp.blockSize;
            g.fillRect(pollX2 -(blockwight/2),height -(blockDefaultSize*(numOfLeftBlock-i)),blockwight,blockDefaultSize);
        }
        System.out.println("center갯수"+ numOfLeftBlock);

        //right출력
        numOfLeftBlock = hanoiTower.rightBar.size();
        tmpStack.clear();
        for (int i = 0; i < numOfLeftBlock; i++) {
            tmpStack.push(hanoiTower.rightBar.elementAt(i));
        }
        for (int i = 0 ; i < numOfLeftBlock; i++) {
            HanoiBlock tmp = tmpStack.pop();
            g.setColor(tmp.color);
            blockwight = blockDefaultSize*tmp.blockSize;
            g.fillRect(pollX3 -(blockwight/2),height -(blockDefaultSize*(numOfLeftBlock-i)),blockwight,blockDefaultSize);
        }
        System.out.println("right갯수"+ numOfLeftBlock);
        System.out.println("");
    }
}
