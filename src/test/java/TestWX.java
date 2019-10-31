import com.fh.shop.api.util.MyConfig;
import com.github.wxpay.sdk.WXPay;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class TestWX {

    @Test
    public void test1(){
        MyConfig config = new MyConfig();
        WXPay wxpay = new WXPay(config);

        Map<String, String> data = new HashMap<String, String>();
        data.put("body", "飞狐大卖场");
        data.put("out_trade_no", "2016090910595900000012c");
        data.put("fee_type", "CNY");
        data.put("total_fee", "1");
        data.put("spbill_create_ip", "123.12.12.123");
        data.put("notify_url", "http://www.example.com/wxpay/notify");
        data.put("trade_type", "NATIVE");  // 此处指定为扫码支付
        data.put("product_id", "12");
        try {
            Map<String, String> resp = wxpay.unifiedOrder(data);
            System.out.println(resp);
            String return_code = resp.get("return_code");
            String return_msg = resp.get("return_msg");
            if (!return_code.equalsIgnoreCase("success")){
                System.out.println("11111"+return_msg);
                return;
            }
            String result_code = resp.get("result_code");
            String err_code_des = resp.get("err_code_des");
            if (!result_code.equalsIgnoreCase("success")){
                System.out.println("22222"+err_code_des);
                return;
            }
            String url = resp.get("code_url");
            System.out.println("url为："+ url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test2(){
        MyConfig config = new MyConfig();
        WXPay wxpay = new WXPay(config);

        Map<String, String> data = new HashMap<String, String>();
        data.put("out_trade_no", "2016090910595900000012c");

        try {
            Map<String, String> resp = wxpay.orderQuery(data);
            String return_code = resp.get("return_code");
            String return_msg = resp.get("return_msg");
            if (!return_code.equalsIgnoreCase("success")){
                System.out.println("第一次"+return_msg);
                return;
            }
            String result_code = resp.get("result_code");
            String err_code_des = resp.get("err_code_des");
            if (!result_code.equalsIgnoreCase("success")){
                System.out.println("第二次"+err_code_des);
                return;
            }
            String trade_state = resp.get("trade_state");
            String trade_state_desc = resp.get("trade_state_desc");
            if (trade_state.equalsIgnoreCase("success")){
                System.out.println("第三次"+trade_state_desc);
                return;
            }
            System.out.println(trade_state_desc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
