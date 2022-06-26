package net.deechael.voicechatbot;

import net.deechael.khl.api.User;
import net.deechael.khl.message.cardmessage.Card;
import net.deechael.khl.message.cardmessage.CardMessage;
import net.deechael.khl.message.cardmessage.Theme;
import net.deechael.khl.message.cardmessage.element.KMarkdownText;
import net.deechael.khl.message.cardmessage.element.PlainText;
import net.deechael.khl.message.cardmessage.module.Header;
import net.deechael.khl.message.cardmessage.module.Section;
import net.deechael.khl.message.kmarkdown.KMarkdownMessage;

public class MessageUtils {

    public static CardMessage success(User user, String message) {
        CardMessage msg = new CardMessage();
        Card card = new Card();
        card.setTheme(Theme.SUCCESS);
        PlainText error = new PlainText();
        error.setContent("成功");
        card.append(new Header().setText(error));
        KMarkdownText content = new KMarkdownText();
        content.setContent(KMarkdownMessage.mentionUser(user).expendSpace(KMarkdownMessage.create(message)));
        card.append(new Section().setText(content));
        msg.append(card);
        return msg;
    }

    public static CardMessage failed(User user, String message) {
        CardMessage msg = new CardMessage();
        Card card = new Card();
        card.setTheme(Theme.DANGER);
        PlainText error = new PlainText();
        error.setContent("错误");
        card.append(new Header().setText(error));
        KMarkdownText content = new KMarkdownText();
        content.setContent(KMarkdownMessage.mentionUser(user).expendSpace(KMarkdownMessage.create(message)));
        card.append(new Section().setText(content));
        msg.append(card);
        return msg;
    }

}
