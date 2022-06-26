package net.deechael.voicechatbot;

import net.deechael.khl.api.Channel;
import net.deechael.khl.api.GuildUser;
import net.deechael.khl.api.Role;
import net.deechael.khl.api.User;
import net.deechael.khl.bot.KaiheilaBot;
import net.deechael.khl.bot.KaiheilaBotBuilder;
import net.deechael.khl.command.Command;
import net.deechael.khl.command.CommandSender;
import net.deechael.khl.command.argument.UserArgumentType;
import net.deechael.khl.configuration.file.FileConfiguration;
import net.deechael.khl.configuration.file.YamlConfiguration;
import net.deechael.khl.message.TextMessage;
import net.deechael.khl.message.cardmessage.Card;
import net.deechael.khl.message.cardmessage.CardMessage;
import net.deechael.khl.message.cardmessage.Theme;
import net.deechael.khl.message.cardmessage.element.KMarkdownText;
import net.deechael.khl.message.cardmessage.element.PlainText;
import net.deechael.khl.message.cardmessage.module.Header;
import net.deechael.khl.message.cardmessage.module.Section;
import net.deechael.khl.message.kmarkdown.KMarkdownMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Objects;

public class PrivateVoiceChatBot {

    public static void main(String[] args) {
        Logger Log = LoggerFactory.getLogger(PrivateVoiceChatBot.class);
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(new File("config.yml"));
        String apiToken = configuration.getString("token");

        Log.info("Starting...");
        KaiheilaBot bot = (KaiheilaBot) KaiheilaBotBuilder.builder()
                .createDefault(apiToken) // 使用默认配置构建 KaiheilaBot 实例
                .build(); // 创建实例
        bot.getCommandManager()
                .register(
                        Command.create("voice-chat").addAlias("vc").addAlias("voicechat").addPrefix(".").addPrefix("。")
                                .literal()
                                .executes(context -> {
                                    CommandSender sender = context.getSource();
                                    sender.getChannel().sendTempMessage(new TextMessage("输入 \".voice-chat help\" 获取帮助"), sender.getUser());
                                    return 1;
                                })
                                .then(Command.create("help").literal().executes(context -> {
                                    CommandSender sender = context.getSource();
                                    sender.getChannel().sendTempMessage(KMarkdownMessage.create("---\n" +
                                            ".voice-chat help - 获取帮助\n" +
                                            ".voice-chat create - 创建频道\n" +
                                            ".voice-chat invite <用户> - 邀请别人加入\n" +
                                            ".voice-chat turnon - 允许该频道所在分组创建语音频道，且仅在该频道输入指令有效 [管理员指令]\n" +
                                            ".voice-chat turnoff - 禁止该频道所在分组创建语音频道 [管理员指令]\n" +
                                            "---"), sender.getUser());
                                    return 1;
                                }))
                                .then(Command.create("create").literal().executes(context -> {
                                    CommandSender sender = context.getSource();
                                    Channel channel = sender.getChannel();
                                    User user = sender.getUser();
                                    if (channel.getGuild().getJoinedChannel(user).size() > 0) {
                                        channel.sendTempMessage(MessageUtils.failed(user, "你已经加入了一个语音频道"), user);
                                    } else {

                                    }
                                    return 1;
                                }))
                                .then(Command.create("invite").literal().then(Command.create("user").argument(UserArgumentType.user(bot)).executes(context -> {
                                    CommandSender sender = context.getSource();
                                    Channel channel = sender.getChannel();
                                    User user = sender.getUser();
                                    User invitee = UserArgumentType.getUser(context, "user");
                                    return 1;
                                })))
                                .then(Command.create("turnon").literal().requires(sender -> {
                                    Channel channel = sender.getChannel();
                                    User user = sender.getUser();
                                    GuildUser guildUser = bot.getCacheManager().getGuildUsersCache().get(channel.getGuild().getId()).get(user.getId());
                                    boolean isOp = false;
                                    if (Objects.equals(guildUser.getGuild().getCreator().getId(), guildUser.getId())) {
                                        isOp = true;
                                    } else {
                                        for (Role role : guildUser.getRoles()) {
                                            if ((role.getPermissionsRaw() & 0x01) == 0x01) {
                                                isOp = true;
                                                break;
                                            }
                                        }
                                    }
                                    return isOp;
                                }).executes(context -> {
                                    CommandSender sender = context.getSource();
                                    Channel channel = sender.getChannel();
                                    User user = sender.getUser();
                                    sender.getChannel().sendTempMessage(KMarkdownMessage.create("---\n" +
                                            ".voice-chat help - 获取帮助\n" +
                                            ".voice-chat create - 创建频道\n" +
                                            ".voice-chat invite <用户> - 邀请别人加入\n" +
                                            "---"), sender.getUser());
                                    return 1;
                                }))
                                .then(Command.create("turnoff").literal().requires(sender -> {
                                    Channel channel = sender.getChannel();
                                    User user = sender.getUser();
                                    GuildUser guildUser = bot.getCacheManager().getGuildUsersCache().get(channel.getGuild().getId()).get(user.getId());
                                    boolean isOp = false;
                                    if (Objects.equals(guildUser.getGuild().getCreator().getId(), guildUser.getId())) {
                                        isOp = true;
                                    } else {
                                        for (Role role : guildUser.getRoles()) {
                                            if ((role.getPermissionsRaw() & 0x01) == 0x01) {
                                                isOp = true;
                                                break;
                                            }
                                        }
                                    }
                                    return isOp;
                                }).executes(context -> {
                                    CommandSender sender = context.getSource();
                                    Channel channel = sender.getChannel();
                                    User user = sender.getUser();
                                    sender.getChannel().sendTempMessage(KMarkdownMessage.create("---\n" +
                                            ".voice-chat help - 获取帮助\n" +
                                            ".voice-chat create - 创建频道\n" +
                                            ".voice-chat invite <用户> - 邀请别人加入\n" +
                                            "---"), sender.getUser());
                                    return 1;
                                }))
                );
    }

}
