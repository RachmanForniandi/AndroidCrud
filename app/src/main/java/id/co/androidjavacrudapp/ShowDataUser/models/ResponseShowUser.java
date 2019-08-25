package id.co.androidjavacrudapp.ShowDataUser.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseShowUser{

	@SerializedName("response_code")
	private int responseCode;

	@SerializedName("data")
	private List<DataItemShowUser> data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public void setResponseCode(int responseCode){
		this.responseCode = responseCode;
	}

	public int getResponseCode(){
		return responseCode;
	}

	public void setData(List<DataItemShowUser> data){
		this.data = data;
	}

	public List<DataItemShowUser> getData(){
		return data;
	}

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
			"ResponseShowUser{" + 
			"response_code = '" + responseCode + '\'' + 
			",data = '" + data + '\'' + 
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}