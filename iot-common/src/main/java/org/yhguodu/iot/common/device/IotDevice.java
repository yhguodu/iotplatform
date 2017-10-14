package org.yhguodu.iot.common.device;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/10/14.
 */
public class IotDevice implements Serializable {
    private static final long serialVersionUID = 7590999461767050470L;

    private final int deviceId;                       //设备ID
    private final String deviceModel;                 //设备型号
    private final String industryCode;                //行业编码
    private final String protocol;                    //协议

    public IotDevice(int deviceId,String deviceModel,String industryCode,String protocol) {
        this.deviceId = deviceId;
        this.deviceModel = deviceModel;
        this.industryCode = industryCode;
        this.protocol = protocol;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public String getIndustryCode() {
        return industryCode;
    }

    public String getProtocol() {
        return protocol;
    }
}
