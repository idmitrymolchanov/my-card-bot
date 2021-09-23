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
import ru.vkbot.common.ArrayImageConstants;
import ru.vkbot.common.ImageConstants;
import ru.vkbot.common.TextConstants;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    private static final TransportClient transportClient = new HttpTransportClient();
    private static final VkApiClient vk = new VkApiClient(transportClient);
    private static GroupActor actor;

    private static final Random random = new Random();

    public static void main(String[] args) throws ClientException, ApiException, InterruptedException {
        Keyboard keyboard = new Keyboard();
        Keyboard keyboard2 = new Keyboard();

        List<List<KeyboardButton>> allKey = new ArrayList<>();
        List<KeyboardButton> line1 = new ArrayList<>();
        List<KeyboardButton> line2 = new ArrayList<>();
        List<KeyboardButton> line3 = new ArrayList<>();
        List<KeyboardButton> line4 = new ArrayList<>();

        setButton(line1, "Лента");
        setButton(line1, "Пятерочка");
        setButton(line1, "Окей");
        setButton(line2, "Билла");
        setButton(line2, "Перекресток");
        setButton(line2, "Мастер");
        setButton(line3, "Кантата");
        setButton(line3, "Автомаг");
        setButton(line3, "Би-Би");
        setButton(line4, "Чит-город");
        setButton(line4, "Верный");
        setButton(line4, "Здоров-ру");

        allKey.add(line1);
        allKey.add(line2);
        allKey.add(line3);
        allKey.add(line4);
        keyboard.setButtons(allKey);

        List<List<KeyboardButton>> startList = new ArrayList<>();
        List<KeyboardButton> startLine1 = new ArrayList<>();
        startLine1.add(new KeyboardButton().setAction(new KeyboardButtonAction().setLabel("Карты").setType(TemplateActionTypeNames.TEXT)).setColor(KeyboardButtonColor.POSITIVE));
        startList.add(startLine1);
        keyboard2.setButtons(startList);

        int groupId = ;
        String accessToken = "";
        actor = new GroupActor(groupId, accessToken);
        Integer ts = vk.messages().getLongPollServer(actor).execute().getTs();

        while (true) {
            MessagesGetLongPollHistoryQuery historyQuery = vk.messages().getLongPollHistory(actor).ts(ts);
            List<Message> messages = historyQuery.execute().getMessages().getItems();
            if(!messages.isEmpty()) {
                messages.forEach(message -> {
                    try {
                        Random inner_rand = new Random();
                        String textFromMessage = message.getText();

                        switch (textFromMessage) {
                            case "Лента" : {setActionOnButton(finalMessage("Lenta"), ImageConstants.LENTA, message); break;}
                            case "Пятерочка" : {setActionOnButton(finalMessage("Пятерочка"), ImageConstants.PYATEROCHKA, message); break;}
                            case "Окей" : {setActionOnButton(finalMessage("Okay"), ImageConstants.OKAY, message); break;}

                            case "Кантата" : {setActionOnButton(finalMessage("Kantata"), ImageConstants.KANTATA, message); break;}
                            case "Мастер" : {setActionOnButton(finalMessage("master"), ImageConstants.MASTER, message); break;}
                            case "Билла" : {setActionOnButton(finalMessage("Billa"), ImageConstants.BILLA, message); break;}

                            case "Перекресток" : {setActionOnButton(finalMessage("Перекресток"), ImageConstants.PERECRESTOK, message); break;}
                            case "Автомаг" : {setActionOnButton("Avtomag mag mag", ImageConstants.AVTOMAG, message); break;}
                            case "Би-Би" : {setActionOnButton("Bi-Bi, you fag???", ImageConstants.BIBI, message); break;}

                            case "Чит-город" : {setActionOnButton("Читай город", ImageConstants.CHITAY_GOROD, message); break;}
                            case "Верный" : {setActionOnButton("Вах, нэвэрный!", ImageConstants.VERNIY, message); break;}
                            case "Здоров-ру" : {setActionOnButton("Выздоравливай <==З", ImageConstants.ZDOTOV_RU, message); break;}

                            default: {
                                if(TextConstants.hello_list.contains(textFromMessage)) setActionOnButton(TextConstants.HELLO_ARR[inner_rand.nextInt(TextConstants.HELLO_ARR.length)], "", message);
                                else if(TextConstants.jerk_list.contains(textFromMessage)) setActionOnButton(TextConstants.JERK_ARR[inner_rand.nextInt(TextConstants.JERK_ARR.length)], "", message);
                                else if(TextConstants.okay_list.contains(textFromMessage)) setActionOnButton(TextConstants.OKAY_ARR[inner_rand.nextInt(TextConstants.OKAY_ARR.length)], "", message);
                                else if(TextConstants.why_list.contains(textFromMessage)) setActionOnButton(TextConstants.STUPID_ARR[inner_rand.nextInt(TextConstants.STUPID_ARR.length)], ArrayImageConstants.STUPID_PICS_ARR[inner_rand.nextInt(ArrayImageConstants.STUPID_PICS_ARR.length)], message);
                                else if(TextConstants.good_list.contains(textFromMessage)) setActionOnButton(TextConstants.GOOD_ARR[inner_rand.nextInt(TextConstants.GOOD_ARR.length)], ArrayImageConstants.GOOD_PICS_ARR[inner_rand.nextInt(ArrayImageConstants.GOOD_PICS_ARR.length)], message);
                                else if(TextConstants.yes_list.contains(textFromMessage)) setActionOnButton(" ", ImageConstants.KIRKOROV, message);
                                else if(TextConstants.no_list.contains(textFromMessage)) setActionOnButton(" ", ImageConstants.KOT, message);
                                else if(TextConstants.maestro_list.contains(textFromMessage)) setActionOnButton(TextConstants.MAESTRO_STRING, ImageConstants.MAESTRO, message);
                                else if(TextConstants.track_list.contains(textFromMessage)) setActionOnButton(TextConstants.TRACK_STRING, ImageConstants.STOP, message);

                                else if(textFromMessage.equals("Начать") ||
                                        textFromMessage.equals("Start") ||
                                        textFromMessage.equals("start")) {
                                    vk.messages()
                                            .send(actor)
                                            .message("start_m")
                                            .userId(message.getFromId())
                                            .randomId(random.nextInt(10000))
                                            .keyboard(keyboard2)
                                            .execute();
                                }
                                else if(textFromMessage.equals("Карты")) {
                                    vk.messages()
                                            .send(actor)
                                            .message("your fucking cards, маленький любитель скидок :)")
                                            .userId(message.getFromId())
                                            .randomId(random.nextInt(10000))
                                            .keyboard(keyboard)
                                            .execute();
                                }
                                else if(textFromMessage.equals("Upload_new"))
                                    uploadPhoto();
                                else {
                                    setActionOnButton(TextConstants.WHAT_ARR[inner_rand.nextInt(TextConstants.WHAT_ARR.length)],
                                            ArrayImageConstants.WHAT_PICS_ARR[inner_rand.nextInt(ArrayImageConstants.WHAT_PICS_ARR.length)], message);
                                }
                            }
                        }
                        //System.out.println(message.toString());
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
        System.out.println(serverResponse.getUploadUrl());
        MessageUploadResponse photoUploadResponse = photo.execute();

        PhotosSaveMessagesPhotoQuery a = vk.photos().saveMessagesPhoto(actor, photoUploadResponse.getPhoto()).server(photoUploadResponse.getServer()).hash(photoUploadResponse.getHash());
        //System.out.println(photoUploadResponse.getPhoto());
        List<SaveMessagesPhotoResponse> list = a.execute();

        //System.out.println("count: " + list.size());
        String p = "photo" + list.get(0).getOwnerId()+"_"+list.get(0).getId();
        System.out.println(p + " " + file.getName());
    }

    public static void getImages(VkApiClient vk, GroupActor actor) throws ClientException, ApiException {
        PhotosGetMessagesUploadServerQuery server = vk.photos().getMessagesUploadServer(actor);
        GetMessagesUploadServerResponse serverResponse = server.execute();

        Integer APP_ID = 0;
        String CLIENT_SECRET = "";
        String REDIRECT_URI = "";
        String code = "";

        UserAuthResponse authResponse = vk.oAuth()
                .userAuthorizationCodeFlow(APP_ID, CLIENT_SECRET, REDIRECT_URI, code)
                .execute();

        UserActor userActor = new UserActor(authResponse.getUserId(), authResponse.getAccessToken());

        PhotosGetQuery photosGetQuery = vk.photos().get(userActor).albumId(serverResponse.getAlbumId().toString());
        GetResponse response = photosGetQuery.execute();
        response.toPrettyString();
    }

    public static void setButton(List<KeyboardButton> line, String name) {
        line.add(new KeyboardButton()
                .setAction(new KeyboardButtonAction()
                        .setLabel(name)
                        .setType(TemplateActionTypeNames.TEXT))
                .setColor(KeyboardButtonColor.POSITIVE));
    }

    public static void setActionOnButton(String label, String attachment, Message message)
            throws ClientException, ApiException {
        if(attachment == null) attachment = "";

        vk.messages()
                .send(actor)
                .message(label)
                .attachment(attachment)
                .userId(message.getFromId())
                .randomId(random.nextInt(10000))
                .execute();
    }

    public static String finalMessage(String param) {
        return "your fucking " + param + ", хорошо?";
    }

    public static void uploadPhoto() {
//        String a = "C:\\Users\\Dmitry\\IdeaProjects\\bot\\src\\main\\resources\\img\\new\\avtomag.jpg";
//        String b = "C:\\Users\\Dmitry\\IdeaProjects\\bot\\src\\main\\resources\\img\\new\\bibi.jpg";
//        String c = "C:\\Users\\Dmitry\\IdeaProjects\\bot\\src\\main\\resources\\img\\new\\gorod.jpg";
//        uploadPhotoToBot(vk, actor, a);
//        uploadPhotoToBot(vk, actor, b);
//        uploadPhotoToBot(vk, actor, c);
    }
}