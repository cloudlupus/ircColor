import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.NickAlreadyInUseException;


public class botMain {
	public static void main(String[] args) throws FileNotFoundException {
		//config file must have a space between field identifier and value
		File config = new File("./conf/config");
		String name = "";
		String Oauth = "";
		String channel = "";
		@SuppressWarnings("resource")
		Scanner scanConf = new Scanner(config);
		while (scanConf.hasNextLine()){
			String temp = scanConf.nextLine();
			String split[] = temp.split(" ");
			if(temp.toLowerCase().startsWith("name:")){
				name = split[1].trim();
			} else if (temp.toLowerCase().startsWith("oauth:")){
				Oauth = split[1].trim();
			} else if (temp.toLowerCase().startsWith("channel:")){
				channel = split[1].trim();
			}
		}
		System.out.println("name is = " + name + " Oauth is = "+ Oauth +  " channel = " + channel);	
		MyBot testB = new MyBot(name);
		testB.setAutoNickChange(false);
		testB.setVerbose(true);
	
		try {
			testB.connect("irc.twitch.tv",6667,Oauth);
			testB.joinChannel(channel);
			testB.sendRawLine("twitchclient 3");
		} catch (NickAlreadyInUseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IrcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
