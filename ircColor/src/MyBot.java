import org.jibble.pircbot.*;

public class MyBot extends PircBot {
	private drawBox myBox = new drawBox();
	public MyBot(String name){
		this.setName(name);
		
	}
	
	/*protected void handleLine(String line){
		System.out.println("::IMPORTANT:: we got a server message? should be 4 of these and the message is = "+ line);
		
	}*/
	
	protected void onMessage(String channel, String sender, String login, String hostname, String message){
		if (message.startsWith("!")){
			//System.out.println("channel is = " + channel + " the sender is = "+ sender + " the hostname is = " + hostname + " the message is = " +message);
			String[] split = message.split(" ");
			if(split[0].substring(1).equals("ping")){
				sendMessage(channel, "pong");
			} else if( split[0].substring(1).equals("color") || split[0].substring(1).equals("colour")){
				if(split[1].startsWith("#")|| split[1].matches("\\d")){
					myBox.addColor(split[1]);
				} else {
					sendMessage(channel, "Invalid formatting please use !color RRRGGGBBB or !color #RRGGBB example to draw a perfectly red square type either !color 255000000 or !color #ff0000");
				}
			}
		}
	}
}