package zhuoxin.andriody.com.newsday.Util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Administrator on 9/18 0018.
 */
public class DataManger {
    /**
     * 百度地址
     */
    public static  final  String BAIDU_URL="http://apis.baidu.com/showapi_open_bus/channel_news/search_news";
    public static final String BAIDU_KEY="03db9b5b9d8c25675268bd00f52b65f4";
    /**
     * 拼接url
     */
    public static  String fingNewsByname(String newname,int page){
        return  BAIDU_URL+"?channe1Name="+changeYoUTF(newname)+"&page="+page;
    }

    /**
     * 需要将newname转换成utf-8
     * @param newname
     * @return
     */
    private static  String changeYoUTF(String newname){
        try {
            newname= URLEncoder.encode(newname,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return  newname;
    }
}
