package Main.Models;

import org.json.JSONObject;
import org.json.JSONTokener;

public class Json
{
	public JSONObject object;
	
	public Json(String json)
	{
		JSONTokener tokener = new JSONTokener(json);
		this.object = new JSONObject(tokener);
	}
	
	public Json(JSONObject object)
	{
		this.object = object;
	}
	
	public boolean getBoolean(String key)
	{
		boolean value = false;
		try {
			value = this.object.getBoolean(key);
		}
		catch (org.json.JSONException exception) {
			System.out.println(exception.getMessage());
		}
		return value;
	}
	
	public int getInt(String key)
	{
		int value = 0;
		try {
			value = this.object.getInt(key);
		}
		catch (org.json.JSONException exception) {
			System.out.println(exception.getMessage());
		}
		return value;
	}
	
	public Json getObject(String key)
	{
		JSONObject value = this.object;
		try {
			value = this.object.getJSONObject(key);
		}
		catch (org.json.JSONException exception) {
			System.out.println(exception.getMessage());
		}
		return new Json(value);
	}
	
	public String getString(String key)
	{
		String value = "";
		try {
			value = this.object.getString(key);
		}
		catch (org.json.JSONException exception) {
			System.out.println(exception.getMessage());
		}
		return value;
	}
}
