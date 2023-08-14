package common;

public class batchPogo {
	String batchKey,batchValue;
	public String getBatchKey() {
		return batchKey;
	}
	public void setBatchKey(String batchKey) {
		this.batchKey = batchKey;
	}
	public String getBatchValue() {
		return batchValue;
	}
	public void setBatchValue(String batchValue) {
		this.batchValue = batchValue;
	}
	public batchPogo(String batchKey, String batchValue) {
		// TODO Auto-generated constructor stub
		this.batchKey=batchKey;
		this.batchValue=batchValue;
	}

}
