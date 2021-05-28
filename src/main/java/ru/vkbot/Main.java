package ru.vkbot;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.messages.*;
import com.vk.api.sdk.objects.photos.responses.GetMessagesUploadServerResponse;
import com.vk.api.sdk.objects.photos.responses.MessageUploadResponse;
import com.vk.api.sdk.objects.photos.responses.PhotoUploadResponse;
import com.vk.api.sdk.objects.photos.responses.SaveMessagesPhotoResponse;
import com.vk.api.sdk.queries.messages.MessagesGetLongPollHistoryQuery;
import com.vk.api.sdk.queries.photos.PhotosGetMessagesUploadServerQuery;
import com.vk.api.sdk.queries.photos.PhotosSaveMessagesPhotoQuery;
import com.vk.api.sdk.queries.upload.UploadPhotoMessageQuery;
import com.vk.api.sdk.queries.upload.UploadPhotoQuery;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws ClientException, ApiException, InterruptedException {
        TransportClient transportClient = new HttpTransportClient();
        VkApiClient vk = new VkApiClient(transportClient);
        Random random = new Random();
        Keyboard keyboard = new Keyboard();
        Keyboard keyboard2 = new Keyboard();

        List<List<KeyboardButton>> allKey = new ArrayList<>();
        List<KeyboardButton> line1 = new ArrayList<>();
        List<KeyboardButton> line2 = new ArrayList<>();
        line1.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Лента").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.POSITIVE));
        line1.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Пятерочка").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.POSITIVE));
        line2.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Билла").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.POSITIVE));
        line2.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Перекресток").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.POSITIVE));

        allKey.add(line1);
        allKey.add(line2);
        keyboard.setButtons(allKey);

        List<List<KeyboardButton>> startList = new ArrayList<>();
        List<KeyboardButton> startLine1 = new ArrayList<>();
        startLine1.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Карты").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.POSITIVE));
        startList.add(startLine1);
        keyboard2.setButtons(startList);

        GroupActor actor = new GroupActor(, "");
        Integer ts = vk.messages().getLongPollServer(actor).execute().getTs();
        while (true) {
            MessagesGetLongPollHistoryQuery historyQuery = vk.messages().getLongPollHistory(actor).ts(ts);
            List<Message> messages = historyQuery.execute().getMessages().getItems();
            if(!messages.isEmpty()) {
                messages.forEach(message -> {
                    //System.out.println(message.toString());
                    try {
                        if(message.getText().equals("Лента")) {
                            PhotosGetMessagesUploadServerQuery server = vk.photos().getMessagesUploadServer(actor);
                            GetMessagesUploadServerResponse serverResponse = server.execute();
                            File file = new File("C:\\Users\\Dmitry\\IdeaProjects\\bot\\src\\main\\resources\\img\\first.jpg");

                            UploadPhotoMessageQuery photo = vk.upload().photoMessage(serverResponse.getUploadUrl().toString(), file);
                            System.out.println(serverResponse.getUploadUrl() + " +=+=");
                            MessageUploadResponse photoUploadResponse = photo.execute();

                            PhotosSaveMessagesPhotoQuery a = vk.photos().saveMessagesPhoto(actor, photoUploadResponse.getPhoto()).server(photoUploadResponse.getServer()).hash(photoUploadResponse.getHash());
                            System.out.println(photoUploadResponse.getPhoto() + " +=+=2");
                            //System.out.println(a.executeAsString());
                            List<SaveMessagesPhotoResponse> list = a.execute();
                            String p = "photo" + list.get(0).getOwnerId()+"_"+list.get(0).getId();
                            System.out.println(p);

                            vk.messages().send(actor).message("Fuck you, okay?").attachment(p).userId(message.getFromId()).randomId(random.nextInt(10000)).execute();
                        }
                        else if(message.getText().equals("Пятерочка")) {
                            vk.messages().send(actor).message("your fucking Пятерочка").userId(message.getFromId()).randomId(random.nextInt(10000)).execute();
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