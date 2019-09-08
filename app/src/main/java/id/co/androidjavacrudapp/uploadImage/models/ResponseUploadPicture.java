package id.co.androidjavacrudapp.uploadImage.models;


import com.google.gson.annotations.SerializedName;

public class ResponseUploadPicture{

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"ResponseUploadPicture{" + 
			"message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}