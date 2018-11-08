package cn.wgt.bean;

/**
 * @author cai
 */
public class RestResult {
    private boolean status;
    private Object data;

    public RestResult(boolean status, Object data) {
        this.status = status;
        this.data = data;
    }

    public RestResult(boolean status) {
        this.status = status;
    }

    public RestResult() {
    }

    public RestResult(Object data) {
        this.status = true;
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RestResult{" +
                "status=" + status +
                ", data=" + data +
                '}';
    }

}
