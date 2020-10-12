public class BotMain {
    public static void main(String[] args) throws Exception {

        // Now start our bot up.
        Bot bot = new Bot();

        // Enable debugging output.
        bot.setVerbose(true);

        System.out.println(bot.getNick());
        // Connect to the IRC server.
        bot.connect("irc.freenode.net");

        // Join the #pircbot channel.
        bot.joinChannel("#pircbotJavaProject1");

    }
}
