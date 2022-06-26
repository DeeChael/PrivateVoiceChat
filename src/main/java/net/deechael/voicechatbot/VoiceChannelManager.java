package net.deechael.voicechatbot;

import net.deechael.khl.api.GuildUser;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class VoiceChannelManager {

    private final static Map<String, VC> VOICE_CHANNELS = new HashMap<>();

    public static void createNew(GuildUser guildUser) {
        String channelName = guildUser.getNickname().toLowerCase() + "-" + generateRandomString();
    }

    private static String generateRandomString() {
        char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        StringBuilder base = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            base.append(chars[random.nextInt(chars.length)]);
        }
        return base.toString();
    }

    private static class VC {



    }

}
