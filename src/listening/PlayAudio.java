package listening;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

public class PlayAudio implements Runnable{
	private String audio;
	public PlayAudio(String audio)
	{
		this.audio = audio;
	}
	public void run()
	{
		try 
		{
			FileInputStream music = new FileInputStream(this.audio);
			AdvancedPlayer player = new AdvancedPlayer(music);
			player.play();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(JavaLayerException e)
		{
			e.printStackTrace();
		}
	}
}
