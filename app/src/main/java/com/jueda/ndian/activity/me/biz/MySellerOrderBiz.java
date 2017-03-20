package com.jueda.ndian.activity.me.biz;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.jueda.ndian.entity.CommodityEntity;
import com.jueda.ndian.nohttp.CallServer;
import com.jueda.ndian.nohttp.HttpListener;
import com.jueda.ndian.utils.Constants;
import com.jueda.ndian.utils.ConstantsUser;
import com.jueda.ndian.utils.LogUtil;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.Request;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 卖家订单列表
 * Created by Administrator on 2016/6/25.
 */
public class MySellerOrderBiz implements HttpListener<String> {
    private Request mRequest;
    private Handler handler;
    private ArrayList<CommodityEntity> entityList;
    public MySellerOrderBiz(Context context, Handler handler, ArrayList<CommodityEntity> entityList, int page){
        this.entityList=entityList;
        this.handler=handler;
        RequestMethod method = RequestMethod.POST;// 默认get请求
        mRequest=  NoHttp.createStringRequest(Constants.SELLER_ORDERS_LIST, method);
        mRequest.add("user_token", ConstantsUser.userEntity.getUserToken());
        mRequest.add("p",page);
        // 添加到请求队列
        CallServer.getRequestInstance().add(context, 0, mRequest, this, true, false);
    }
    @Override
    public void onSucceed(int what, Response<String> response) {
        try {
            new LogUtil("MySellerOrderBiz",response.get());
            JSONObject jsonObject=new JSONObject(response.get());
            String status=jsonObject.getString("status");
            if(status.equals("1")){
                JSONArray array=new JSONArray(jsonObject.getString("data"));
                if(array.length()==0){
                    handler.sendEmptyMessage(Constants.FAILURE);
                }else {
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        CommodityEntity entity = new CommodityEntity();
                        entity.setConsignee(object.getString("consignee"));
                        entity.setPhone(object.getString("consignee_phone"));
                        entity.setAddress(object.getString("consignee_add"));
                        entity.setTitle(object.getString("title"));
                        entity.setOfficial_or_personal(object.getString("off_per"));
                        entity.setTotal_fee(object.getString("total_fee"));
                        entity.setOrder_no(object.getString("order_no"));
                        entity.setPrice(object.getString("price"));
                        entity.setFreightage(object.getString("freightage"));
                        entity.setImg(object.getString("img"));
                        entity.setCreat_time(object.getString("creat_time"));
                        entityList.add(entity);
                    }
                    Message message = new Message();
                    message.obj = entityList;
                    message.what = Constants.ON_SUCCEED;
                    handler.sendMessage(message);
                }
            }else if(jsonObject.getString("status").equals("10007")){
                /**用户失效*/
                handler.sendEmptyMessage(Constants.USER_FAILURE);
            }else{
                handler.sendEmptyMessage(Constants.FAILURE_TWO);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            handler.sendEmptyMessage(Constants.FAILURE_TWO);
        }catch (NullPointerException e){
            handler.sendEmptyMessage(Constants.FAILURE_TWO);
            e.printStackTrace();
        }
    }

    @Override
    public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
        handler.sendEmptyMessage(Constants.FAILURE_TWO);
    }
}
