# my-card-bot
for plastic cards of shops

Долбаный вк не имеет документации на загрузку фотографий для Java, а методы есть
Поэтому здесь будет подробный разбор кода + возможно сделаю видео

Пока что первый минимально рабочий код:  
    PhotosGetMessagesUploadServerQuery server = vk.photos().getMessagesUploadServer(actor);
    GetMessagesUploadServerResponse serverResponse = server.execute();
    File file = new File("C:\\Users\\Dmitry\\IdeaProjects\\bot\\src\\main\\resources\\img\\first.jpg");
    UploadPhotoMessageQuery photo = vk.upload().photoMessage(serverResponse.getUploadUrl().toString(), file);
    MessageUploadResponse photoUploadResponse = photo.execute();
    PhotosSaveMessagesPhotoQuery a = vk.photos().saveMessagesPhoto(actor, photoUploadResponse.getPhoto()).server(photoUploadResponse.getServer()).hash(photoUploadResponse.getHash());
    List<SaveMessagesPhotoResponse> list = a.execute();
    String p = "photo" + list.get(0).getOwnerId()+"_"+list.get(0).getId();

    vk.messages().send(actor).message("Fuck you, okay?").attachment(p).userId(message.getFromId()).randomId(random.nextInt(10000)).execute();
                        
