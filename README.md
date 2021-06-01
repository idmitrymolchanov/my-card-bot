# my-card-bot
for plastic cards of shops

to upload image (save in private album)  
  
u need ->  

    TransportClient transportClient = new HttpTransportClient();
    VkApiClient vk = new VkApiClient(transportClient);
	
then -> ONE - Integer - ur groupId; TWO - String - ur accessToken (for group)  

    GroupActor actor = new GroupActor(**ONE**, **TWO**);
	
next:  
* get upload server address    

	  PhotosGetMessagesUploadServerQuery server = vk.photos().getMessagesUploadServer(actor);
	  GetMessagesUploadServerResponse serverResponse = server.execute();
	
* get photo path local  

      File file = new File(path);
	
* upload photo (u need uploadURL and file)  
    
      UploadPhotoMessageQuery photo = vk.upload().photoMessage(serverResponse.getUploadUrl().toString(), file);
      MessageUploadResponse photoUploadResponse = photo.execute();
        
* save photo (u need GroupActor object, upload photo, server, hash)  
		
      PhotosSaveMessagesPhotoQuery a = vk.photos().saveMessagesPhoto(actor, photoUploadResponse.getPhoto()).server(photoUploadResponse.getServer()).hash(photoUploadResponse.getHash());
      List<SaveMessagesPhotoResponse> list = a.execute();

  
Next, to add photo in bot message:  

*  get owner id and photo id  (u need the string like this: "photo-1234567_1234456"  
    
       String line = "photo" + list.get(0).getOwnerId()+"_"+list.get(0).getId();
	
* add attachment in send method  

      .send(actor).message("").attachment(line)
                        
			
## next time about "get" and "delete"  
