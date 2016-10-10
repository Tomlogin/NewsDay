package zhuoxin.andriody.com.newsday.info;

/**
 * Created by Administrator on 9/14 0014.
 */
public class Newsinfo {
     public String ctime, title, picUrl;

    public Newsinfo(String ctime, String title, String picUrl) {
        this.ctime = ctime;
        this.title = title;
        this.picUrl = picUrl;
    }

    @Override
    public String toString() {
        return "Newsinfo{" +
                "ctime='" + ctime + '\'' +
                ", title='" + title + '\'' +
                ", picUrl='" + picUrl + '\'' +
                '}';
    }
}