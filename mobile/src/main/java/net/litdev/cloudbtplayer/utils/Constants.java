package net.litdev.cloudbtplayer.utils;

/**
 * 常量集合
 */
public interface Constants {
    /**
     * 关键字搜索地址，需要format一下，word为URL编码后的数据,第二个值是页码
     */
    String URL_WORD_SEARCH = "http://btdb.in/q/%s/%d?sort=popular";

    /**
     * 主机名
     */
    String URL_HOST = "http://btdb.in";

    /**
     * web 请求的agent为PC
     */
    String AGENT_PC = "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:46.0) Gecko/20100101 Firefox/46.0";


    /**
     * 匹配Hash的正则表达式
     */
    String REGEX_HASH = "btih:\\w+&";

    /**
     * 视频文件后缀(小写)
     */
    String [] VIDEO_EXT = {"rm","rmvb","wmv","avi","mp4","3gp","mkv","flv"};

    /**
     * 豆瓣热门电影前二十条
     */
    String DB_MOVIE = "https://movie.douban.com/j/search_subjects?type=movie&tag=%E7%83%AD%E9%97%A8&sort=recommend&page_limit=21&page_start=0";

    /**
     * 搜索历史数据库名称
     */
    String DB_SEARCH_HOSTORY_NAME = "searchhostory.db";

    /**
     * 搜索历史数据库版本
     */
    int DB_SEARCH_HOSTORY_VERSION = 1;

    /**
     * 解析hash文件列表（1接口）
     */
    String PARSE_LIST_1 = "http://jx.taoka123.com:88/xunlei.php?hash=%s";

    /**
     * 解析文件的播放地址（1接口）
     */
    String PARSE_VIDEO_URI_1 = "http://jx.taoka123.com:88/fx.php?hash=%s&index=%s";

}
