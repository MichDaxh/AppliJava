package ViewPackage;

public class ThreadMessage extends Thread {
    private String[] messages = {"Bomb gonna explode !", "Throwing a grenade !", "Easy peazy lemon squeazy...", "Monster kill!!!"};
    private ThreadPanel linkedPanel;

    public ThreadMessage(ThreadPanel linkedPanel) {
        this.linkedPanel = linkedPanel;
    }

    public void run(){
        while(true){
            try{
                int i = 0;
                while (i < messages.length){
                    Thread.sleep(3000);
                    linkedPanel.getText().setText(messages[i]);
                    linkedPanel.repaint();
                    i++;
                    if(i == messages.length) i = 0;
                }
            }
            catch (Exception e){

            }
        }
    }
}
