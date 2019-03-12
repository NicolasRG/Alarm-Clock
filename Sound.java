import java.io.*;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class Sound {
    ClassLoader classLoader = Sound.class.getClassLoader();
    String sound = "src/sounds/e.wav";
    InputStream is = this.getClass().getClassLoader().getResourceAsStream(sound);
    final JFXPanel fxPanel = new JFXPanel();
    private Media clip;
    private MediaPlayer player;



    public Sound(){
        //loads in the sound information
        try{
            //need to figure out how to get this to a executable form
            clip = new Media(new File(sound).toURI().toString());
            player  = new MediaPlayer(clip);
            player.setOnEndOfMedia(new Runnable(){
                public void run(){
                    player.stop();
                }
            });
        }
        catch(Exception e)
        {
            System.out.println("Error in audio playback " + e);
        }
    }

    public void play(){
        player.play();

    }
    public void stop()
    {
            System.out.println(player.statusProperty());
            player.stop();

    }

}



