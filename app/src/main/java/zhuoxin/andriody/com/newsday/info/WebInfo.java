package zhuoxin.andriody.com.newsday.info;

/**
 * Created by Administrator on 9/20 0020.
 */
public class WebInfo {
       public String title,url;
        public WebInfo(String title, String url) {
            this.title = title;
            this.url = url;
        }

    @Override
    public String toString() {
        return "WebInfo{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}

