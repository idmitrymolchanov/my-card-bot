package ru.vkbot;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.messages.*;
import com.vk.api.sdk.queries.messages.MessagesGetLongPollHistoryQuery;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CardBot {
    public static void main(String[] args) throws ClientException, ApiException, InterruptedException {
        TransportClient transportClient = new HttpTransportClient();
        VkApiClient vk = new VkApiClient(transportClient);
        Random random = new Random();
        Keyboard keyboard = new Keyboard();
        Keyboard keyboard2 = new Keyboard();

        List<List<KeyboardButton>> allKey = new ArrayList<>();
        List<KeyboardButton> line1 = new ArrayList<>();
        List<KeyboardButton> line2 = new ArrayList<>();
        line1.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Лента").setType(KeyboardButtonActionType.TEXT)).setColor(KeyboardButtonColor.POSITIVE));
        line1.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Пятерочка").setType(KeyboardButtonActionType.TEXT)).setColor(KeyboardButtonColor.POSITIVE));
        line2.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Билла").setType(KeyboardButtonActionType.TEXT)).setColor(KeyboardButtonColor.POSITIVE));
        line2.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Перекресток").setType(KeyboardButtonActionType.TEXT)).setColor(KeyboardButtonColor.POSITIVE));

        allKey.add(line1);
        allKey.add(line2);
        keyboard.setButtons(allKey);

        List<List<KeyboardButton>> startList = new ArrayList<>();
        List<KeyboardButton> startLine1 = new ArrayList<>();
        startLine1.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Карты").setType(KeyboardButtonActionType.TEXT)).setColor(KeyboardButtonColor.POSITIVE));
        startList.add(startLine1);
        keyboard2.setButtons(startList);

        GroupActor actor = new GroupActor(, "");
        Integer ts = vk.messages().getLongPollServer(actor).execute().getTs();
        while (true) {
            MessagesGetLongPollHistoryQuery historyQuery = vk.messages().getLongPollHistory(actor).ts(ts);
            List<Message> messages = historyQuery.execute().getMessages().getItems();
            if(!messages.isEmpty()) {
                messages.forEach(message -> {
                    System.out.println(message.toString());
                    try {
                        if(message.getText().equals("Лента")) {
                            vk.messages().send(actor).message("Fuck you, okay?").userId(message.getFromId()).randomId(random.nextInt(10000)).execute();
                        }
                        else if(message.getText().equals("Пятерочка")) {
                            vk.photos().getMessagesUploadServer(actor);
                            File file = new File("");
                            vk.upload().photoMessage("", file);
                            vk.messages().send(actor).message().attachment()

                        }
                        else if(message.getText().equals("Билла")) {
                            vk.messages().send(actor).message("your fucking Билла").userId(message.getFromId()).randomId(random.nextInt(10000)).execute();
                        }
                        else if(message.getText().equals("Перекресток")) {
                            vk.messages().send(actor).message("your fucking Перекресток").userId(message.getFromId()).randomId(random.nextInt(10000)).execute();
                        }
                        else if(message.getText().equals("Начать")) {
                            vk.messages().send(actor).message("start_m").userId(message.getFromId()).randomId(random.nextInt(10000)).keyboard(keyboard2).execute();
                        }
                        else if(message.getText().equals("Карты")) {
                            vk.messages().send(actor).message("your fucking cards").userId(message.getFromId()).randomId(random.nextInt(10000)).keyboard(keyboard).execute();
                        }
                        else {
                            vk.messages().send(actor).message("Whhat? Stupid shit").userId(message.getFromId()).randomId(random.nextInt(10000)).execute();
                        }
                    } catch (ApiException | ClientException e) { e.printStackTrace(); }
                });
            }
            ts = vk.messages().getLongPollServer(actor).execute().getTs();
            Thread.sleep(500);
        }
    }
}
