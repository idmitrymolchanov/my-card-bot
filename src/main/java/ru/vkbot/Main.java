package ru.vkbot;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.UserAuthResponse;
import com.vk.api.sdk.objects.messages.*;
import com.vk.api.sdk.objects.photos.responses.*;
import com.vk.api.sdk.queries.messages.MessagesGetLongPollHistoryQuery;
import com.vk.api.sdk.queries.photos.PhotosGetMessagesUploadServerQuery;
import com.vk.api.sdk.queries.photos.PhotosGetQuery;
import com.vk.api.sdk.queries.photos.PhotosSaveMessagesPhotoQuery;
import com.vk.api.sdk.queries.upload.UploadPhotoMessageQuery;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    private static Integer APP_ID;
    private static String CLIENT_SECRET;
    private static String REDIRECT_URI;
    private static String code;

    public static void main(String[] args) throws ClientException, ApiException, InterruptedException {
        TransportClient transportClient = new HttpTransportClient();
        VkApiClient vk = new VkApiClient(transportClient);

        Random random = new Random();
        Keyboard keyboard = new Keyboard();
        Keyboard keyboard2 = new Keyboard();

        List<List<KeyboardButton>> allKey = new ArrayList<>();
        List<KeyboardButton> line1 = new ArrayList<>();
        List<KeyboardButton> line2 = new ArrayList<>();
        List<KeyboardButton> line3 = new ArrayList<>();
        line1.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Лента").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.POSITIVE));
        line1.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Пятерочка").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.POSITIVE));
        line1.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Окей").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.POSITIVE));

        line2.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Билла").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.POSITIVE));
        line2.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Перекресток").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.POSITIVE));
        line2.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Мастер").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.POSITIVE));

        line3.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Кантата").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.POSITIVE));
        line3.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Чача").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.POSITIVE));
        line3.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Вася").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.POSITIVE));

        allKey.add(line1);
        allKey.add(line2);
        allKey.add(line3);
        keyboard.setButtons(allKey);

        List<List<KeyboardButton>> startList = new ArrayList<>();
        List<KeyboardButton> startLine1 = new ArrayList<>();
        startLine1.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Карты").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.POSITIVE));
        startList.add(startLine1);
        keyboard2.setButtons(startList);

        String billa = "photo-*_457239033";
        String five_ka = "photo-*_457239034";
        String lenta = "photo-*_457239035";
        String perec = "photo-*_457239036";
        String wwhhat = "photo-*_457239026";

        String okay = "photo-*_457239039";
        String kantata = "photo-*_457239040";
        String chacha = "photo-*_457239041";
        String vasya = "photo-*_457239042";
        String stop = "photo-*_457239043";
        String master = "photo-*_457239044";

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
//                            String a = "C:\\Users\\Dmitry\\IdeaProjects\\bot\\src\\main\\resources\\img\\okay.jpg";
//                            String b = "C:\\Users\\Dmitry\\IdeaProjects\\bot\\src\\main\\resources\\img\\kantata.jpg";
//                            String c = "C:\\Users\\Dmitry\\IdeaProjects\\bot\\src\\main\\resources\\img\\chacha.jpg";
//                            String d = "C:\\Users\\Dmitry\\IdeaProjects\\bot\\src\\main\\resources\\img\\vasya.jpg";
//                            String e = "C:\\Users\\Dmitry\\IdeaProjects\\bot\\src\\main\\resources\\img\\stop.png";
//                            String g = "C:\\Users\\Dmitry\\IdeaProjects\\bot\\src\\main\\resources\\img\\master.jpg";
//
//                            uploadPhotoToBot(vk, actor, a);
//                            uploadPhotoToBot(vk, actor, b);
//                            uploadPhotoToBot(vk, actor, c);
//                            uploadPhotoToBot(vk, actor, d);
//                            uploadPhotoToBot(vk, actor, e);
//                            uploadPhotoToBot(vk, actor, g);

                            vk.messages()
                                    .send(actor)
                                    .message("Fuck you, okay?")
                                    .attachment(lenta)
                                    .userId(message.getFromId())
                                    .randomId(random.nextInt(10000))
                                    .execute();
                        }
                        else if(message.getText().equals("Пятерочка")) {
                            vk.messages()
                                    .send(actor)
                                    .message("your fucking Пятерочка")
                                    .attachment(five_ka)
                                    .userId(message.getFromId())
                                    .randomId(random.nextInt(10000))
                                    .execute();
                        }


                        else if(message.getText().equals("Окей")) {
                            vk.messages()
                                    .send(actor)
                                    .message("your fucking Okay, okay?")
                                    .attachment(okay)
                                    .userId(message.getFromId())
                                    .randomId(random.nextInt(10000))
                                    .execute();
                        }else if(message.getText().equals("Кантата")) {
                            vk.messages()
                                    .send(actor)
                                    .message("your fucking Kantata")
                                    .attachment(kantata)
                                    .userId(message.getFromId())
                                    .randomId(random.nextInt(10000))
                                    .execute();
                        }else if(message.getText().equals("Мастер")) {
                            vk.messages()
                                    .send(actor)
                                    .message("your fucking master, fucking slave")
                                    .attachment(master)
                                    .userId(message.getFromId())
                                    .randomId(random.nextInt(10000))
                                    .execute();
                        }else if(message.getText().equals("Чача")) {
                            vk.messages()
                                    .send(actor)
                                    .message("Я враг системы, а ты козел")
                                    .attachment(chacha)
                                    .userId(message.getFromId())
                                    .randomId(random.nextInt(10000))
                                    .execute();
                        }else if(message.getText().equals("Вася")) {
                            vk.messages()
                                    .send(actor)
                                    .message("Отличный парень!")
                                    .attachment(vasya)
                                    .userId(message.getFromId())
                                    .randomId(random.nextInt(10000))
                                    .execute();
                        }else if(message.getText().contains("okay") || message.getText().contains("Okay")) {
                            vk.messages()
                                    .send(actor)
                                    .message("Okay-okay, good luck")
                                    .attachment(stop)
                                    .userId(message.getFromId())
                                    .randomId(random.nextInt(10000))
                                    .execute();
                        }


                        else if(message.getText().equals("Билла")) {
                            vk.messages()
                                    .send(actor)
                                    .message("your fucking Билла")
                                    .attachment(billa)
                                    .userId(message.getFromId())
                                    .randomId(random.nextInt(10000))
                                    .execute();
                        }
                        else if(message.getText().equals("Перекресток")) {
                            vk.messages()
                                    .send(actor)
                                    .message("your fucking Перекресток")
                                    .attachment(perec)
                                    .userId(message.getFromId())
                                    .randomId(random.nextInt(10000))
                                    .execute();
                        }
                        else if(message.getText().equals("Начать") ||
                                message.getText().equals("Start") ||
                                message.getText().equals("start")) {
                            vk.messages()
                                    .send(actor)
                                    .message("start_m")
                                    .userId(message.getFromId())
                                    .randomId(random.nextInt(10000))
                                    .keyboard(keyboard2)
                                    .execute();
                        }
                        else if(message.getText().equals("Карты")) {
                            vk.messages()
                                    .send(actor)
                                    .message("your fucking cards, маленький любитель скидок :)")
                                    .userId(message.getFromId())
                                    .randomId(random.nextInt(10000))
                                    .keyboard(keyboard)
                                    .execute();
                        }
                        else {
                            vk.messages()
                                    .send(actor)
                                    .message("Whhat? Stupid shit")
                                    .attachment(wwhhat)
                                    .userId(message.getFromId())
                                    .randomId(random.nextInt(10000))
                                    .execute();
                        }
                    } catch (ApiException | ClientException e) { e.printStackTrace(); }
                });
            }

            ts = vk.messages().getLongPollServer(actor).execute().getTs();
            Thread.sleep(500);
        }
    }

    public static void uploadPhotoToBot(VkApiClient vk, GroupActor actor, String path) throws ClientException, ApiException {
        PhotosGetMessagesUploadServerQuery server = vk.photos().getMessagesUploadServer(actor);
        GetMessagesUploadServerResponse serverResponse = server.execute();
        File file = new File(path);

        UploadPhotoMessageQuery photo = vk.upload().photoMessage(serverResponse.getUploadUrl().toString(), file);
        //System.out.println(serverResponse.getUploadUrl());
        MessageUploadResponse photoUploadResponse = photo.execute();

        PhotosSaveMessagesPhotoQuery a = vk.photos().saveMessagesPhoto(actor, photoUploadResponse.getPhoto()).server(photoUploadResponse.getServer()).hash(photoUploadResponse.getHash());
        //System.out.println(photoUploadResponse.getPhoto());
        List<SaveMessagesPhotoResponse> list = a.execute();

        //System.out.println("count: " + list.size());
        String p = "photo" + list.get(0).getOwnerId()+"_"+list.get(0).getId();
        //System.out.println(p + " " + file.getName());
    }

    public static void getImages(VkApiClient vk, GroupActor actor) throws ClientException, ApiException {
        PhotosGetMessagesUploadServerQuery server = vk.photos().getMessagesUploadServer(actor);
        GetMessagesUploadServerResponse serverResponse = server.execute();

        APP_ID = ;
        CLIENT_SECRET = "";
        REDIRECT_URI = "";
        code = "";

        UserAuthResponse authResponse = vk.oAuth()
                .userAuthorizationCodeFlow(APP_ID, CLIENT_SECRET, REDIRECT_URI, code)
                .execute();

        UserActor userActor = new UserActor(authResponse.getUserId(), authResponse.getAccessToken());

        PhotosGetQuery photosGetQuery = vk.photos().get(userActor).albumId(serverResponse.getAlbumId().toString());
        GetResponse response = photosGetQuery.execute();
        response.toPrettyString();
    }
}