package pokerResult;

import java.io.IOException;

public class ResultPoker {

    public ResultPoker() throws IOException {
        startFileManager();
    }

    public void startFileManager() throws IOException {
        FileManager myFileManager = new FileManager();
        myFileManager.wonHands();
        myFileManager.heroCalls();
        myFileManager.heroRaise();
        myFileManager.heroBets();
        myFileManager.heroAnte();
        myFileManager.handsResult();
        myFileManager.handStarted();
    }

    public double startCalulating() {
        return 55.55;
    }
}
