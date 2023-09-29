import java.awt.*;

public class HanoiBlock {
     int blockSize;
     Color color;
    HanoiBlock(int Size,int floors){
        this.blockSize = Size;
        int colorNum = 255/floors;
        color = new Color(colorNum*blockSize,colorNum*blockSize,colorNum*blockSize);
    }
}
