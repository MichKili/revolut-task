package task;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Solution {

    private static final String URL_DOMAIN = "://short.com/";
    private static final int MAX_SIZE_KEYWORD = 20;

    private final Map<String, String> urlMap = new ConcurrentHashMap<>(10);


    public String shortGivenUrl(String url, String keyword) {
        URL shortenerUrl;

        if (keyword.trim().length() == 0) {
            throw new IllegalStateException("Keyword can not be empty string");
        }

        if (keyword.length() > MAX_SIZE_KEYWORD) {
            throw new IllegalStateException("Keyword can not have more than 20 characters");
        }

        synchronized (urlMap) {
            if (urlMap.containsKey(keyword)) {
                throw new IllegalStateException("Some url already contains this keyword");
            }

            urlMap.put(keyword, url);
        }

        try {
            shortenerUrl = new URL(url);
        } catch (MalformedURLException e) {
            throw new IllegalStateException("Url can not be empty");
        }

        String protocol = shortenerUrl.getProtocol();
        StringBuilder stringBuilder = new StringBuilder(protocol);
        stringBuilder.append(URL_DOMAIN);
        stringBuilder.append(keyword);
        return stringBuilder.toString();
    }

}
