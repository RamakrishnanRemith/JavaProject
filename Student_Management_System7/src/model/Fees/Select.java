package model.Fees;

public class Select {
	public String key, value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Select(String key, String value) {
		System.out.println(key + "@" + value);
		this.key = key;
		this.value = value;
	}

}
